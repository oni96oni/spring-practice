package com.myhome.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginData") == null) {
			String qs = "";
			if(req.getQueryString() != null) {
				qs = req.getQueryString();
			}
			response.sendRedirect(req.getContextPath() + "/login?url=" + req.getRequestURI() + "?" + req.getQueryString());
			return false;
		} else {
			return true;
		}
		
		
	}
	
}
