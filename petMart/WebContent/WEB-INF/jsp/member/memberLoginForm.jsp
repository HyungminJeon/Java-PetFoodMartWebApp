<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>memberLoginForm.jsp</title>
<script>

function formCheck() {
	if(frm.memberId.value=="") {
		alert("아이디를 입력하세요.");
		frm.memberId.focus();
		return false;
	}
	if(frm.memberPwd.value=="") {
		alert("비밀번호를 입력하세요.");
		frm.memberPwd.focus();
		return false;
	}
	frm.submit();
}

</script>


	<div align = "center">
	<h1>로그인</h1>
		<form id="frm" action="memberLogin.do" method="post">
			<div>
				<table style="border:'1'; border-collapse:collapse;">
					<tr>
						<th>아이디</th>
						<td><input type="text" id="memberId" name="memberId">
						</td>
						<td rowspan="2" >
						<button type="reset" >지우기</button>
						</td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td><input type="password" id="memberPwd" name="memberPwd">
						</td>
					</tr>
				</table>
			</div>
			<div>
				<button type="button" onclick="formCheck()">로그인</button>
				
				<button type="button" onclick="location.href='homePage.do'">홈</button>
			</div>
		</form>
	</div>