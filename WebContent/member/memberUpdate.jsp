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


  <title>회원정보수정</title>


 
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



<div style="margin-left:100px; margin-right:100px; ">

<br><br><br><br>

<div style="margin:30px">
   <div class="container-fluid"  >
	 <h2>회원정보수정</h2>
	 <form name="myform" id = myform method="post"  action ="/index.do?cmd=update">
	 <input type="hidden" id="userid" name="userid" value= "<%=userid%>" >
	
	 <table class="table table-striped" style="text-align:center">
     <thead class="thead-dark">     
      <colgroup>
		<col width="5%">
		<col width="10%">
		<col width="10%">
		<col width="15%">
		<col width="12%">
		<col width="20%">
		<col width="*">
	</colgroup>
      
      <tr class="thead-dark">
        <th>번호</th>
        <th>아이디</th>
        <th>이름</th>
        <th>학번</th>
        <th>회원등급</th>
        <th>핸드폰번호(-자 제외작성)</th>   
        <th>주소</th>
      </tr>
     </thead>   
     <tbody>
  	           <tr>
	             <td><%=1%></td>
	             <td><input type="text" class="form-control" style="width:100%;border:none;" value= "<%=userid%>" disabled ></td>
	             <td><input type="text" id="username" name="username" class="form-control" style="width:100%;border:none;" value= "<%=username%>" placeholder="이름을 입력하세요"></td>
	             <td><input type="text" id="schoolnum" name="schoolnum" class="form-control" style="width:100%;border:none;" value= "<%=schoolnum%>" placeholder="학번을 입력하세요"></td>
	             <td>          
				 <select  id="level" name= "level" style="width:100%;border:none;" >
    				<option 	 value="1">일반회원</option>
  					<option 	 value="2">관리자</option>
				 </select>
          		 </td>
	             <td><input type="text" id="phone" name="phone" class="form-control" style="width:100%;border:none;" value= "<%=phone%>" placeholder="핸드폰번호를 입력하세요"></td>
	             <td><input type="text" id="address1" name="address1" class="form-control" style="width:100%;border:none;" value= "<%=address1%>" placeholder="주소를입력하세요"></td>
	            </tr>     
    </tbody>    
  </table>


	   <div class="form-group" style="float:right"> 
	    	<input type="button" class="btn btn-primary" value="수정하기" onclick="goUpdate()">   
		    <a href = "/memberManage.do">
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

function goUpdate() {	
	
	    document.myform.submit();
	    // 모두 확인 후 submit()
}

</script>
