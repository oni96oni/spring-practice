package com.myhome.web.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingInterceptor implements HandlerInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// true로 반환하면, 다음 인터셉터 또는 컨트롤러로 이동
		// false를 반환하면 응답으로 넘어감.
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
		
		logger.info("[{}] {}", dateFormat.format(date), request.getRequestURI());
		
		request.setAttribute("timer", date);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 동작이 완료되면 동작한다.
		Date date = new Date();
		Date oldDate = (Date)request.getAttribute("timer");
		long timer = date.getTime() - oldDate.getTime();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
		logger.info("[{}] - {} 내부 로직 동작 시간 -> {}초", dateFormat.format(date), request.getRequestURI() , timer/1000.0);
		
		request.setAttribute("timer", date);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// view(jsp) 페이지 작업이 완료되면 동작한다.
		Date date = new Date();
		Date oldDate = (Date)request.getAttribute("timer");
		long timer = date.getTime() - oldDate.getTime();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
		logger.info("[{}] - {} 뷰 랜더링 시간 -> {}초", dateFormat.format(date), request.getRequestURI() , timer/1000.0);
		
		request.setAttribute("timer", date);
	}
}
