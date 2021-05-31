<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>bulletinListPaging</title>
	<style>
		th, td{
			text-align:center;
		}
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
		
		.caption{
			text-align: right;
			padding-right: 3%;
		}
		
	</style>
	<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
	<script>
		function formSubmit(bulletinId){
			frm.id.value = bulletinId;
			frm.submit();
		}
		function goPage(page){
			location.href = "bulletinListPaging.do?page=" + page;
		}
	</script>

</head>
<body>

	<form id="frm" action="bulletinSelect.do" method="post">
		<input type="hidden" id="id" name="id">
	</form>
	<div align="center">
		<div class="m-3" style="width: 65%">
			<h3 class="m-3">자유 게시판 리스트</h3>
			<table class="table table-bordered table-hover">
				<c:if test="${paging.pageNo != paging.finalPageNo }">
					<caption class="caption">${(paging.pageNo - 1) * 10 + 1 }-${paging.pageNo*10 }</caption>
				</c:if>
				<c:if test="${paging.pageNo == paging.finalPageNo }">
					<caption class="caption">${(paging.pageNo - 1) * 10 + 1 }-${paging.totalCount }</caption>
				</c:if>
				<thead class="thead-dark">
					<tr>
						<th scope="col" width="50">순번</th>
						<th scope="col" width="250">제목</th>
						<th scope="col" width="100">작성자</th>
						<th scope="col" width="100">작성일자</th>
						<th scope="col" width="50">조회수</th>
					</tr>				
  				</thead>
				<c:forEach items="${total }" var="vo">
					<tr onclick="formSubmit(${vo.id })">
						<td>${vo.id }</td>
						<td>${vo.title }</td>
						<td>${vo.writer }</td>
						<td>${vo.regDate }</td>
						<td>
							<c:forEach begin="1" end="${vo.hit }">
  								<i class="fas fa-heart" style="font-size:24px;color:red;"></i>
	 						</c:forEach>${vo.hit }
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="m-3">
			<button type="button" class="btn btn-secondary" onclick="location.href='homePage.do'">홈으로</button>
			<c:if test="${!empty id }">
				<button type="button" class="btn btn-info" onclick="location.href='bulletinForm.do'">등록</button>
			</c:if>
		</div>
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

</body>
</html>