package com.myhome.web.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.service.BoardService;
import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDTO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getList(Model model, HttpSession session
			, @RequestParam(defaultValue="1", required=false) int page
			, @RequestParam(defaultValue="0", required=false) int pageCount) {
		logger.info("getList(page={}, pageCount={})", page, pageCount);
		
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
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add() {
		return "board/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@SessionAttribute("loginData") EmpDTO empDto, @ModelAttribute BoardVO boardVo) {
		logger.info("add(boardVo={})", boardVo);
		
		BoardDTO data = new BoardDTO();
		data.setTitle(boardVo.getTitle());
		data.setContent(boardVo.getContent());
		data.setEmpId(empDto.getEmpId());
		
		int id = service.add(data);
		if(id != -1) {
			return "redirect:/board/detail?id=" + id;
		} else {
			return "board/add";
		}
		
	}
	
	@GetMapping("/detail")
	public String getDetail(Model model, @RequestParam int id) {
		logger.info("getDetail(id={})", id);
		
		BoardDTO data = service.getData(id);
		
		if(data != null) {
			model.addAttribute("data", data);
			return "board/detail";
		} else {
			model.addAttribute("error", "해당 데이터가 존재하지 않습니다.");
			return "error/notExists";
		}
	}
	
	@GetMapping(value="/modify")
	public String modify(Model model
			, @SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int id) {
		logger.info("modify(empDto={}, id={})", empDto, id);
		
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
		logger.info("modify(empDto={}, boardVo={})", empDto, boardVo);
		
		BoardDTO data = service.getData(boardVo.getId());
		
		if(data != null) {
			if(data.getEmpId() == empDto.getEmpId()) {
				data.setTitle(boardVo.getTitle());
				data.setContent(boardVo.getContent());
				boolean result = service.modify(data);
				if(result) {
					return "redirect:/board/detail?id=" + data.getId();
				} else {
					return "board/modify";
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
}
