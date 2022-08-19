package com.myhome.web.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.service.BoardService;
import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.comment.service.CommentService;
import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.emp.service.EmpService;
import com.myhome.web.upload.model.FileUploadDTO;
import com.myhome.web.upload.service.FileUploadService;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private EmpService empService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getList(Model model, HttpSession session
			, @RequestParam(defaultValue="1", required=false) int page
			, @RequestParam(defaultValue="0", required=false) int pageCount) {
		
		List datas = service.getAll();
		
		if(session.getAttribute("pageCount") == null) {
			session.setAttribute("pageCount", 5);
		}
		
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount);
		}
		
		pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
		Paging paging = new Paging(datas, page, pageCount);
		
		model.addAttribute("datas", paging.getPageData());
		model.addAttribute("pageData", paging);
		
		return "board/list";
	}
	
	@GetMapping(value="/detail")
	public String getDetail(Model model
			, HttpSession session
			, @RequestParam int id
			, @RequestParam(value="page", defaultValue = "1") String page) {
		
		BoardDTO data = service.getData(id);
		List<FileUploadDTO> fileDatas = fileUploadService.getDatas(id);
		
		if(data != null) {
			service.incViewCnt(session, data);
			EmpDTO empData = empService.getId("" + data.getEmpId());
			
			List commentDatas = commentService.getDatas(data.getId());
		
			page = page == null ? "1" : page;
		
			Paging commentPage = new Paging(commentDatas, Integer.parseInt(page), 5);
		
			model.addAttribute("data", data);
			model.addAttribute("empData", empData);
			model.addAttribute("commentPage", commentPage);
			model.addAttribute("fileDatas", fileDatas);
			return "board/detail";
		} else {
			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
			return "error/notExists";
		}
	}
	
	@GetMapping(value="/add")
	public String add() {
		return "board/add";
	}
	
	@PostMapping(value="/add")
	public String add(HttpServletRequest request
			, @SessionAttribute("loginData") EmpDTO empDto
			, @ModelAttribute BoardVO boardVo
			, @RequestParam("fileUpload") MultipartFile[] files) {
		
		BoardDTO data = new BoardDTO();
		data.setTitle(boardVo.getTitle());
		data.setContent(boardVo.getContent());
		data.setEmpId(empDto.getEmpId());
		
		int id = service.add(data);
		
		for(MultipartFile file: files) {
			String location = request.getServletContext().getRealPath("/resources/board/upload");
			String url = "/static/board/upload";
			FileUploadDTO fileData = new FileUploadDTO(id, location, url);
			
			try {
				int fileResult = fileUploadService.upload(file, fileData);
				if(fileResult == -1) {
					request.setAttribute("error", "파일 업로드 수량을 초과하였습니다.");
					return "board/add";
				}
			} catch(Exception e) {
				request.setAttribute("error", "파일 업로드 작업중 예상치 못한 에러가 발생하였습니다.");
				return "board/add";
			}
			
		}
		
		if(id != -1) {
			return "redirect:/board/detail?id=" + id;			
		} else {
			request.setAttribute("error", "게시글 저장 실패!");
			return "board/add";
		}
	}
	
	@GetMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int id) {
		
		BoardDTO data = service.getData(id);
		if(data != null) {
			if(data.getEmpId() == empDto.getEmpId()) {
				model.addAttribute("data", data);
				return "board/modify";
			} else {
				model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
				return "error/permission";
			}
		} else {
			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
			return "error/noExists";
		}
	}
	
	@PostMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute("loginData") EmpDTO empDto
			, @ModelAttribute BoardVO boardVo) {
		
		BoardDTO data = service.getData(boardVo.getId());
		
		if(data != null) {
			if(data.getEmpId() == empDto.getEmpId()) {
				data.setTitle(boardVo.getTitle());
				data.setContent(boardVo.getContent());
				boolean result = service.modify(data);
				if(result) {
					return "redirect:/board/detail?id=" + data.getId();
				} else {
					return modify(model, empDto, boardVo.getId());
				}
			} else {
				model.addAttribute("error", "해당 작업을 수행할 권한이 없습니다.");
				return "error/permission";
			}
		} else {
			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
			return "error/noExists";
		}
	}
	
	@PostMapping(value="/delete", produces="application/json; charset=utf-8")
	@ResponseBody
	public String delete(@SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int id) {
		
		BoardDTO data = service.getData(id);
		
		JSONObject json = new JSONObject();
		
		if(data == null) {
			// 삭제할 데이터 없음
			json.put("code", "notExists");
			json.put("message", "이미 삭제 된 데이터 입니다.");
		} else {
			if(data.getEmpId() == empDto.getEmpId()) {
				// 작성자, 수정자 동일인
				boolean result = service.remove(data);
				if(result) {
					json.put("code", "success");
					json.put("message", "삭제가 완료되었습니다.");
				} else {
					// 삭제 실패
					json.put("code", "fail");
					json.put("message", "삭제 작업 중 문제가 발생하였습니다.");
				}
			} else {
				// 작성자, 수정자 동일인 아님 - 권한 없음
				json.put("code", "permissionError");
				json.put("message", "삭제 할 권한이 없습니다.");
			}
		}
		
		return json.toJSONString();
	}
	
	@PostMapping(value="/like", produces="application/json; charset=utf-8")
	@ResponseBody
	public String like(HttpSession session
			, @RequestParam int id) {
		
		BoardDTO data = service.getData(id);
		JSONObject json = new JSONObject();
		
		if(data == null) {
			// 존재하지 않음.
			json.put("code", "noData");
			json.put("message", "해당 데이터가 존재하지 않습니다.");
		} else {
			service.incLike(session, data);
			json.put("code", "success");
			json.put("like", data.getLike());
		}
		return json.toJSONString();
	}
	
}
