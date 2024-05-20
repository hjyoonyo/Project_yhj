var iduse = false;
var pwuse = false;
var pwsame = false;
var repwuse = false;
var euse = false;
var edupl = false;
var isUpper;
var cnameuse;
	
$(function(){
	$("input[name=id]").keydown(function(){
		$("#idMsg").hide();
	});//idkeydown
	
	$("input[name=passwd]").keydown(function(){
		$("#pwMsg").hide();
	});//pwkeydown
	
})

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

function loginCheck(){
	if($("input[name=id]").val() == ""){
		alert("아이디를 입력하세요.");
		return false
	}
	if($("input[name=passwd]").val() == ""){
		alert("비밀번호를 입력하세요.");
		return false
	}
}//loginCheck


function registerCheck(){
	if($("input[name=id]").val() == ""){
		alert("아이디를 입력하세요.");
		$("input[name=id]").focus();
		return false;
	}
	if($("input[name=passwd]").val() == ""){
		alert("비밀번호를 입력하세요.");
		$("input[name=passwd]").focus();
		return false;
	}
	if($("input[name=repasswd]").val() == ""){
		alert("비밀번호 확인을 입력하세요.");
		$("input[name=repasswd]").focus();
		return false;
	}
	if($("input[name=day]").val() == ""){
		alert("생년월일을 입력하세요.");
		$("input[name=day]").focus();
		return false;
	}
	if($("input[name=email]").val() == ""){
		alert("이메일을 입력하세요.");
		$("input[name=email]").focus();
		return false;
	}

	if(isNaN($("input[name=day]").val())){
		alert("생년월일이 형식에 맞지 않습니다.");
		$("input[name=day]").select();
		return false;
	}
	
	if(!iduse){
		alert("입력한 아이디를 사용할 수 없습니다.");
		return false;
	}
	if(!pwuse){
		alert("비밀번호는 6~12자리 숫자+영문 조합으로 설정해야 합니다.");
		return false;
	}
	if(!pwsame){
		alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
		return false;
	}
	if(!edupl){
		alert("입력한 이메일은 사용할 수 없습니다.");
		return false;
	}
	if(!euse){
		alert("이메일이 형식이 적합하지 않습니다.");
		return false;
	}
	
}//registerCheck

function idCheck(){
	$("#idMsg").html("").show();
	
	if($("input[name=id]").val() == ""){
		$("#idMsg").html("<font color='red'>아이디를 입력하세요.</font>");
		iduse = false;
	}else{
		$.ajax({
			url : "id_check_proc.jsp",
			data : ({
				userid : $("input[name=id]").val()
			}),
			success : function(response){
				if($.trim(response) == "unusable"){
					$("#idMsg").html("<font color='red'>중복된 아이디입니다.</font>");
					iduse = false;
				}else if($.trim(response) == "usable"){
					$("#idMsg").html("사용 가능한 아이디입니다.");
					iduse = true;
				}
			}//success
		})//ajax
	}//else
}//idCheck

function pwCheck(){
	var pw = document.regiForm.passwd.value;
	var pwregex = /.{6,12}/ ;
	var check_num = pw.search(/[0-9]/);
	var check_eng = pw.search(/[a-zA-Z]/);
	var check_else = pw.search(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/);
	$("#pwMsg").html("").show();
	
	if($("input[name=passwd]").val() == ""){
		$("#pwMsg").html("<font color='red'>비밀번호를 입력하세요.</font>");
		pwuse = false;
		return false;
	}else if(pw.search(pwregex) == -1){
		$("#pwMsg").html("<font color='red'>비밀번호는 6~12자리로 설정해야 합니다.</font>");
		pwuse = false; 
		return false;
	}
	
	if(check_num < 0 || check_eng < 0){
		$("#pwMsg").html("<font color='red'>비밀번호는 숫자+영문 조합으로 설정해야 합니다.</font>");
		pwuse = false; 
		return false;		
	}

	if(check_else > 0){
		$("#pwMsg").html("<font color='red'>비밀번호는 숫자+영문만 사용 가능합니다.</font>");
		pwuse = false; 
		return false;		
	}

		pwuse = true; 
}//pwCheck

function repwCheck(){
	var repw = document.regiForm.repasswd.value;
	var pw = document.regiForm.passwd.value;
	
	if($("input[name=repasswd]").val() == ""){
		$("#repwMsg").html("<font color='red'>비밀번호 확인을 입력하세요.</font>");
	}else if(pw == repw){
		$("#repwMsg").html("<font color='blue'>비밀번호가 일치합니다.</font>").show();
		pwsame = true; 
	}else {
		$("#repwMsg").html("<font color='red'>비밀번호가 일치하지 않습니다.</font>").show();
		pwsame = false;
	}
}//repwCheck

function eCheck(){
	var email = document.regiForm.email.value;
	var checkEmail = email.search(/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
	$("#eMsg").html("");
	
	if($("input[name=email]").val() == ""){
		$("#eMsg").html("<font color='red'>이메일을 입력하세요.</font>");
	}else{
		$.ajax({
			url : "email_check_proc.jsp",
			data : ({
				userE : $("input[name=email]").val()
			}),
			success : function(response){
				if($.trim(response) == "unusable"){
					$("#eMsg").html("<font color='red'>중복된 이메일입니다.</font>");
					edupl = false;
				}else if($.trim(response) == "usable"){
					$("#eMsg").html("사용 가능한 이메일입니다.");
					edupl = true;
				}
			}//success
		})//ajax
	}//else
	
	if(checkEmail < 0){
		$("#eMsg").html("<font color='red'>이메일이 형식이 적합하지 않습니다.</font>");
		euse = false; 
		return false;		
	}
	euse = true;
}//eCheck

function findIdCheck(){
	
	if(document.findidForm.name.value == ""){
		alert("이름을 입력하세요.");
		document.findidForm.name.focus();
		return false;
	}
	if(document.findidForm.email.value == ""){
		alert("이메일을 입력하세요.");
		document.findidForm.email.focus();
		return false;
	}
}//findIdCheck

function findPwCheck(){
	
	if(document.findpwForm.id.value == ""){
		alert("아이디를 입력하세요.");
		document.findpwForm.id.focus();
		return false;
	}
	if(document.findpwForm.name.value == ""){
		alert("이름을 입력하세요.");
		document.findpwForm.name.focus();
		return false;
	}
	if(document.findpwForm.email.value == ""){
		alert("이메일을 입력하세요.");
		document.findpwForm.email.focus();
		return false;
	}
}//findIdCheck

function upperCheck(){
		$.ajax({
			url : "cname_check_proc.jsp",
			data : ({
				cname : $("input[name=cname_upper]").val()
			}),
			success : function(response){
				if($.trim(response) == "notDupl"){
					isUpper = false;
				}else if($.trim(response) == "isDupl"){
					isUpper = true;
				}
			}//success
		})//ajax
}

function cnameCheck(){
		$.ajax({
			url : "cname_check_proc.jsp",
			data : ({
				cname : $("input[name=cname]").val()
			}),
			success : function(response){
				if($.trim(response) == "notDupl"){
					cnameuse = true;
				}else if($.trim(response) == "isDupl"){
					$("input[name=cname]").select();
					cnameuse = false;
				}
			}//success
		})//ajax
}//cnameCheck

function cnameInputCheck(){
	
	if($("input[name=cname_upper]").val() == ""){
		alert("상위 카테고리를 입력하세요.");
		return false;
	}
	
	if($("input[name=cname]").val() == ""){
		alert("카테고리명을 입력하세요.");
		return false;
	}
	
	if($("input[name=cname_upper]").val() == "없음"){
		
	}else if(!isUpper){
		alert("입력한 상위 카테고리가 존재하지 않습니다.");
		return false;
	}
	
	if(!cnameuse){
		alert("이미 존재하는 카테고리입니다.");
		return false;
	}
}//cnameCheck