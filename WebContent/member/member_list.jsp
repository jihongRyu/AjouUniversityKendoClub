<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
      <!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->    
        <title>회원정보</title>
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
     
     <div class="wrapper bgded overlay" style="background-image:url('/images/memberinfo.jpg');">
	   <div id="breadcrumb" class="hoc clear"> 
	    <h6 class="heading">회원정보</h6>
	   </div>
	 </div>
     
	  <br><br>
      <form name = "mform" id = "mform" method="post" action = "/index.do?cmd=member&job=logon">	                
      <div class="col-sm-6 col-md-offset-3" style="font-weight:bold;margin:auto">
        <br><br>
        <b><font size="6" color="BLACK"><%=username%>님의 회원 정보</font></b>        
        <br><br>
                       
        <table class="table table-striped">
            <tr>
             	<td id="title">아이디</td>
           		<td><%=userid%>               
            </tr>               
            <tr>
            <td id="title">이름</td>
             <%if(username == null){%>
			 		<td>이름 정보가 없습니다.</td>			  			             
			<%} else { %>
					<td><%=username%></td>	
			<%} %>      
            </tr>   
            <tr>
            <td id="title">학번</td>
            <%if(schoolnum == null ){%>
			 		<td>학번정보가 없습니다.</td>			  			             
			<%} else { %>
					<td><%=schoolnum%></td>	
			<%} %>            
            </tr>   
            <tr>
            <td id="title">학과</td>
            <%if(schoolsub == null ){%>
			 		<td>학과정보가 없습니다.</td>			  			             
			<%} else { %>
					<td><%=schoolsub%></td>	
			<%} %>
			</tr>   
            <tr> 
			<td id="title">입부년도</td>
            <%if(enteryear == null ){%>
			 		<td>입부년도정보가 없습니다.</td>			  			             
			<%} else { %>
					<td><%=enteryear%></td>	
			<%} %>              
            </tr>                               
            <tr>
            <td id="title">핸드폰</td>
            <%if(phone == null ){%>
			 		<td>핸드폰정보가 없습니다.</td>			  			             
			<%} else { %>
					<td><%=phone%></td>	
			<%} %>            
            </tr>                    
            <tr>
            <td id="title">이메일</td>
            <%if(email == null ){%>
			 		<td>이메일정보가 없습니다.</td>			  			             
			<%} else { %>
					<td><%=email%></td>	
			<%} %>
            </tr>                    
            <tr>
            <td id="title">주소</td>
            <%if(address1 == null ){%>
			 		<td>주소정보가 없습니다.</td>			  			             
			<%} else { %>
					<td><%=address1%></td>	
			<%} %>			 	
            </tr>
            <tr>
            <td id="title">회원등급</td>
            <%if( level == 1){%>
			 		<td>일반회원</td>			  			             
			<%} else if(level == 2) { %>
					<td>관리자</td>	
			<%} else if(level == 9) { %>
					<td>예비회원</td>	
			<%} else { %>
					<td>회원등급정보가 없습니다.</td>	
			<%} %>			 	
            </tr>
           
        </table>

        <font size="2" color="BLACK">정보 수정은 관리자에게 문의해주세요.</font>
        <br><br>
        <a href =/index.do><input type="button" class="btn btn-success" value="뒤로"></a>
        <%-- <button type="button" id="btnUpdate" class="btn btn-primary">정보수정하기</button> --%>
        <button type="button" id="btnDelete" class="btn btn-primary">회원탈퇴하기</button> 
		
    </div>
   </form>
          
    </body>
    
    <%@include file="/include/bottom.jsp" %>
</html>

<script>

$(document).ready(function(){
	$("#btnDelete").click(function() {
		
		var frm = document.mform;
		frm.action="/index.do?cmd=member&job=delete"		
		frm.submit();
		alert('회원탈퇴가 완료되었습니다.');
	})
		
})

$(document).ready(function(){
	$("#btnUpdate").click(function() {
		
		var frm = document.mform;
		frm.action="/member/memberUpdate.jsp"		
		frm.submit();		
	})
		
})

</script>