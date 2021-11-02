<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>아이디 찾기</title>
        <!-- Bootstrap -->
        <!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

		<!-- Popper JS -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
    <form name = "mform" id = "mform" method="post" action = "/index.do?cmd=member&job=logon">
        <article class="container" style="margin-top:30px">
            <div class="page-header">
                <div class="col-md-6 col-md-offset-3" style="margin:auto">
                 <hr><br>
                <h2 style="font-weight:bold;">아이디 찾기</h2>
                 <br>
                 <hr>
                 <br>
                </div>
            </div>
                 <div class="col-sm-6 col-md-offset-3"  style="margin:auto">
            <%
            	String userid=(String)request.getAttribute("userid");
            	if(userid==null||userid.equals("")){
            %>            
               <div class="form-group">
               <label for ="email">해당하는 아이디를 찾을 수 없습니다.</label>
               </div>
             <%}else{%>
            	 <div class="form-group">
                 <label for ="email">당신의 아이디는 <%=userid%> 입니다.</label>
                </div>      
             <%}%>
        
                 	<br><br><hr>
                          
					<div class="form-group text-center">
					<a href ="/index.do?cmd=member&job=logon_form">
                        <button type="button" id="btnFindId" class="btn btn-primary">
        					로그인<i class="fa fa-check spaceLeft"></i>
                        </button></a>
                   	<a href ="/index.do?cmd=member&job=findpassword">
                        <button type="button"  id="btnCancel" class="btn btn-warning">
                           	 비밀번호찾기<i class="fa fa-times spaceLeft"></i>
                        </button>
                        </a>
                    </div>
             
            </div>

        </article>
        </form>
    </body>
</html>