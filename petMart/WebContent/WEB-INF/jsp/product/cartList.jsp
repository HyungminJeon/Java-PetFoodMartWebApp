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

<script>
	//체크된 카트 삭제
	function deleteCart() {
		
            var checkboxes = document.getElementsByName('check');
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

<script>
//상품 결제
function purchase() {
	 var checkboxes = document.getElementsByName('check');
	 var id = document.getElementById('id').value;
	 var selected = new Array();
	 for (var i=0; i <checkboxes.length; i++) {
         if (checkboxes[i].checked) {
             selected.push(checkboxes[i].value);
         }
     }
	 
	 $.ajax({	
         url:"purchaseCart",
         type:"post",
         data: {purchaseList:selected, id:id },
         success:function(getId){
   			$.ajax({
   				url:'afterPurchaseCartCount.do',
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
	
IMP.init('imp49164212');

IMP.request_pay({
    pg : 'inicis', // version 1.1.0부터 지원.
    pay_method : 'card',
    merchant_uid : 'merchant_' + new Date().getTime(),
    name : checkedItems,
    amount : 100, //판매 가격
    buyer_email : 'iamport@siot.do',
    buyer_name : '구매자이름',
    buyer_tel : '010-1234-5678',
    buyer_addr : '서울특별시 강남구 삼성동',
    buyer_postcode : '123-456'
}, function(rsp) {
    if ( rsp.success ) {
        var msg = '결제가 완료되었습니다.';
        /* msg += '고유ID : ' + rsp.imp_uid;
        msg += '상점 거래ID : ' + rsp.merchant_uid;
        msg += '결제 금액 : ' + rsp.paid_amount;
        msg += '카드 승인번호 : ' + rsp.apply_num;  */
    } else {
        var msg = '결제에 실패하였습니다.';
        msg += '에러내용 : ' + rsp.error_msg;
    }
    alert(msg);
});
};
</script>

<script>
function loginToPurchase() {
	var logConfirm = confirm('로그인이 필요한 서비스입니다. 로그인하시겠습니까?');
	   if (logConfirm) {
		   location.href='memberLoginForm.do'
	   }
	   else {
	      alert('결제가 취소되었습니다.');
	   }
}
</script>


<body>
	<div align="center">
		<div class="m-3" style="width: 65%">
			<h3 class="m-4">장바구니</h3>
			<c:choose>
			
			
			<c:when test="${userCartList eq null}">
			
			<table class="table table-hover">
			<tr>
				<th>선택</th>
				<th>이미지</th>
				<th>상품명</th>
				<th>설명</th>
				<th>가격</th>
				<th>세일가</th>
				<th>수량</th>
			</tr>
			<c:if test="${empty guestCartList}">
			<tr>
			<th colspan="7">장바구니가 비었습니다.</th>
			</tr>
			</c:if>
			
			<c:if test="${!empty guestCartList}">	
				<c:set var="sum" value="0"></c:set>
				<c:forEach items="${guestCartList }" var="vo">
					<tr>
						<td><input type="checkbox" name="check" value=${vo.itemCode }></td>
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
					<button type="button" onclick="deleteCart()">삭제</button>
					<button type="button" onclick="loginToPurchase()">결제</button>
			</c:when>
			
			
			
			<c:otherwise>
			<table class="table table-hover">
			<tr>
				<th>선택</th>
				<th>이미지</th>
				<th>상품명</th>
				<th>설명</th>
				<th>가격</th>
				<th>세일가</th>
				<th>수량</th>
			</tr>
			
			<c:if test="${empty userCartList}">
			<tr>
			<th colspan="7">장바구니가 비었습니다.</th>
			</tr>
			</c:if>
			
			
			<c:if test="${!empty userCartList}">
				<c:set var="sum" value="0"></c:set>
				<c:forEach items="${userCartList }" var="vo">
					<tr>
						<td><input type="checkbox" name="check" value=${vo.itemCode }></td>
						
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
					<button type="button" onclick="deleteCart()">삭제</button>
					<button type="button" onclick="purchase()">결제</button>
					
			</c:otherwise>
			</c:choose>		
					
		</div>
	</div>



		
</body>
</html>