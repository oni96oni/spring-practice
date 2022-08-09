<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="loginUrl" value="/login" />
<form action="${loginUrl}" method="post">
	<div class="form-floating mb-2">
		<input class="form-control" type="text" id="id_empId" name="empId" value="" placeholder="직원ID">
		<label for="id_empId">직원ID</label>
	</div>
	<div class="form-floating mb-2">
		<select class="form-select" id="id_deptId" name="deptId">
			<option>부서 선택</option>
			<c:forEach items="${deptDatas}" var="deptDto">
				<option value="${deptDto.deptId}">[${deptDto.deptId}] ${deptDto.deptName}</option>
			</c:forEach>
		</select>
		<label for="id_deptId">부서 선택</label>
	</div>
	<div class="form-floating mb-2">
		<input class="form-control" type="text" id="id_empName" name="empName" value="" placeholder="이름">
		<label for="id_empName">이름</label>
	</div>
	<div class="mb-2 text-end">
		<button class="btn btn-outline-primary" type="submit">로그인</button>
	</div>
</form>