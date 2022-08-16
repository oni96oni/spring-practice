<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="mainUrl" value="." />
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">Logo</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav me-auto">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						JSP/Servlet
					</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<li>
							<a class="dropdown-item" href="./jsp/script">스크립트 태그</a>
						</li>
						<li>
							<a class="dropdown-item" href="./jsp/request">request 객체</a>
						</li>
						<li>
							<a class="dropdown-item" href="./jsp/response">response 객체</a>
						</li>
						<li>
							<a class="dropdown-item" href="./jsp/model2">Model2</a>
						</li>
					</ul>
				</li>
				<c:if test="${not empty sessionScope.loginData}">
					<li class="nav-item">
						<a class="nav-link" href="${mainUrl}/board">게시판</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${mainUrl}/emp">직원</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${mainUrl}/dept">부서</a>
					</li>
				</c:if>
			</ul>
			<div class="d-flex">
				<c:if test="${not empty sessionScope.loginData}">
					<ul class="navbar-nav">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="profileDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
								${sessionScope.loginData.empName}
							</a>
							<ul class="dropdown-menu" aria-labelledby="profileDropdownMenuLink">
								<li>
									<a class="dropdown-item" href="${mainUrl}/myinfo">개인정보</a>
								</li>
								<li>
									<a class="dropdown-item" href="${mainUrl}/logout">로그아웃</a>
								</li>
							</ul>
						</li>
					</ul>
				</c:if>
			</div>
		</div>
	</div>
</nav>