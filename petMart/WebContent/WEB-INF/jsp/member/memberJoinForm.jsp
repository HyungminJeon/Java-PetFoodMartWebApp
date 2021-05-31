<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js">
</script>



<script>
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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js">
</script>

<script>

	
	$(function() {
		$('#idCheck').click(function() {
			if($('#memberId').val()=="") {
				alert('아이디입력');
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
						alert('아이디가 존재합니다. 다른 아이디를 입력하세요');
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
	})

	function formCheck() {
		if (frm.memberId.value == "") {
			alert("아이디를 입력하세요.");
			frm.memberId.focus();
			return false;
		}
		if(frm.idCheck.value=='UnChecked'){
			alert("중복체크를 하세요.");
			return false;
		}
		
		if (frm.memberPwd.value == "") {
			alert("비밀번호를 입력하세요.");
			frm.memberPwd.focus();
			return false;
		}
		alert("정상적으로 회원가입 되었습니다")
		frm.submit();
	}
	
</script>

<div align="center">
	<div>
		<h1>회원가입</h1>
	</div>

	<div>
		<form id="frm" action="memberJoin.do" method="post">
			<div>
				<table border="1">
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
							name="memberPwd"></td>
					</tr>
					<tr>
						<th width="150">이름</th>
						<td width="300"><input type="text" id="memberName"
							name="memberName"></td>
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
			<div>
  
 
				<button type="button" onclick="formCheck()">회원가입</button>
				<button type="reset">취소</button>
				<button type="button" onclick="location.href='homePage.do'">홈</button>
			</div>
		</form>
	</div>

</div>