<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>noticeList</title>
	<script>
		function formSubmit(id){
			frm.id.value = id;
			frm.submit();
		}
	</script>
</head>
<body>
	<form id="frm" action="notice.do" method="post">
		<input type="hidden" id="id" name="id">
	</form>
	<div align="center">
		<div style="width: 70%">
			<h3>공지 사항</h3>
			<table class="table" border="1">
				<tr>
					<th width="20">순번</th>
					<th width="200">제목</th>
					<th width="50">작성일자</th>
					<th width="20">조회수</th>
				</tr>
				
				<c:forEach items="${noticeList }" var="vo">
					<tr onclick="formSubmit(${vo.id })">
						<td>${vo.id }</td>
						<td>${vo.title }</td>
						<td>${vo.regDate }</td>
						<td>${vo.hit }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<button type="button" onclick="location.href='main.do'">홈으로</button>
			<c:if test="${id eq 'admin' }">
				<button type="button" onclick="location.href='noticeInsertForm.do'">등록</button>
			</c:if>
		</div>
	</div>

</body>
</html>