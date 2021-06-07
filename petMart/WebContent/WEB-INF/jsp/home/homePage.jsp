<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<script>
		function noticeFormSubmit(id){
			noticeFrm.id.value = id;
			noticeFrm.submit();
		}
		
		function bulletinFormSubmit(id){
			bulletinFrm.id.value = id;
			bulletinFrm.submit();
		}	
	</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<!-- 상품 좋아요 순 상위 5건 -->
	<div class="container col-md-12" style="text-align:center">
		
		<section class="py-5">
		<h3>HOT 5</h3>
	<div class="container px-4 px-lg-5 mt-5" style="text-align:center; " >
		<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
			<c:forEach items="${homepageProductList }" var="vo">
				<div class="col mb-5">
					<div class="card h-100">
                          <!-- Product image-->
                          <img class="card-img-top" style="width:300px; height:250px;" src="image/${vo.itemImage }" onerror="this.src='image/no_image.jpg';" />
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
						<c:if test="${not empty id }">
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<a class="btn btn-outline-dark mt-auto" id="addCnt" onclick="addCnt('${vo.itemCode }')">Add to cart</a>
							</div>
						</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</section>

		<!-- 공지사항 클릭 시 페이지 이동 -->
		<form id="noticeFrm" action="notice.do" method="post">
			<input type="hidden" id="id" name="id">
		</form>
		<!-- 공지사항 최신 순 상위 7건 -->
		<div class="container col-md-10">
			<div class="container col-md-10" style="text-align:center">
				<h3>NOTICE</h3>
				<table class="table table-hover">
					<tr>
						<td>제목</td>
						<td>작성일자</td>
					</tr>
					<c:forEach items="${homepageNoticeList }" var="vo">
						<tr onclick="noticeFormSubmit(${vo.id })">
							<td>${vo.title }</td>
							<td>${vo.regDate }</td>
						</tr>
					</c:forEach>
				</table>
			</div><hr>
			
			<!-- 자유게시판 클릭 시 페이지 이동 -->
			<form id="bulletinFrm" action="bulletinSelect.do" method="post">
				<input type="hidden" id="id" name="id">
			</form>
			<!-- 자유게시판 최신 순 상위 7건 -->
			<div class="container col-md-10" style="text-align:center">
				<h3>Free Board</h3>
				<table class="table table-hover">
					<tr>
						<td>제목</td>
						<td>작성일자</td>
					</tr>
					<c:forEach items="${homepageBulletinList }" var="vo">
						<tr onclick="bulletinFormSubmit(${vo.id })">
							<td>${vo.title }</td>
							<td>${vo.regDate }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	
	<script>
		function addCnt(itemCode){
			$.ajax({
				url:'addCart.do',
				data:{
					id:'${id }',
					itemCode:itemCode
				},
				success:function(result){
					location.href = 'homePage.do';
				},
				error:function(err){
					console.log(err);
				}
			});
		};	
	</script>
</body>
</html>