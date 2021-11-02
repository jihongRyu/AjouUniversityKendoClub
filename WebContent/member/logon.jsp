<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">       -->    
        <title>로그인</title>
        <!-- Bootstrap -->
        <!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link href="layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
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
     <div class="wrapper bgded overlay" style="background-image:url('/images/logon.jpg');">
	   <div id="breadcrumb" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
	    <h6 class="heading">로그인</h6>
	   </div>
	 </div>

    <form name = "mform" id = "mform" method="post" action ="/index.do?cmd=member&job=logon">
        <article class="container" >
            <div class="page-header" >
                <div class="col-md-6 col-md-offset-3" style="margin:auto">
                 <hr><br>
                <h2 style="font-weight:bold;">로그인</h2>
                <br><hr><br>
                </div>
            </div>
            <div class="col-sm-6 col-md-offset-3" style="margin:auto" >
               
                    <div class="form-group">
                        <label for="inputUserid">아이디</label>
                        <input type="text" class="form-control" id="inputUserid" name = "userid" placeholder="아이디를 입력해 주세요">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">비밀번호</label>
                        <input type="password" class="form-control" id="inputPassword" name = "password"  placeholder="비밀번호를 입력해주세요">
                    </div>
                 	<br><br><hr>
                        
					<div class="form-group text-center">
					   <input type="button" id="join-submit" class="btn btn-primary" value="로그인" onclick="check_input()">
                       <a href = "/index.jsp">
                       <button type="button" class="btn btn-warning">
                          	  로그인 취소
                       </button>  
                       </a>       
                       <br><br><br>       
            		   <a class="logoname nav-link" href ="/index.do?cmd=member&job=findid"><span>아이디찾기</span></a>	
			 		   <a class="logoname nav-link" href ="/index.do?cmd=member&job=findpassword"><span>비밀번호찾기</span></a>
			 	   </div>  
                 </div>
               </article>
        </form>
        
        <%@include file="/include/bottom.jsp" %>
    </body>    
    
<script type="text/javascript">

function check_input() {
	
    if (!document.mform.userid.value)
    // login_form 이름을 가진 form 안의 id_val 의 value가 없으면
    {
        alert("아이디를 입력하세요!");
        document.mform.userid.focus();
        // 화면 커서 이동
        return;
    }
    
    if (!document.mform.password.value)
    {
        alert("비밀번호를 입력하세요!");
        // 화면 커서 이동
        return;
    }
    
    document.mform.submit();
    // 모두 확인 후 submit()
 }
</script>
    
    
</html>