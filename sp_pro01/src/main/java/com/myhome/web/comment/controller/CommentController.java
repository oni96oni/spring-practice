package com.myhome.web.comment.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myhome.web.comment.model.CommentDTO;
import com.myhome.web.comment.service.CommentService;
import com.myhome.web.emp.model.EmpDTO;

@Controller
public class CommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService service;
	
	@PostMapping(value="/comment/add")
	public void addComment(HttpServletRequest request, HttpServletResponse response, @SessionAttribute("loginData") EmpDTO empDto, @RequestParam int bid, @RequestParam String content) throws IOException {
		logger.info("addComment(empDto={}, bid={}, content={})", empDto, bid, content);
		
		CommentDTO commentData = new CommentDTO();
		commentData.setbId(bid);
		commentData.setContent(content);
		commentData.setEmpId(empDto.getEmpId());
		
		service.add(commentData);
		
		response.sendRedirect(request.getContextPath() + "/board/detail?id=" + commentData.getbId());
	}
	
	@PostMapping(value="/comment/modify", produces="application/json; charset=utf-8")
	@ResponseBody
	public void modifyComment(HttpServletResponse response, @SessionAttribute("loginData") EmpDTO empDto, @RequestParam int id, @RequestParam String content) throws IOException {
		
		CommentDTO commentData = service.getData(id);
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(commentData.getEmpId() == empDto.getEmpId()) {
			commentData.setContent(content);
			boolean result = service.modify(commentData);
			if(result) {
				sb.append(String.format("\"%s\": \"%s\", ", "code", "success"));
				sb.append(String.format("\"%s\": \"%s\"  ", "value", commentData.getContent()
						.replace("\r", "\\r").replace("\n", "\\n") ));
			}
		}
		sb.append("}");
		
		response.getWriter().append(sb.toString());
		response.getWriter().flush();
		
	}
	
	@PostMapping(value="/comment/delete", produces="application/json; charset=utf-8")
	@ResponseBody
	public void deleteComment(HttpServletResponse response, @SessionAttribute("loginData") EmpDTO empDto, @RequestParam int id) throws IOException {
		CommentDTO commentData = service.getData(id);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(commentData.getEmpId() == empDto.getEmpId()) {
			boolean result = service.remove(commentData);
			if(result) {
				sb.append(String.format("\"%s\": \"%s\"", "code", "success"));
			} else {
				sb.append(String.format("\"%s\": \"%s\"", "code", "error"));
			}
		} else {
			sb.append(String.format("\"%s\": \"%s\"", "code", "error"));
		}
		sb.append("}");
		
		response.getWriter().append(sb.toString());
		response.getWriter().flush();
	}
}
