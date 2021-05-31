<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
		function goPage(page){
			location.href = "productListPaging.do?page=" + page;
		}
	</script>
	<style>
		.pagination {
		  display: inline-block;
		}
		
		.pagination a {
		  color: black;
		  float: left;
		  padding: 8px 16px;
		  text-decoration: none;
		  transition: background-color .3s;
		  border: 1px solid #ddd;
		  border-radius: 5px;
		}
		
		.pagination a.active {
		  background-color: #4CAF50;
		  color: white;
		  border: 1px solid #4CAF50;
		  border-radius: 5px;
		}
		
		.pagination a:hover:not(.active) {background-color: #ddd;}
	</style>


<section class="py-5" style="align:center;">
	<div class="container px-4 px-lg-5 mt-5">
		<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
			<c:forEach items="${productList }" var="vo">
				<div class="col mb-5">
					<div class="card h-100">
                          <!-- Product image-->
                          <img class="card-img-top" style="width:300px; height:300px;" src="upload/${vo.itemImage }" onerror="this.src='upload/no_image.jpg'" alt="..." />
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
	<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
		<jsp:include page="../common/paging.jsp" flush="true">
		    <jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
		    <jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
		    <jsp:param name="startPageNo" value="${paging.startPageNo}" />
		    <jsp:param name="pageNo" value="${paging.pageNo}" />
		    <jsp:param name="endPageNo" value="${paging.endPageNo}" />
		    <jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
		    <jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
		</jsp:include>
	</div>
<script>
	function addCnt(itemCode){
		// e.preventDefault();
		if(${!empty id }){
			$.ajax({
				url:'addCart.do',
				data: {
					id: '${id }',
					itemCode: itemCode
					},
				success:function(result){
					console.log(result);
					location.href = 'productListPaging.do';
				}
			});
		}
	}	
	
</script>