<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>일정등록</title>

<!-- Bootstrap core CSS -->
<link href="/layout/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">
<!-- Bootstrap core JavaScript -->
<script src="/layout/jquery/jquery.min.js"></script>
<script src="/layout/bootstrap/js/bootstrap.bundle.min.js"></script>
<link href="/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<!-- bootstrap.min css -->
<link rel="stylesheet" href="/layout2/plugins/bootstrap/css/bootstrap.min.css">
<!-- Icon Font Css -->
<link rel="stylesheet" href="/layout2/plugins/themify/css/themify-icons.css">
<link rel="stylesheet" href="/layout2/plugins/fontawesome/css/all.css">
<link rel="stylesheet" href="/layout2/plugins/magnific-popup/dist/magnific-popup.css">
 <!-- Owl Carousel CSS -->
 <link rel="stylesheet" href="/layout2/plugins/slick-carousel/slick/slick.css">
<link rel="stylesheet" href="/layout2/plugins/slick-carousel/slick/slick-theme.css">

<!-- Main Stylesheet -->
<link rel="stylesheet" href="/layout2/css/style.css">

<script src="../jquery/jquery-3.1.1.min.js"></script> <!-- 값 제어를 위해 jquery -->
<link href="../jquery/datepicker/css/datepicker.min.css" rel="stylesheet" type="text/css" media="all">
<!-- Air datepicker css -->
<script src="../jquery/datepicker/js/datepicker.js"></script> <!-- Air datepicker js -->
<script src="../jquery/datepicker/js/i18n/datepicker.ko.js"></script> 

</head>
<body>



		<article>
		
		
            <div class="page-header" style="margin:auto">
                <div style="margin:auto;text-align:center">
                 <hr>
                <h3 style="font-weight:bold;">일정등록</h3>
                 <hr><br>
                </div>
            </div>
            <form name = "mform" id = "mform" method="post" action = "/calendar.do?cmd=insert">    
            <div class="col-sm-6 col-md-offset-3" style="margin:auto;">
           
                    <div class="form-group">
                        <label for="inputYear">일자</label>
                        <input type="text" class="form-control" id="datepicker" name = "year" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label for="inputContents">일정</label>
                        <input type="text" class="form-control" id="contents" name = "contents" >
                    </div>
                
                    <div class="form-group text-center">
                        <input type="button" class="btn btn-primary" value="등록" onclick="check_input()" style="font-weight:bold;" >                        
                      
                    </div>
              
            </div>
			</form>
        </article>
        
        <%@include file="/include/bottom.jsp" %>

</body>

<script type="text/javascript"> 

$("#datepicker").datepicker({
	language: 'ko'
}); 

function check_input() {
	
    if (!document.mform.datepicker.value)
    // 일자를 입력안하면 경고
    {
        alert("일자를 입력하세요!");
        document.mform.datepicker.focus();
        // 화면 커서 이동
        return;
    }
    if (!document.mform.contents.value)
    {
        alert("일정을 입력하세요!");
        document.mform.contents.focus();
        // 화면 커서 이동
        return;
    }
 

    document.mform.submit();
    // 모두 확인 후 submit()
   

    
 }



</script>


</html>

