<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>문의사항</h5>
	<table class="table table-striped table-hover">
		<tr>
			<th>작성자</th> <th>제목</th> <th>내용</th> <th>작성일</th> 
		</tr>
		<c:forEach items="${questionList }" var="vo" varStatus="status">
			<tr id="${vo.questionId }">
				<td>
					<c:if test="${vo.depth == 1 }">
							&rdca;${vo.writer }
					</c:if>
					<c:if test="${vo.depth == 0 }">
						${vo.writer }
					</c:if>
				</td>
				<c:if test="${vo.isOpen eq 'N'}">
					<!-- id가 작성자이거나, 관리자이거나, 읽을 권한이 있거나 -->
					<c:choose>
						<c:when test="${vo.writer eq id || id eq 'admin' || vo.canRead eq id }">
							<td>${vo.title }</td>
							<td>${vo.content }</td>
						</c:when>
						<c:otherwise>
							<td colspan="2" style="text-align:center;">비밀글은 작성자와 관리자만 볼 수 있습니다</td>
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${vo.isOpen eq 'Y'}">
					<td>${vo.title }</td>
					<td>${vo.content }</td>
				</c:if>
				<td>${vo.regDate }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>