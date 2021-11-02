<%@ page language="java"   contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.multi.model.*" %>
<%@page import="com.multi.dto.*" %>
<%@page import="com.multi.common.*" %>
<%@page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 



  <title>역대부원</title>


 
<!-- Menu Toggle Script -->
<script>
  $("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
  });
 </script>
 
</head>
<body>
<%
	//파라미터 처리하기 
	String contextPath=request.getContextPath();
	String mode=Util.nullToValue(request.getParameter("mode"), "insert");

	MemberManageDto dto = (MemberManageDto)request.getAttribute("dto");
	
	if( dto==null) dto = new MemberManageDto();

	int totalCount;

	if(request.getAttribute("totalCount") !=  null){
		  totalCount = (int)request.getAttribute("totalCount");
	}else{
		  totalCount = 0;
	}

	%>

<%@include file="/include/top.jsp" %>

<div class="wrapper bgded overlay" style="background-image:url('/images/memberlist.jpg');">
  <div id="breadcrumb" class="hoc clear"> 
   <h6 class="heading">역대부원</h6>
  </div>
</div>


<div style="margin-left:10%; margin-right:10%; ">

<br><br><br><br>

<div style="margin:30px">
   <div class="container-fluid"  >
	 <h2>부원등록</h2>
	 <form name="myform" id = myform method="post"  action ="/memberList.do?cmd=insert">
	 	
	 <table class="table table-striped" style="text-align:center">
     <thead class="thead-dark">     
      <colgroup>
		<col width="10%">
		<col width="10%">
		<col width="10%">
		<col width="15%">
		<col width="*">
	</colgroup>
      
      <tr class="thead-dark">
        <th>성명</th>
        <th>학번</th>
        <th>학과</th>
        <th>입부년도</th>
        <th>비고</th>
      </tr>
     </thead>   
     <tbody>
  	           <tr>  	           	
	             <td><input type="text" id="username" name="username"  class="form-control" style="width:100%;border:none;"  placeholder="이름을 입력하세요"></td>
	             <td><input type="text" id="schoolnum" name="schoolnum" class="form-control" style="width:100%;border:none;" placeholder="학번을 입력하세요"></td>
	             <td><input type="text" id="schoolsub" name="schoolsub" class="form-control" style="width:100%;border:none;" placeholder="학과을 입력하세요"></td>
	             <td><input type="text" id="enteryear" name="enteryear" class="form-control" style="width:100%;border:none;" placeholder="입부년도를 입력하세요"></td>
	             <td><input type="text" id="etc" name="etc" class="form-control" style="width:100%;border:none;"   placeholder="기타사항을 입력하세요"></td>
	            </tr>     
    </tbody>    
  </table>


	   <div class="form-group" style="float:right"> 
	    	<input type="button" class="btn btn-primary" value="등록하기" onclick="goInsert()">   
		    <a href = "/memberList.do">
            <button type="button" class="btn btn-warning">취소</button>  
           </a> 
	  </div> 
	  </form>
	</div>
  </div>
</div>
<br><br><br><br><br><br>
	 
</body>
</html>



<script>

function goInsert() {	
	
	    document.myform.submit();
	    // 모두 확인 후 submit()
}

</script>
