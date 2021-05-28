<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="//cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>
	<script>
	// CKEDITOR.instances.content.getData() = name인지 id인지 뭔지 모르지만 가져옴 
		$(function(){ 
			CKEDITOR.replace('content', {
				filebrowserUploadUrl: '${pageContext.request.contextPath }/fileUpload', // 서블릿을 찾아가라
				height:'600px',
				width:'800px'
			});
		});
		
		function bulletinDelete(){
			deleteFrm.submit();
		}
		
		/* 
		$('#btnUpdate').click(function (e){
			e.preventDefault();
			console.log(CKEDITOR.instances.content.getData());
			
			let id = $('#id').val();
			let title = $('#title').val();
			let content = CKEDITOR.instances.content.getData();
			$.ajax({
				url: 'ajaxBulletinUpdate',
				data: {
					id:id,
					title:title,
					content:content
				},
				success: function(result){
					console.log(result)
					location.href = 'bulletinListPaging.do';
				},
				error: function(err){
					console.log(err);
				}
			});
		}) */
		
	</script>
</head>
<body>
	<div align="center">
		<div style="width: 50%">
			<h3> 상세 </h3>
			<form id="frm" action="bulletinUpdate.do" method="post">
				<table class="table" border="1">
					<tr>
						<th>순번</th>
						<td>${bulletin.id }</td>
						<th>작성자</th>
						<td id="id">${bulletin.writer }</td>
						<th>작성일자</th>
						<td>${bulletin.regDate }</td>
						<th>조회수</th>
						<td>${bulletin.hit }</td>
					</tr>
					<tr>
						<th>제목</th>
						<c:if test="${id == bulletin.writer }">
							<td colspan="7"><input type="text" name="title" value="${bulletin.title }"></td>
						</c:if>
						<c:if test ="${id ne bulletin.writer }">
							<td colspan="7">${bulletin.title }</td>
						</c:if>
						
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="7"><textarea rows="6" cols="90" name="content" id="content">${bulletin.content }</textarea></td>
					</tr>
				</table>
				<div>
					<button type="button" onclick="location.href='bulletinListPaging.do'">목록 보기</button>
					<c:if test="${id == bulletin.writer }">
						<input type="hidden" name="id" value="${bulletin.id }">
						<button type="submit">수정</button>
						<button type="button" onclick="bulletinDelete()">삭제</button>
					</c:if>
				</div>
			</form>
			<form id="deleteFrm" action="bulletinDelete.do" method="post">
				<input type="hidden" name="id" value="${bulletin.id }">
			</form>
			
		</div>
	</div>
</body>
</html>