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
	<h5>상품평</h5>
	<table class="table table-striped">
		<tr>
			<th>순번</th> <th>작성자</th> <th>내용</th> <th>작성일</th> <th>추천도</th> 
		</tr>
		<c:forEach items="${reviewList }" var="vo">
			<tr>
				<td>${vo.reviewId }</td> <td>${vo.writer }</td> <td>${vo.content }</td> <td>${vo.regDate }</td> <td>
					<!-- 각각 검은별, 흰별 찍기 -->
					<c:forEach begin="1" end="${vo.satisfaction }">
						<i class="bi bi-star-fill" style="color:orange;"></i>
					</c:forEach>
					<c:forEach begin="1" end="${5-vo.satisfaction }">
						<i class="bi bi-star" style="color:orange;"></i>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>