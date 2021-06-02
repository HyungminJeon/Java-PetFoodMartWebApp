<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="//cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>
	<script>
	// CKEDITOR.instances.content.getData() = name인지 id인지 뭔지 모르지만 가져옴 
		$(function(){ 
			CKEDITOR.replace('content', {
				filebrowserUploadUrl: '${pageContext.request.contextPath }/fileUpload', // 서블릿을 찾아가라
				height:'600px',
				width:'800px'
			});
		});
		
		function bulletinDelete() {
			deleteFrm.submit();
		}
		
		// 클릭 시에 대댓글창을 보여주는 메서드
		function showComment(commentId){
			var cid = commentId;
			
			// 클릭 부분의 display를 바꿔주기 전에 나머지를 전부 숨긴다
			var areaList = document.getElementsByName('addCommentsArea');
			areaList.forEach(function(item){
				item.style.display = "none";
			});
			
			// 만약에 클릭한 부분의 display가 none이면 show로 바꾸어라
			if(document.getElementById(cid).style.display == "none"){
				document.getElementById(cid).style.display = "block";
			}
			
		}
		
		// 새롭게 댓글을 작성하는 경우
		// 게시글의 번호, 내용, 작성자를 제외한 내용은 모두 java에서 처리한다
		function newComment(bid, id){
			var bid = bid;
			var ctt = document.getElementById('newCommentsArea').value;
			var writer = id;
			$.ajax({
				url:'addComment',
				type:'post',
				data:{
					bid:bid,
					writer:writer,
					content:ctt,
					want:'new'
				},
				success:function(result){
					console.log('입력됨');
					location.href='bulletinSelect.do?id='+bid; // 새로고침과 마찬가지
				}
			});
		}
		
		// 대댓글을 다는 경우
		// depth:몇번째 하위 댓글인가? 0이면 최상위 댓글. 대댓글이므로 읽어온 depth에 +1을 해준다
		// groupId:추후 댓글 삭제 시 cascade 삭제하기 위해 필요
		function addComment(commentId, bulletinId, groupId, writer, depth){
			console.log('엔터 테스트');
			var bid = bulletinId;
			var gid = groupId;
			var id = writer;
			var dth = depth+1;
			var ctt = document.getElementById(commentId).value;
			var cid = commentId;
			console.log(bid, gid, id, dth, ctt, cid);
			$.ajax({
				url:'addComment',
				type:'post',
				data:{
					bid:bid,
					gid:gid,
					writer:id,
					depth:dth,
					content:ctt,
					pid:cid,
					want:'add'
				},
				success:function(result){
					console.log('입력됨');
					location.href='bulletinSelect.do?id='+bid; // 새로고침과 마찬가지
				}
			});
		}	
			
		/* 
		$('#btnUpdate').click(function (e){
			e.preventDefault();
			console.log(CKEDITOR.instances.content.getData());
			
			let id = $('#id').val();
			let title = $('#title').val();
			let content = CKEDITOR.instances.content.getData();
			$.ajax({
				url: 'ajaxBulletinUpdate',
				data: {
					id:id,
					title:title,
					content:content
				},
				success: function(result){
					console.log(result)
					location.href = 'bulletinListPaging.do';
				},
				error: function(err){
					console.log(err);
				}
			});
		}) */
		
	</script>

</head>
<body>
	<div align="center">
		<div style="width: 50%">
			<h3> 상세 </h3>
			<form id="frm" action="bulletinUpdate.do" method="post">
				<table class="table" border="1">
					<tr>
						<th>순번</th>
						<td>${bulletin.id }</td>
						<th>작성자</th>
						<td id="id">${bulletin.writer }</td>
						<th>작성일자</th>
						<td>${bulletin.regDate }</td>
						<th>조회수</th>
						<td>${bulletin.hit }</td>
					</tr>
					<tr>
						<th>제목</th>
						<c:if test="${id == bulletin.writer }">
							<td colspan="7"><input type="text" name="title" value="${bulletin.title }"></td>
						</c:if>
						<c:if test ="${id ne bulletin.writer }">
							<td colspan="7">${bulletin.title }</td>
						</c:if>
						
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="7"><textarea rows="6" cols="90" name="content" id="content">${bulletin.content }</textarea></td>
					</tr>
				</table>
			</form>
				<!--  대댓글 창 기능 구현 -->
				<div align="left">
					<h5>댓글</h5>
						<c:forEach items="${commentsList }" var="vo">
							<div class="border" onclick="showComment(${vo.cid })">
								<c:if test="${vo.depth != 0 }">
									<c:forEach begin="1" end="${vo.depth }"><td>&rdca;</td></c:forEach>
								</c:if>
								${vo.writer }
								${vo.content }
								<!-- 버튼을 만들어보니 생각보다 불편해서, 버튼 없이 엔터만 치면 함수가 호출되도록 onkeypress을 사용. event.keyCode == 13이 enter이다. -->
								<c:if test="${id != null }">
									<textarea style="display:none;" rows="1" cols="70" name="addCommentsArea" id="${vo.cid }" onkeypress="javascript:if(event.keyCode==13)addComment(${vo.cid }, ${vo.bid }, ${vo.group_id }, '${id }', ${vo.depth })"></textarea>
								</c:if>
							</div>
						</c:forEach>
					<!-- 회원인 경우에만 댓글을 쓰도록 한다 -->
					<c:if test="${id != null }">
						<h6>새 댓글 쓰기</h6>
						<textarea rows="2" cols="75" id="newCommentsArea" onkeypress="javascript:if(event.keyCode==13)newComment(${bulletin.id }, '${id }')"></textarea>
					</c:if>
				</div>
				<div>
					<button type="button" onclick="location.href='bulletinListPaging.do'">목록 보기</button>
					<c:if test="${id == bulletin.writer }">
						<input type="hidden" name="id" value="${bulletin.id }">
						<button type="submit">수정</button>
						<button type="button" onclick="bulletinDelete()">삭제</button>
					</c:if>
				</div>
			<form id="deleteFrm" action="bulletinDelete.do" method="post">
				<input type="hidden" name="id" value="${bulletin.id }">
			</form>
			
		</div>
	</div>
</body>
</html>