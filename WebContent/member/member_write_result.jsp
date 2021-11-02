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
        <title>회원가입을 환영합니다</title>
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
    <form name = "mform" id = "mform" method="post" >
        <article class="container">
            <div class="page-header" >
                <div class="col-md-6 col-md-offset-3" style="margin:auto">
                 <hr><br>
                 <h2 style="font-weight:bold;text-align:center">회원가입을 환영합니다</h2>
                 <br>
                 <hr>
                 <br>
                </div>
                
                <!-- The image -->
				<div style="margin-top:15px">
				<img class="mx-auto d-block img-fluid" src="/images/mainicon.png" alt="business"
				style="width:500px;">
				</div>
                  <div  style="margin-top:5%;width:100%;text-align:center">          
                    
                       <a href = "/index.jsp">
                       <button type="button" class="btn btn-primary">
                          	  메인으로<i class="fa fa-check spaceLeft"></i>
                       </button>  
                       </a>     	
			
                  </div>
            </div>
          
                  

        </article>
        </form>
    </body>
</html>