<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
function formCheck() {
	if (frm.memberId.value == "") {
		alert("아이디를 입력하세요.");
		frm.memberId.focus();
		return false;
	}
	if (frm.memberPwd.value == "") {
		alert("비밀번호를 입력하세요.");
		frm.memberPwd.focus();
		return false;
	}
	frm.submit();
}
</script>



<div align="center">
	<div>
		<h1>회원탈퇴</h1>
	</div>

	<div>
		<form id="frm" action="memberCancel.do" method="post">
			<div>
				<table border="1">
					<tr>
						<th width="150">아이디</th>
						<td width="300"><input type="text" id="memberId"
							name="memberId">
						</td>
					</tr>
					<tr>
						<th width="150">비밀번호</th>
						<td width="300"><input type="password" id="memberPwd"
							name="memberPwd"></td>
					</tr>
				</table>
			</div>
			<div>
				<button type="button" onclick="formCheck()">회원탈퇴</button>
				<button type="reset">취소</button>
				<button type="button" onclick="location.href='homePage.do'">홈</button>
			</div>
		</form>
	</div>

</div>