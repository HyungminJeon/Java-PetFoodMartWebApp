<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<%@ page import="com.petMart.member.serviceImpl.MemberServiceImpl"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"> //주소입력 스크립트
</script>

<script>

$(function() {
		$('#sendEmail').click(function() {

			if ($('#email').val() == "") {
				alert('이메일을 입력하세요.');
				$('#email').focus();
				return false;
			}
			//email 중복확인 ajax
			$.ajax({
				url : 'ajaxEmailCheck',
				data : {
					email : $('#email').val()
				},
				type : 'post',
				success : function(data) {
					console.log(data);
					if (data > 0) {
						alert('등록된 이메일이 존재합니다. 새로운 이메일을 입력하세요');
						$('#email').val('');
						$('#email').focus();
					} else {
						alert('사용가능한 이메일입니다!');
						$('#sendEmail').val('checked');
						$('#emailCode').focus();
						//중복확인 통과후 인증코드 메일보내는 ajax
						$.ajax({
							url : 'ajaxSendEmail',
							data : {
								email : $('#email').val()
							},
							type : 'post',
							success : function(code) {
								console.log(code);
								alert('메일이 전송되었습니다.');
								$('#checkEmail').click(function() { // 성공해서 이메일에서 값을 건네받은 경우에, 인증번호 버튼을 클릭 시 값을 검사
									if ($('#emailCode').val() == code) { // 사용자의 입력값과 sendSMS에서 받은 값이 일치하는 경우
										alert('인증되었습니다');
										frm.checkEmail.value = 'checked';
									} else {
										alert('인증번호가 틀립니다');
									}
								})
							},
							error : function(err) {
								console.log(err);
							}
						});
					}
				},
				error : function(err) {
					console.log(err);
				}
			});
		});
		
		
		
	});
</script>

<script>
	//아이디 중복체크
	$(function() {
		$('#idCheck').click(function() {
			if($('#memberId').val()=="") {
				alert('아이디를 입력하세요.');
				$('#memberId').focus();
				return;
			}
			$.ajax({
				url:'ajaxMemberIdCheck',
				data: {id: $('#memberId').val()},
				type: 'post',
				success: function(data){
					console.log(data);
					if(data>0) {
						alert('등록된 아이디가 존재합니다. 새로운 아이디를 입력하세요');
						$('#memberId').val('');
						$('#memberId').focus();
					} else{
						alert('사용가능한 아이디입니다!');
						$('#idCheck').val('checked');
						$('#memberPwd').focus();
					}
				},
				error: function(err){
					console.log(err);
				}
			});
		});
	});
</script>

<script>
//주소입력
function findAddr(){
	new daum.Postcode({
        oncomplete: function(data) {
        	
        	console.log(data);
        	
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('member_post').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("member_addr").value = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("member_addr").value = jibunAddr;
            }
        }
    }).open();
}
</script>

<script>
	//휴대폰 번호입력 ajax
	$(function() {
		$('#sendSMS').click(function() { // 클릭하면 인증 번호 보내기
			var tel = $('#tel').val(); // 인증번호를 보낼 사용자가 입력한 tel

			if (tel == "") {
				alert('휴대폰번호를 입력하세요.');
				$('#tel').focus();
				return false;
			}
			
			//휴대폰번호 중복확인 ajax
			$.ajax({
				url : 'ajaxTelCheck',
				data : {
					tel : tel
				},
				type : 'post',
				success : function(data) {
					console.log(data);
					if (data > 0) {
						alert('등록된 휴대폰번호가 존재합니다. 새로운 휴대폰번호를 입력하세요');
						$('#tel').val('');
						$('#tel').focus();
					} else {
						alert('사용가능한 휴대폰번호입니다!');
						$('#sendSMS').val('checked');
						$('#smsKey').focus();
						
						//사용가능한 휴대폰일때 인증번호 전송 ajax
						if (tel != null) {
							$.ajax({
								url : 'sendSMS',
								data : {
									tel : tel
								},
								type : 'post',
								success : function(code) {
									alert('인증번호가 전송되었습니다');
									$('#checkSMS').click(function() { // 성공해서 sendSMS에서 값을 건네받은 경우에, 인증번호 버튼을 클릭 시 값을 검사
										if ($('#smsKey').val() == code) { // 사용자의 입력값과 sendSMS에서 받은 값이 일치하는 경우
											alert('인증되었습니다');
											frm.checkSMS.value = 'checked';
										} else {
											alert('인증번호가 틀립니다');
										}
									});
								}
							});
						}
					}
				},
				error : function(err) {
					console.log(err);
				}
			});
		});
	});
	function formCheck() {
		if (frm.memberId.value == "") {
			alert("아이디를 입력하세요.");
			frm.memberId.focus();
			return false;
		}
		if (frm.idCheck.value == 'UnChecked') {
			alert("ID 중복체크를 하세요.");
			return false;
		}
		if (frm.memberPwd1.value == "") {
			alert("비밀번호를 입력하세요.");
			frm.memberPwd.focus();
			return false;
		}

		if (frm.memberPwd1.value != frm.memberPwd2.value) {
			alert("비밀번호를 재확인하세요.");
			frm.memberPwd.focus();
			return false;
		}
		if (frm.memberName.value == "") {
			alert("이름을 입력하세요.");
			return false;
		}
		if (frm.email.value == "") {
			alert("이메일을 입력하세요.");
			return false;
		}
		if (frm.tel.value == "") {
			alert("휴대폰번호를 입력하세요.");
			return false;
		}
		if (frm.member_post.value == "") {
			alert("주소를 입력하세요");
			frm.smsKey.focus();
			return false;
		}
		/* if (frm.checkEmail.value == "unChecked") {
			alert("이메일을 인증 하세요");
			frm.emailCode.focus();
			return false;
		}  */
		if (frm.checkSMS.value == "unChecked") {
			alert("문자 인증을 하세요");
			frm.smsKey.focus();
			return false;
		} 
		alert("정상적으로 회원가입 되었습니다");
		frm.submit();
	}
</script>

<div  align="center">
	<div>
		<h1>회원가입</h1>
	</div>
<br>
	<div >
		<form id="frm" action="memberJoin.do" method="post" >
			<div>
				<table style="border:'1'; border-collapse:collapse;">
					<tr>
						<th width="150">아이디</th>
						<td width="300">
							<input type="text" id="memberId" name="memberId">
							<button type="button" id="idCheck" value="unChecked">중복체크</button>
						</td>
					</tr>
					<tr>
						<th width="150">비밀번호</th>
						<td width="300"><input type="password" id="memberPwd"
							name="memberPwd1"></td>
					</tr>
					<tr>
						<th width="150">비밀번호 재확인</th>
						<td width="300"><input type="password" id="memberPwd"
							name="memberPwd2"></td>
					</tr>
					<tr>
						<th width="150">이름</th>
						<td width="300"><input type="text" id="memberName"
							name="memberName"></td>
					</tr>
					
					<tr><td colspan="2">&nbsp;</td></tr>
					<tr>
						<th>이메일</th>
						<td width="350">
 						<input type="text" placeholder="ex) kim1@naver.com" id="email" name="email" value="">
 						<button type="button" id="sendEmail" value="unChecked">인증코드 전송</button>
 						</tr>
 						<tr>
 						<th></th>
 						<td width="350">
 						<input type="text" placeholder="인증코드를 입력하세요" id="emailCode" value="">
 						<button type="button" id="checkEmail" value="unChecked">인증코드 확인</button>
						</td>
					</tr>
					
					<tr><td colspan="2">&nbsp;</td></tr>
					
					<tr>
						<th width="150">전화번호</th>
						<td width="350"><input type="text" id="tel"
							name="tel" placeholder ="ex) 01029532154" >
							<button type="button" id="sendSMS" value="unChecked">인증번호 전송</input>
						</td>
					</tr>
					<tr>
						<th>인증 번호</th>
						<td width="350">
 						<input type="text" placeholder="인증번호를 입력하세요" id="smsKey" value="">
 						<button type="button" id="checkSMS" value="unChecked">인증번호 확인</button>
						</td>
					</tr>
					<tr><td colspan="2">&nbsp;</td></tr>
					<tr>
						<th width="150">주소
						</th>
						<td width="300">
						<input id="member_post"  type="text" name="memberAddressZip" placeholder="Zip Code" readonly onclick="findAddr()">
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
  						<input id="member_addr" type="text" name="memberAddress" placeholder="Address" readonly> <br>
 						<input type="text" placeholder="Detailed Address" name="memberAddressDetail">
						</td>
					</tr>
					
				</table>
			</div>
			<br>
 			<br>
			<div>
				<button type="button" onclick="formCheck()">가입하기</button>
				<button type="button" onclick="location.href='homePage.do'">홈</button>
			</div>
		</form>
		<form action="">
			
		</form>
	</div>

</div>