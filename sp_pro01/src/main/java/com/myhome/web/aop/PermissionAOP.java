package com.myhome.web.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.login.model.PermDTO;
import com.myhome.web.login.model.PermissionDAO;

@Component
@Aspect
public class PermissionAOP {

	@Autowired
	private PermissionDAO dao;
	
	// 모든 서비스 클래스의 메서드 중 get 으로 시작하는 메서드만 선택될 수 있게 PointCut으로 설정한다.
	@Pointcut(value="execution(public * com.myhome.web..*Service.get*(..))")
	private void permSelectCut() {}
	
	// 모든 서비스 클래스의 메서드 중 add 로 시작하는 메서드만 선택될 수 있게 PointCut으로 설정한다.
	@Pointcut(value="execution(public * com.myhome.web..*Service.add*(..))")
	private void permInsertCut() {}
	
	// 모든 서비스 클래스의 메서드 중 modify 로 시작하는 메서드만 선택될 수 있게 PointCut으로 설정한다.
	@Pointcut(value="execution(public * com.myhome.web..*Service.modify*(..))")
	private void permUpdateCut() {}
	
	// 모든 서비스 클래스의 메서드 중 remove 로 시작하는 메서드만 선택될 수 있게 PointCut으로 설정한다.
	@Pointcut(value="execution(public * com.myhome.web..*Service.remove*(..))")
	private void permDeleteCut() {}
	
	@Before(value="permSelectCut()")
	public void beforePermSelect(JoinPoint joinPoint) throws Exception {
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse resp = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		HttpSession session = req.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		String serviceName = joinPoint.getSignature().toShortString().split("\\.")[0];
		serviceName = serviceName.split("Service")[0].toLowerCase();
		
		PermDTO data = new PermDTO();
		data.setTableName("other");
		if(empData != null) {
			data.setEmpId(empData.getEmpId());
			data.setTableName(serviceName);
		}
		
		boolean result = dao.selectData(data);
		
		if(result) {
			if(data.ispRead()) {
				System.out.println("읽기 권한 있음");
			} else {
				 System.out.println("읽기 권한 없음");
				 throw new PermissionDeniedDataAccessException("권한이 없습니다.", null);
			}
		} else {
			System.out.println("권한 정보를 찾을 수 없음.");
		}
	}
	
	@Before(value="permInsertCut()")
	public void beforePermInsert(JoinPoint joinPoint) {
	}
	
	@Before(value="permUpdateCut()")
	public void beforePermUpdate(JoinPoint joinPoint) {
	}
	
	@Before(value="permDeleteCut()")
	public void beforePermDelete(JoinPoint joinPoint) {
	}
}
