<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${data.title}</title>
	<%@ include file="../module/head.jsp" %>
</head>
<script type="text/javascript">
	function ajaxLike(element, id) {
		$.ajax({
			type: "post",
			url: "/ajax/board/like",
			data: {
				id: id
			},
			success: function(data) {
				element.innerText = data.like;
			}
		});
	}
</script>
<body>
	<header></header>
	<section class="container">
		<div class="mt-3">
			<div class="mb-1 border-bottom border-2 border-secondary">
				<h1>${data.title}</h1>
			</div>
			<div class="mb-3">
				<label class="pe-3 text-secondary text-opacity-75">${data.empName}</label>
				<fmt:formatDate value="${data.createDate}" var="createDate" dateStyle="long" />
				<label class="pe-3 text-secondary text-opacity-75">${createDate}</label>
				<label class="pe-3 text-secondary text-opacity-75">조회수: ${data.viewCnt}</label>
			</div>
			<div class="mb-1 border-bottom border-2 border-secondary">
				<p>${data.content}</p>
			</div>
			<div class="mb-1">
				<div onclick="ajaxLike(id_like, ${data.id});">
					<i class="bi bi-hand-thumbs-up text-secondary text-opacity-50"></i>
					<label id="id_like" class="text-secondary text-opacity-75">${data.like}</label>
				</div>
			</div>
			<div class="mb-1 text-end">
				<c:url var="boardUrl" value="/board" />
				<button class="btn btn-primary" type="button" onclick="location.href='${boardUrl}'">목록</button>
				<c:if test="${data.empId eq sessionScope.loginData.empId}">
					<button class="btn btn-success" type="button" onclick="location.href='${boardUrl}/modify?id=${data.id}'">수정</button>
					<button class="btn btn-danger" type="button" data-bs-toggle="modal" data-bs-target="#removeModal">삭제</button>
				</c:if>
			</div>
		</div>
		
		<nav>
			<div>
				<ul class="pagination justify-content-center">
					<c:url var="boardUrl" value="/board/detail">
						<c:param name="id">${data.id}</c:param>
					</c:url>
					<c:if test="${commentPage.hasPrevPage()}">
						<li class="page-item">
							<a class="page-link" href="${boardUrl}&page=${commentPage.prevPageNumber}">Prev</a>
						</li>
					</c:if>
					<c:forEach items="${commentPage.getPageNumberList(commentPage.currentPageNumber - 2, commentPage.currentPageNumber + 2)}" var="num">
						<li class="page-item ${commentPage.currentPageNumber eq num ? 'active' : ''}">
							<a class="page-link" href="${boardUrl}&page=${num}">${num}</a>
						</li>
					</c:forEach>
					<c:if test="${commentPage.hasNextPage()}">
						<li class="page-item">
							<a class="page-link" href="${boardUrl}&page=${commentPage.nextPageNumber}">Next</a>
						</li>
					</c:if>
				</ul>
			</div>
		</nav>
		
		<div class="mt-3 mb-3">
			<c:forEach items="${commentPage.pageData}" var="comment">
				<div class="mb-1">
					<div class="card border-light">
						<div class="card-header">
							<div class="d-flex justify-content-between">
								<span><small>${comment.empName}</small></span>
								<span><small>${comment.createDate}</small></span>
							</div>
						</div>
						<div class="card-body">
							<input type="hidden" value="${comment.id}">
							<c:choose>
								<c:when test="${comment.isDeleted()}">
									<p class="text-muted">삭제된 댓글 입니다.</p>
								</c:when>
								<c:otherwise>
									<c:set var="newLine" value="<%= \"\n\" %>" />
									<p class="card-text">${fn:replace(comment.content, newLine, '<br>')}</p>
								</c:otherwise>
							</c:choose>
							<c:if test="${sessionScope.loginData.empId eq comment.empId}">
								<c:if test="${not comment.isDeleted()}">
									<div class="text-end">
										<button class="btn btn-sm btn-outline-dark" type="button" onclick="changeEdit(this);">수정</button>
										<button class="btn btn-sm btn-outline-dark" type="button" onclick="commentDelete(this, ${comment.id})">삭제</button>
									</div>
								</c:if>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
			<div class="mb-1">
				<form action="/comment/add" method="post">
					<input type="hidden" name="bid" value="${data.id}">
					<div class="input-group">
						<textarea class="form-control" name="content" rows="2"></textarea>
						<button class="btn btn-outline-dark" type="button" onclick="formCheck(this.form);">등록</button>
					</div>
				</form>
			</div>
		</div>
		
		<div class="modal fade" id="removeModal" tabindex="-1" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h6 class="modal-title">삭제 확인</h6>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						해당 데이터를 삭제하겠습니까?
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-danger" data-bs-dismiss="modal" onclick="deleteBoard(${data.id})">확인</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<footer></footer>
	<script type="text/javascript">
		function changeEdit(element) {
			element.innerText = "확인";
			element.nextElementSibling.remove();
			
			var value = element.parentElement.previousElementSibling.innerText;
			var textarea = document.createElement("textarea");
			textarea.setAttribute("class", "form-control");
			textarea.value = value;
			
			element.parentElement.previousElementSibling.innerText = "";
			element.parentElement.previousElementSibling.append(textarea);
			
			element.setAttribute("onclick", "commentUpdate(this);");
		}
		
		function changeText(element) {
			element.innerText = "수정";
			var cid = element.parentElement.parentElement.children[0].value;
			var value = element.parentElement.previousElementSibling.children[0].value;
			element.parentElement.previousElementSibling.children[0].remove();
			element.parentElement.previousElementSibling.innerText = value;
			
			var btnDelete = document.createElement("button");
			btnDelete.innerText = "삭제";
			btnDelete.setAttribute("class", "btn btn-sm btn-outline-dark");
			btnDelete.setAttribute("onclick", "commentDelete(this, " + cid + ");");
			
			element.parentElement.append(btnDelete);
			element.setAttribute("onclick", "changeEdit(this);");
		}
		
		function commentUpdate(element) {
			var cid = element.parentElement.parentElement.children[0].value;
			var value = element.parentElement.previousElementSibling.children[0].value;
			
			$.ajax({
				url: "/comment/modify",
				type: "post",
				data: {
					id: cid,
					content: value
				},
				success: function(data) {
					element.parentElement.previousElementSibling.children[0].value = data.value
					changeText(element);
				}
			});
		}
		
		function commentDelete(element, id) {
			$.ajax({
				url: "/comment/delete",
				type: "post",
				data: {
					id: id
				},
				success: function(data) {
					if(data.code === "success") {
						element.parentElement.parentElement.parentElement.parentElement.remove();
					}
				}
			});
		}
		function formCheck(form) {
			if(form.content.value.trim() === "") {
				alert("댓글 내용을 입력하세요.");
			} else {
				form.submit();
			}
		}
		function deleteBoard(boardId) {
			$.ajax({
				url: "/board/delete",
				type: "post",
				data: {
					id: boardId
				},
				dataType: "json",
				success: function(data) {
					if(data.code === "success") {
						alert("삭제 완료");
						location.href = "/board";
					} else if(data.code === "permissionError") {
						alert("권한이 오류");
					} else if(data.code === "notExists") {
						alert("이미 삭제되었습니다.")
					}
				}
			});
		}
	</script>
</body>
</html>