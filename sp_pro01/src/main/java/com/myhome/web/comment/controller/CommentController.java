package com.myhome.web.comment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myhome.web.comment.model.CommentDTO;
import com.myhome.web.comment.service.CommentService;
import com.myhome.web.emp.model.EmpDTO;

@Controller
public class CommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService service;
	
	@PostMapping("/board/comment/add")
	public String addComment(@SessionAttribute("loginData") EmpDTO empDto, Model model) {
		logger.info("add(empDto={})", empDto);
		
		String bid = (String) model.getAttribute("bid");
		String content = (String) model.getAttribute("content");
		
		CommentDTO commentData = new CommentDTO();
		commentData.setbId(Integer.parseInt(bid));
		commentData.setContent(content);
		commentData.setEmpId(empDto.getEmpId());
		
		service.add(commentData);
		return "/board/detail?id=" + commentData.getbId();
	}
}
