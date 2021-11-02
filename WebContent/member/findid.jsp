<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 

<title>아이디 찾기</title>
        <!-- Bootstrap -->
        <!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

		<!-- Popper JS -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


    </head>
    <body>
    <form name = "mform" id = "mform" method="post" action = "/index.do?cmd=member&job=logon">
        <article class="container" style="margin-top:30px">
            <div class="page-header">
                <div class="col-md-6 col-md-offset-3" style="margin:auto">
                 <hr><br>
                <h2 style="font-weight:bold">아이디 찾기</h2>
                 <br>
                 <hr>
                 <br>
                </div>
            </div>
            <div class="col-sm-6 col-md-offset-3"  style="margin:auto">
               
                  <div class="form-group">
                        <label for="email">이메일</label>
                        <input type="email" class="form-control" id="email" name ="email" placeholder="이메일을 입력해 주세요">
                    </div>
                    <div class="form-group">
                        <label for="phone">핸드폰번호</label>
                        <input type="tel" class="form-control" id="phone" name = "phone"  placeholder="핸드폰번호를 입력해주세요">
                    </div>
                 	<br><br><hr>
                          
					<div class="form-group text-center">
                        <button type="button" id="btnFindId" class="btn btn-primary">
                            확인<i class="fa fa-check spaceLeft"></i>
                        </button>
                        <a href = "/index.jsp">
                       <button type="button" class="btn btn-warning">
                          	취소
                       </button>  
                       </a>
                    </div>
             
            </div>

        </article>
        </form>
    </body>
</html>

<script>
	$(document).ready(function(){
		$("#btnFindId").click(function() {
			
			var frm = document.mform;
			frm.action="/index.do?cmd=member&job=findidresult"
			frm.submit();
		})
			$("#btnCancel").click(function() {
			var frm = document.mform;
			frm.action="/index.do"
		    frm.submit();
		})
	})
</script>