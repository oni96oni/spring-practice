package com.myhome.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(
//		urlPatterns = {
//				"/myinfo", "/depts", "/locs",
//				"/depts/*", "/locs/*",
//				"/emps", "/emps/*",
//				"/board", "/board/*",
//				"/comment/add"
//		}
//)
public class LoginFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		HttpServletRequest req = (HttpServletRequest)request;
		
		if(session.getAttribute("loginData") != null) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/login?url=" + req.getRequestURI());
		}
	}
	
}
