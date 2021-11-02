<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.multi.model.*" %>
<%@page import="com.multi.dto.*" %>
<%@page import="com.multi.common.*" %>
<%@page import="java.net.URLDecoder" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 

<title>역대부원</title>

<!-- Bootstrap core CSS -->
<link href="/layout/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">
<!-- Bootstrap core JavaScript -->
<script src="/layout/jquery/jquery.min.js"></script>
<script src="/layout/bootstrap/js/bootstrap.bundle.min.js"></script>
<link href="/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">

</head>
<body>

<%@include file="/include/top.jsp" %>

<div class="wrapper bgded overlay" style="background-image:url('/images/memberlist.jpg');">
  <div id="breadcrumb" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
   <h6 class="heading">역대부원</h6>
  </div>
</div>
<div class="wrapper row2" id="wrapper">
  <main class="hoc container clear"> 
    <!-- main body -->
    <div class="container-fluid" >
      <%
      //파라미터 처리하기 
      String key=Util.encoding(request.getParameter("key"));
      String sel=Util.nullTo(request.getParameter("sel"));
      String contextPath = request.getContextPath();
      int totalCount;
	
      if(request.getAttribute("totalCount") !=  null){
    	  totalCount = (int)request.getAttribute("totalCount");
      }else{
    	  totalCount = 1;
      }
      
      System.out.println("전체데이터개수 : " + totalCount);

      %>

  <form name="mform">
     <input type="hidden" name="cmd"            value="write">
     <input type="hidden" name="pg"             value="0">
     <input type="hidden" name="id"             value="1">
   
    
      <div class="hoc clear" style="float:right"> 
        <label style="float:left">    
          <select class="drop" id="sel"  name="sel" style="width:70px;height:20px;">
           <option value="all"  <%if(sel.equals("all")){%>   selected    <%}%> >전체</option>
           <option value="1"    <%if(sel.equals("1")){%>     selected    <%}%> >이름</option>
           <option value="2"    <%if(sel.equals("2")){%>     selected    <%}%> >학번</option>
           <option value="3"    <%if(sel.equals("3")){%>     selected    <%}%> >입부년도</option>
          </select>
        </label>   
        <input type="text" class="btmspace-15" style="float:left;height:20px;" placeholder="검색어를 입력하세요" id="key" name="key" value="<%=key%>">
        <button  type="button" style="float:left" onclick="goSearch()" class="fas fa-search" type="button"></button> 
      </div> 
    
   </form>

   <table class="table table-striped">
     <thead class="thead-dark">
       <colgroup>       	
        <col width=15%>
      	<col width=15%>
      	<col width=15%>
      	<col width=15%>
      	<col width=*>
      	<col width=5%>
      </colgroup>
      <tr class="thead-dark">
        
        <th>성명</th>
        <th>학번</th>
        <th>학과</th>
        <th>입부년도</th>
        <th>비고</th>
        <%if(level==2) {%>
        <th>삭제</th>
        <%} %>
      </tr>
     </thead>   
     <tbody>
     <%
     
	 if(request.getAttribute("list")!=null){
		 
	      List<MemberListDto> list = (List<MemberListDto>)request.getAttribute("list");
	      for(int i=0; i<list.size(); i++){
	    	  MemberListDto tempDto = list.get(i);
		 
	      %>
	           <tr>
	             <td><%=tempDto.getUsername()%></td>
	             <td><%=tempDto.getSchoolnum()%></td>
	             <td><%=tempDto.getSchoolsub()%></td>
	             <td><%=tempDto.getEnteryear()%></td>
	             <td><%=tempDto.getEtc()%></td>
	             <%if(level==2) {%>
	           	 <td><a href="#"><i onclick="goDelete('<%=tempDto.getSchoolnum()%>')">삭제</i></a></td>
	             <%} %>
	           </tr>
	     <%} 	 
	 }%>	
     
     
    </tbody>    
  </table>
          
     <!--  페이징 처리  -->
     <div class="group center" style="width:50px">
     	<%=Pager.makeTag(request, 10, totalCount)%>
     </div>  
     
     <%if(level==2) {%>
     <div class="form-group" style="float:right"> 
   		<a href="/memberList/write.jsp"><button type="button" class="btn btn-success">부원등록</button></a>
   	 </div>
	 <%} %>
	
    </div>
  
  
    <!-- / main body -->
   <div class="clear"></div>
 </main>
</div>


</body>

<%@include file="/include/bottom.jsp" %>



<script>


function  goSearch() {
	
	var key = document.getElementById("key").value;
	key = encodeURI(key);//인코딩 
	var frm = document.mform;
	frm.method="get";
	frm.cmd.value="list";
	frm.key.value=key;
	frm.action="/memberList.do";
	frm.submit();

	
}

function  goDelete(schoolnumber) {
	
	  if (!confirm("해당 정보를 삭제하시겠습니까?")) {
	        // 취소(아니오) 버튼 클릭 시 이벤트
	    } else {	    	
	    	
	        // 확인(예) 버튼 클릭 시 이벤트
	    	var frm = document.mform;
	    	frm.cmd.value="delete";
	    	frm.id.value = schoolnumber;
	    	frm.action="/memberList.do";
	    	frm.submit();
	    	
	    }

}




</script>

</html>



