package com.myhome.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.myhome.web.emp.model.EmpDTO;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginData") == null) {
			String qs = "";
			if(request.getQueryString() != null) {
				qs = "?" + request.getQueryString();
			}
			EmpDTO empData = new EmpDTO();
			session.setAttribute("loginData", empData);
			response.sendRedirect(request.getContextPath() + "/login?url=" + request.getRequestURI() + qs);
			return false;
		} else {
			return true;
		}
	}
}
