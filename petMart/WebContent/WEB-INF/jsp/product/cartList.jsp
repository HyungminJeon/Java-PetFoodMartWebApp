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

<script>
	//체크된 카트 삭제
	function deleteCart() {
		
            var checkboxes = document.getElementsByName('delete');
            var id = document.getElementById('id').value;
            var selected = new Array();
            for (var i=0; i <checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    selected.push(checkboxes[i].value);
                }
            }

                $.ajax({	
                    url:"deleteCart",
                    type:"post",
                    data: {deleteList:selected, id:id },
                    success:function(getId){
                      //alert("해당 상품이 장바구니에서 제외되었습니다.");
              			$.ajax({
              				url:'afterDeleteCartCount.do',
              				data: {
              					getId: getId,
              				},
              				success:function(){
              					location.href="cartList.do";
              				}
              			});
              		},
                    
                    error:function(){
                    	console.error();
                    }
                });
   	     }
</script> 



<body>
	<div align="center">
		<div class="m-3" style="width: 65%">
			<h3 class="m-4">장바구니</h3>
			<c:choose>
			
			<%-- <c:when test="${cartList.isEmpty()}" >
			<h6 class="m-4">장바구니가 비었습니다.</h6>
			</c:when> --%>
			
			<c:when test="${userCartList eq null}">
			
			<table class="table table-hover">
			<tr>
				<th>삭제</th>
				<th>이미지</th>
				<th>상품명</th>
				<th>설명</th>
				<th>가격</th>
				<th>세일가</th>
				<th>수량</th>
				
			</tr>
				<c:set var="sum" value="0"></c:set>
				<c:forEach items="${guestCartList }" var="vo">
					<tr>
						<td><input type="checkbox" name="delete" value=${vo.itemCode }></td>
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
			</table>
			</c:when>
			
			
			
			<c:otherwise>
			<table class="table table-hover">
			<tr>
				<th>삭제</th>
				<th>이미지</th>
				<th>상품명</th>
				<th>설명</th>
				<th>가격</th>
				<th>세일가</th>
				<th>수량</th>
				
			</tr>
				<c:set var="sum" value="0"></c:set>
				<c:forEach items="${userCartList }" var="vo">
					<tr>
						<td><input type="checkbox" name="delete" value=${vo.itemCode }></td>
						
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
			</table>
			</c:otherwise>
			</c:choose>		
					<p class="sum">TOTAL : ${sum }</p>
					<button type="button" onclick="deleteCart()">삭제</button>
		</div>
	</div>



		
</body>
</html>