package com.myhome.web.login.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myhome.web.dept.model.DeptDTO;
import com.myhome.web.dept.service.DeptService;
import com.myhome.web.login.service.LoginService;
import com.myhome.web.login.vo.LoginVO;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService service;
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(LoginVO loginVo, HttpSession session, Model model) {
		logger.info("login({}, {}, {})", loginVo.getEmpId(), loginVo.getDeptId(), loginVo.getEmpName());
		
		boolean result = service.getLogin(session, loginVo);
		
		if(result) {
			// 로그인 성공
			return "redirect:/";
		} else {
			// 로그인 실패
			List<DeptDTO> deptDatas = deptService.getAll();
			model.addAttribute("deptDatas", deptDatas);
			return "login/login";
		}
	}
}

	/*
	 * 1. HttpServletRequest
	 * 2. 파라메터명과 동일한 매개변수명을 사용
	 * 3. 파라메터명과 동일한 멤버변수를 가지는 객체를 사용
	 * 
	 */