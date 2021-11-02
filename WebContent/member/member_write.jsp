<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
      <!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
        <title>회원가입</title>
        <!-- Bootstrap -->
        <!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
       

		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

		<!-- Popper JS -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link href="/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">

        


    </head>
    <body>
    
    <%@include file="/include/top.jsp" %>
    
     <div class="wrapper bgded overlay" style="background-image:url('/images/memberwrite.jpg');">
	   <div id="breadcrumb" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
	    <h6 class="heading">회원가입</h6>
	   </div>
	 </div>
	 
    
    <form name = "mform" id = "mform" method="post" action = "/index.do?cmd=member&job=insert">    
    
        <article class="container">
            <div class="page-header" style="margin:auto">
                <div class="col-md-6 col-md-offset-3" style="margin:auto">
                 <hr><br>
                <h3 style="font-weight:bold;">검도부 회원가입</h3>
                 <br><hr><br>
                </div>
            </div>
            <div class="col-sm-6 col-md-offset-3" style="margin:auto">
           
                  <div class="form-group">
                        <label for="inputUserid">아이디</label>
                        <input type="text" class="form-control" id="userid" name = "userid" placeholder="아이디를 입력해 주세요">
                        <br>
                        <input type = "button" class="btn btn-primary" id="idCheck" style="float:right" onclick="goIdCheck()" value="아이디 중복체크" />
                        <br>
                    </div>
                    <div class="form-group">
                        <label for="inputName">성명</label>
                        <input type="text" class="form-control" id="inputName" name = "username"  placeholder="이름을 입력해 주세요">
                    </div>
                    <div class="form-group">
                        <label for="inputName">학번</label>
                        <input type="text" class="form-control" id="inputSchoolnum" name = "schoolnum"  placeholder="학번을 입력해 주세요">
                    </div>
                    <div class="form-group">
                        <label for="inputName">학과</label>
                        <input type="text" class="form-control" id="inputSchoolsub" name = "schoolsub"  placeholder="학과을 입력해 주세요">
                    </div>
                    <div class="form-group">
                        <label for="inputName">입부년도(ex.2011)</label>
                        <input type="text" class="form-control" id="inputEnteryear" name = "enteryear"  placeholder="입부년도를 입력해 주세요">
                    </div>
                    <div class="form-group">
                        <label for="InputEmail">이메일 주소</label>
                        <input type="email" class="form-control" id="InputEmail" name = "email"  placeholder="이메일 주소를 입력해주세요">
                    </div>
                    <div class="form-group">                    
   					<label>비밀번호</label>
   					<input type="password" class="form-control" id="inputPassword" name = "password"  placeholder="비밀번호를 입력해주세요">
   					<font name="pwdlengthcheck" size="2" color="red"></font> 
  					</div>
  					<div class="form-group">
   					<label>비밀번호확인</label>   					
    				<input type="password" class="form-control" id="inputPasswordCheck" name = "password1" placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요">
    				<font name="check" size="2" color="red"></font> 
   					</div>
					
                    <div class="form-group">
                        <label for="inputMobile">휴대폰 번호</label>
                        <input type="tel" class="form-control" id="inputMobile" name = "phone"  placeholder="휴대폰번호를 입력해 주세요">
                    </div>
                    <div class="form-group">
                        <label for="inputtelNO">우편번호</label>
                        <input type="text" class="form-control" readonly
                       		id ="zipcode" name = "zipcode"  placeholder="우편번호를 입력해 주세요">
                        <br>
                        <input type = "button" id ="btnZipcode" class = "btn btn-primary" onclick="goPostcode()" value = "우편번호검색"/>
                    </div>
                     <div class="form-group">
                        <label for="inputtelNO">주소</label>
                        <input type="text" class="form-control"  readonly name = "address1" id= "address1" placeholder="주소를 입력해 주세요">
                    </div>
                        <div class="form-group">
                        <label for="inputtelNO">상세 주소</label>
                        <input type="text" class="form-control" id="address2" name = "address2"  placeholder="상세주소를 입력해 주세요">
                    </div>
                    <div class="form-group" style="padding:10px">
                    <input type="checkbox" style="white-space:nowrap;float:left" id="bbb" name="abcd" value="10" onchange="setDisplay()"><span style=" position: relative;top: -5.5px;">관리자로 가입하기</span>
                    </div>
                    <div class="form-group" id="manager">
                        <label for="manager">관리자 가입용 비밀번호를 입력해 주세요.</label>
                        <input type="text" class="form-control" id="level" name = "level"  placeholder="관리자 가입용 비밀번호를 입력해 주세요.">
                    </div>
					<br><hr>    
					<br>
                    <div class="form-group text-center">
                        <input type="button" class="btn btn-primary" value="가입하기" onclick="check_input()">                        
                        <a href = "/index.do">
                        <button type="button" class="btn btn-warning">
                          	  회원가입취소
                       </button>  
                       </a>
                    </div>
              
            </div>

        </article>
        </form>     
    </body>
    
    <%@include file="/include/bottom.jsp" %>
        
</html>

<!-- JAVASCRIPTS -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- 다음이 서비스하는 우편번호를 이용하기 위한 자바스크립트 라이브러리 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
        
        
<script>

$('#manager').hide();


//비밀번호 길이체크 함수.
 $(function(){
  $('#inputPassword').keyup(function(){
   $('font[name=pwdlengthcheck]').text('');
   if($('#inputPassword').val().length<5){
   $('font[name=pwdlengthcheck]').text('');
   $('font[name=pwdlengthcheck]').html("비밀번호가 너무 짧습니다. 5자이상으로 만들어주세요.");
   }else if($('#inputPassword').val().length>9){
	   $('font[name=pwdlengthcheck]').text('');
	   $('font[name=pwdlengthcheck]').html("비밀번호가 너무 깁니다. 9자 이하으로 만들어주세요.");
	   } else {
	  	 $('font[name=pwdlengthcheck]').text('');
	    $('font[name=pwdlengthcheck]').html("이 비밀번호는 사용가능합니다.");
	   }   
  }); //#user_pass.keyup
 });
  
 
//비밀번호 중복체크 함수.
 $(function(){
  $('#inputPassword').keyup(function(){
   $('font[name=check]').text('');
  }); //#user_pass.keyup
  
  $('#inputPasswordCheck').keyup(function(){
   if($('#inputPassword').val()!=$('#inputPasswordCheck').val()){
    $('font[name=check]').text('');
    $('font[name=check]').html("비밀번호가 서로 일치하지않습니다.");
   }else{
    $('font[name=check]').text('');
    $('font[name=check]').html("비밀번호가 일치합니다.");
   }
  }); //#chpass.keyup
 });
 

function goIdCheck(){
	

	var frm = document.mform;	
	var userid = $("#userid").val();
	var idCheck = $("#idCheck").val();
	
	console.log("userid:" + userid);
	$.ajax({
		url: "/index.do", //호출할 파일명
		data:{'cmd': 'member','job': 'idCheck','userid': userid},
		method: "POST",
		dataType: "json", //내가 받아야할 결과 형태가 text, html, xml, json
		success: function(data){
			console.log(data);
			if(frm.userid.value.length<4) {
				alert("아이디가 짧습니다. 4자 이상으로 적어주세요");
				frm.idCheck.focus(); //포커스 이동
				return false;
				
			}else {
			if (data.idcheck==true) {
				$("#idcheck").val('Y');				
				$("#userid").prop('readonly', true); // 아이디 유무 확인후 가입가능하면 변경못하도록 막는 역할	
				$("#userid").attr('readonly', true);
				alert("사용가능한 아이디입니다.");	
			} else {
				$("#idcheck").val('N');		
				alert("아이디가 중복됩니다. 다른 아이디를 사용해주세요.");		
			}
		
			}
		},
		error:function(){
			console.log("데이터 로딩 실패");
		}
	});
	}
	
	//우편번호를 위한 함수
	
	 function goPostcode() {
        new daum.Postcode({
        	oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode;
                document.getElementById('address1').value = roadAddr;                
     
            }
        }).open();
        
        
        
    }
	
	 function check_input() {
		    if (!document.mform.userid.value)
		    // login_form 이름을 가진 form 안의 id_val 의 value가 없으면
		    {
		        alert("아이디를 입력하세요!");
		        document.mform.userid.focus();
		        // 화면 커서 이동
		        return;
		    }
		    if (!document.mform.username.value)
		    {
		        alert("성명을 입력하세요!");
		        // 화면 커서 이동
		        return;
		    }
		    if (!document.mform.email.value)
		    {
		        alert("이메일을 입력하세요!");
		        // 화면 커서 이동
		        return;
		    }
		    if (!document.mform.password.value)
		    {
		        alert("비밀번호를 입력하세요!");
		        // 화면 커서 이동
		        return;
		    }
		    if (!document.mform.password1.value)
		    {
		        alert("비밀번호를 입력하세요!");
		        // 화면 커서 이동
		        return;
		    }
		    if (!document.mform.phone.value)
		    {
		        alert("휴대폰번호를 입력하세요!");
		        // 화면 커서 이동
		        return;
		    }
		    if (!document.mform.address1.value)
		    {
		        alert("주소를 입력하세요!");
		        // 화면 커서 이동
		        return;
		    }
		    document.mform.submit();
		    // 모두 확인 후 submit()
		 }
	
	 function setDisplay(){
		 
		    if($('input:checkbox[id=bbb]').is(':checked')){
		    	 
		    	$('#manager').show();
		    
			}	else {
				
				$('#manager').hide();
				
			}
	}


	

</script>