<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container col-md-12">
		<h3>HOT</h3>
		<section class="py-5">
	<div class="container px-4 px-lg-5 mt-5">
		<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
			<div class="col mb-5">
				<div class="card h-100">
					<!-- Product image-->
					<img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
					<!-- Product details-->
					<div class="card-body p-4">
						<div class="text-center">
							<!-- Product name-->
							<h5 class="fw-bolder">Fancy Product</h5>
							<!-- Product price-->
							$40.00 - $80.00
						</div>
					</div>
					<!-- Product actions-->
					<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
						<div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>
					</div>
				</div>
			</div>
			<c:forEach items="${list }" var="vo">
				<div class="col mb-5">
					<div class="card h-100">
                          <!-- Product image-->
                          <img class="card-img-top" style="width:300px; height:250px;" src="upload/${vo.itemImage }" onerror="this.src='upload/no_image.png'" alt="..." />
                          <!-- Sale badge-->
                          <c:if test="${vo.sale eq 'Y'}">
                          	<div class="badge bg-warning text-white position-absolute" style="top: 0.5rem; right: 0.5em">Sale</div>
                          </c:if>
                          <!-- Product details-->
                          <div class="card-body p-4">
                              <div class="text-center">
                                  <!-- Product name-->
                                  <h5 class="fw-bolder">${vo.itemName }</h5>
                                  <!-- Product reviews-->
                                  <div class="d-flex justify-content-center small text-warning mb-2">
                                  		<c:forEach begin="1" end="${vo.likeIt }">
                                      		<div class="bi-star-fill"></div>
                                    	</c:forEach>
                                  </div>
                                  <!-- Product price-->
                                  <c:if test="${vo.sale eq 'Y'}">
	                                  <span class="text-muted text-decoration-line-through">
	                                  	<fmt:formatNumber type="currency" value="${vo.price }"/>
	                                  </span>
	                                  <fmt:formatNumber type="currency" value="${vo.salePrice }"/>
                                  </c:if>
                                  <c:if test="${vo.sale eq 'N' }">
                                  	<span><fmt:formatNumber type="currency" value="${vo.price }"/></span>
                                  </c:if>
							</div>
						</div>
						<!-- Product actions-->
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<a class="btn btn-outline-dark mt-auto" id="addCnt" onclick="addCnt('${vo.itemCode }')">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</section>
		
		
		
		
		
		
		
		
		
		
		
		
		<div class="container col-md-6">
			<div class="container col-md-6" style="text-align:center">
				<h3>NOTICE</h3>
				<table class="table">
					<tr>
						<td>제목</td>
						<td>작성일자</td>
					</tr>
					<c:forEach items="${noticeList }" var="vo">
						<td>vo.title</td>
						<td>vo.reg_date</td>
					</c:forEach>
				</table>
			</div><hr>
			<div class="container col-md-6" style="text-align:center">
				<h3>Free Board</h3>
				<table class="table">
					<tr>
						<td>제목</td>
						<td>작성일자</td>
					</tr>
					<c:forEach items="${bulletinList }" var="vo">
						<td>vo.title</td>
						<td>vo.reg_date</td>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>