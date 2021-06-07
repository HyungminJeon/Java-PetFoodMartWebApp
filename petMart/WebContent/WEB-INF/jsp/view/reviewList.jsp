<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h5>상품평</h5>
<c:if test="${empty reviewList }">
	<h4 style="text-align:center;"> 첫번째로 리뷰를 남겨주세요 </h4>
</c:if>
<c:if test="${not empty reviewList }">
	<table class="table table-striped">
	<tr>
		<th>작성자</th> <th>내용</th> <th>작성일</th> <th></th> 
	</tr>
	<c:forEach items="${reviewList }" var="vo">
		<tr>
			<td>${vo.writer }</td> <td>${vo.content }</td> <td>${vo.regDate }</td> <td>
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
</c:if>