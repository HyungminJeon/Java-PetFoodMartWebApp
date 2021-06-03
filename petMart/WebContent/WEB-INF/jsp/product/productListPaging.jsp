<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-modal/2.2.5/js/bootstrap-modal.js"></script>

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
	<script>
		
		function showInfo(itemCode){
			
			// 아래의 로직으로는 버튼을 누를 때마다 ajax 실행하여 받아온 값을 추가하므로, 제품 당 한번만 실행되도록 막을 필요가 있다
			var check = '#'+itemCode+'_infoChecker'; // == $('#item00n_infoCheck')
			if($(check).val() == 'false'){

				// Review를 가져올 ajax
				$.ajax({
					url:'showReview',
					type:'post',
					data:{
						itemCode:itemCode
					},
					dataType:'json',
					success:function(Reviews){
						var targetR = '#'+itemCode+'_infoBodyReviews'; // == $('#item00n_infoBodyReviews')
						console.log(Reviews);
						// 가져온 정보 출력
						var table = document.createElement('table');
						table.classList.add('table');
						table.classList.add('table-bordered');
						table.classList.add('table-hover');
						
						
						for(var i = 0; i < Reviews.length; i++){
							var tr = document.createElement('tr');
							var tdDate = document.createElement('td');
							tdDate.innerText = Reviews[i].regDate;
							var tdWriter = document.createElement('td');
							tdWriter.innerText = Reviews[i].writer;
							var tdContent = document.createElement('td');
							tdContent.innerText = Reviews[i].content;
							var tdSatisfaction = document.createElement('td');
							tdSatisfaction.innerText = Reviews[i].satisfaction;
							tr.append(tdDate, tdWriter, tdContent, tdSatisfaction);
							table.append(tr);
						}
						$(targetR).append(table);
						
					}
				});	
			
				// Question를 가져올 ajax
				$.ajax({
					url:'showQuestion',
					type:'post',
					data:{
						itemCode:itemCode
					},
					dataType:'json',
					success:function(Questions){
						var targetQ = '#'+itemCode+'_infoBodyQuestions'; // == $('#item00n_infoBodyQuestions')
						// console.log(Questions);
						var table = document.createElement('table');
						table.classList.add('table');
						table.classList.add('table-bordered');
						table.classList.add('table-hover');
						
						
						for(var i = 0; i < Questions.length; i++){
							var tr = document.createElement('tr');
							var tdDate = document.createElement('td');
							tdDate.innerText = Questions[i].regDate;
							var tdWriter = document.createElement('td');
							tdWriter.innerText = Questions[i].writer;
							var tdTitle = document.createElement('td');
							tdTitle.innerText = Questions[i].title;
							var tdContent = document.createElement('td');
							tdContent.innerText = Questions[i].content;
							
							tr.append(tdDate, tdWriter, tdTitle, tdContent);
							table.append(tr);
						}
						$(targetQ).append(table);
						
					}
				});
				
				$(check).val('true');
			
		}


			
	}
	</script>
	
	
	
<section class="py-5" style="align:center;">
	<div class="container px-4 px-lg-5 mt-5">
		<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
			<c:forEach items="${productList }" var="vo">
				<div class="col mb-5">
					<div class="card h-100">
                          <!-- Product image-->
                          <img class="card-img-top" style="width:300px; height:300px;" src="upload/${vo.itemImage }" onerror="this.style.display='none';" />
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
									<!-- Button trigger modal -->
									<button type="button" class="btn btn-outline-dark mt-auto" data-bs-toggle="modal" data-bs-target="#${vo.itemCode }_info" onclick="showInfo('${vo.itemCode }')">Show info</button>
									<input type="hidden" value="false" id="${vo.itemCode }_infoChecker">
									<!-- Show info Modal -->
									<div class="modal fade" id="${vo.itemCode }_info" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
									  <div class="modal-dialog modal-xl">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h4 class="modal-title" id="ModalLabel">${vo.itemName }</h4>
									        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									      </div>
									      <!-- modal body (detail Image) -->
									      <div class="modal-body m-2" style="text-align:left;" id="${vo.itemCode }_infoBodyDetails">
									      <h5>상세정보</h5>
									      	<!-- <img src="upload/${vo.detailImage }" onerror="this.style.display='none';" /> -->
									      	detailImage 영역 detailImage 영역 detailImage 영역 detailImage 영역 detailImage 영역 detailImage 영역 detailImage 영역 detailImage 영역 detailImage 영역 detailImage 영역 
									      </div>
									      <!-- modal body (reviews) -->
									      <div class="modal-body m-2" style="text-align:left;" id="${vo.itemCode }_infoBodyReviews">
									      <h5>상품평</h5>
									      </div>
									      <!-- modal body (questions) -->
									      <div class="modal-body m-2" style="text-align:left;" id="${vo.itemCode }_infoBodyQuestions">
									      <h5>문의사항</h5>
									      </div>
									      <!-- modal inner btns in footer-->
									      <div class="modal-footer">
									        <c:if test="${id != null }">
									        	<button type="button" class="btn btn-outline-primary">상품평 남기기</button>
									        	<button type="button" class="btn btn-outline-primary">문의사항 남기기</button>
									        	<a class="btn btn-outline-danger" onclick="addCnt('${vo.itemCode }')">♥</a>
									        </c:if>
										        <a class="btn btn-warning btn-outline-dark" onclick="addCnt('${vo.itemCode }')">Add to cart</a>
									      </div>
									    </div>
									  </div>
									</div>
									<!-- add to cart -->
									<a class="btn btn-warning btn-outline-dark" onclick="addCnt('${vo.itemCode }')">Add to cart</a>
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
		
		// 로그인한 경우
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
		
		// 게스트인 경우
		else {
			// 쿠키 없으면 생성
			if($.cookie('guestBasketId') == null){
				const ranNum = Math.random();
				$.cookie('guestBasketId', ranNum, {expires: 2});
				// 이틀 뒤에 파기. 지정하지 않으면 session cookie가 된다
				var guestId = $.cookie('guestBasketId');
				console.log('게스트 쿠키를 생성합니다...');
				$.ajax({
					url:'addCart.do',
					data: {
						guestId: guestId,
						itemCode: itemCode
					},
					success:function(result){
						console.log(result);
						location.href = 'productListPaging.do';
					}
				});
			} else { // 쿠키 있으면
				console.log('게스트 쿠키 존재...');
				var guestId = $.cookie('guestBasketId');
				$.ajax({
					url:'addCart.do',
					data: {
						guestId: guestId,
						itemCode: itemCode
					},
					success:function(result){
						console.log(result);
						location.href = 'productListPaging.do';
					}
				});
			}
		}
	}	
	
</script>