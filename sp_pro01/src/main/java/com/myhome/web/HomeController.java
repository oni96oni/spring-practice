package com.myhome.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myhome.web.dept.model.DeptDTO;
import com.myhome.web.dept.service.DeptService;

@Controller
public class HomeController {
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<DeptDTO> deptDatas = deptService.getAll();
		model.addAttribute("deptDatas", deptDatas);
		return "index";
	}
	
}
