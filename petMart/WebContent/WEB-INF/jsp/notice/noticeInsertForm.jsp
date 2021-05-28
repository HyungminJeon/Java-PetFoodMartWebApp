<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
</script>
<div align="center">
	<!-- 타이틀 , 내용-->
	
	
	
	<form id="frm" action="noticeInsert.do">
		<table class="table" border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="6" cols="90" name="content"></textarea></td>
			</tr>
		</table>
		<button type="submit">작성</button>
		<button type="reset">초기화</button>
	</form>
</div>