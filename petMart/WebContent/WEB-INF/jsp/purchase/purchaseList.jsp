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

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>


<body>
	<div align="center">
		<div class="m-3" style="width: 65%">
			<h3 class="m-4">주문내역</h3>
			
			
			<table class="table table-hover">
			<tr>
				<th>이미지</th>
				<th>상품명</th>
				<th>설명</th>
				<th>가격</th>
				<th>세일가</th>
				<th>수량</th>
			</tr>
			
			<c:if test="${empty list}">
			<tr>
			<th colspan="7">장바구니가 비었습니다.</th>
			</tr>
			</c:if>
			
			<c:if test="${!empty list}">
				<c:set var="sum" value="0"></c:set>
				<c:forEach items="${userCartList }" var="vo">
					<tr>
						<td><input type="hidden" id="id" value="${vo.userId }"></td>
						<td><img class="card-img-top" style="width:100px; height:80px;" src="upload/${vo.itemImage }" onerror="this.style.display='none';" /></td>
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
				</c:if>
			</table>
					<p class="sum">TOTAL : ${sum }</p>
					<button type="button" onclick="location.href='homePage.do'">홈</button>
					
					
		</div>
	</div>
</body>
</html>