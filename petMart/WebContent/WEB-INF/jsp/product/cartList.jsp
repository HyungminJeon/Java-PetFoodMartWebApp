<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		td, th{
			text-align:center;
			vertical-align:middle;
		}
		.sum{
			text-align:right;
			padding-right:30px;
		}
	</style>
</head>
<body>
	<div align="center">
		<div class="m-3" style="width: 65%">
			<h3 class="m-4">장바구니</h3>
			<table class="table table-hover">
			<tr>
				<th>이미지</th>
				<th>상품명</th>
				<th>설명</th>
				<th>가격</th>
				<th>세일가</th>
				<th>수량</th>
			</tr>
				<c:set var="sum" value="0"></c:set>
				<c:forEach items="${cartList }" var="vo">
					<tr>
						<td><img class="card-img-top" style="width:100px; height:80px;" src="upload/${vo.itemImage }" onerror="this.src='upload/no_image.png'" alt="..." /></td>
						<td>${vo.itemName }</td>
						<td>${vo.itemDesc }</td>
						<td>${vo.price }</td>
						<c:if test="${vo.sale eq 'Y'}">
							<td>${vo.salePrice }</td>
							<c:set var="sum" value="${sum + (vo.salePrice*vo.itemQty) }"></c:set>
						</c:if>
						<c:if test="${vo.sale ne 'Y'}">
							<c:set var="sum" value="${sum + (vo.price*vo.itemQty) }"></c:set>
							<td></td>
						</c:if>
						<td>${vo.itemQty }</td>
					</tr>
				</c:forEach>
			</table>
					<p class="sum">TOTAL : ${sum }</p>
		</div>
	</div>
		
</body>
</html>