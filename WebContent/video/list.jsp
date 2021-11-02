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

<title>영상자료</title>

<!-- Bootstrap core CSS -->
<link href="/layout/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">
<!-- Bootstrap core JavaScript -->
<script src="/layout/jquery/jquery.min.js"></script>
<script src="/layout/bootstrap/js/bootstrap.bundle.min.js"></script>
<link href="/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 

</head>
<body>

<%@include file="/include/top.jsp" %>

<div class="wrapper bgded overlay" style="background-image:url('/images/video.jpg');">
  <div id="breadcrumb" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
   <h6 class="heading">영상자료</h6>
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
 
 
   <form name="mform" >
     <input type="hidden" name="cmd"            value="write">
     <input type="hidden" name="pg"             value="0">
     <input type="hidden" name="id"             value="1">
   
    
      <div class="hoc clear" style="float:right"> 
        <label style="float:left">    
          <select class="drop" id="sel"  name="sel" style="width:70px;height:20px;">
           <option value="all"  <%if(sel.equals("all")){%>   selected    <%}%> >전체</option>
           <option value="1"    <%if(sel.equals("1")){%>     selected    <%}%> >제목</option>
           <option value="2"    <%if(sel.equals("2")){%>     selected    <%}%> >내용</option>
           <option value="3"    <%if(sel.equals("3")){%>     selected    <%}%> >글쓴이</option>
          </select>
        </label>   
        <input type="text" class="btmspace-15" style="float:left;height:20px;" placeholder="검색어를 입력하세요" id="key" name="key" value="<%=key%>">
        <button  type="button" style="float:left" onclick="goSearch()" class="fas fa-search" type="button"></button> 
      </div> 
    
   </form>

   <br><br><br>
   <div class="form-group" style="float:right"> 
   <button type="button" onclick="goWrite()" class="btn btn-success">글쓰기</button>
   </div>
   <br><br><br>
   <table class="table table-striped">
     <thead class="thead-dark">
       <colgroup>
        <col width=10%>
      	<col width=*>
      	<col width=10%>
      	<col width=10%>
      	<col width=10%>
      </colgroup>
      <tr class="thead-dark">
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>작성일자</th>
      </tr>
     </thead>   
     <tbody>
     <%
     
	 if(request.getAttribute("list")!=null){
		 
	      List<VideoDto> list = (List<VideoDto>)request.getAttribute("list");
	      for(int i=0; i<list.size(); i++){
	    	  VideoDto tempDto = list.get(i);
		     int num = tempDto.getNum();
		     int depth = tempDto.getGroup_depth();
		      String reply="";
		
		      for(int jj=0; jj<depth; jj++)
		      {
			     reply = reply + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		      }
		     if( depth!=0)
			     reply=reply+"re)";
	      %>
	           <tr>
	             <td><%=totalCount-num+1%></td>
	             <td>
	             <%=reply%>
	       	     <a href="#none" onclick="goView('<%=tempDto.getId()%>')"><%=tempDto.getTitle()%></a>
	             </td>
	             <td><%=tempDto.getWriter()%></td>
	             <td><%=tempDto.getHit()%></td>
	             <td><%=tempDto.getWdate()%></td>
	           </tr>
	     <%} 	 
	 }%>	
     
     
    </tbody>    
  </table>
          
     <!--  페이징 처리  -->
     <div class="group center" style="width:50px">
     <%=Pager.makeTag(request, 10, totalCount)%>
     </div>    
     
     <div class="form-group" style="float:right"> 
	 <button type="button" onclick="goWrite()" class="btn btn-success">글쓰기</button>
	 </div>
	
    </div>
  
  
    <!-- / main body -->
   <div class="clear"></div>
 </main>
</div>


</body>

<%@include file="/include/bottom.jsp" %>



<script>

//페이지 이동용 함수 
function goPage(page)
{
	var frm = document.mform;
	//새로 변경된 페이지 정보를 저장
	frm.pg.value = page;
	frm.cmd.value="list";
	frm.action="/video.do";
	frm.submit();
}

function  goWrite()
{
	var frm = document.mform;
	frm.cmd.value="write";
	frm.sel.value="";
	frm.pg.value=1;
	frm.key.value="";
	frm.action="/video.do";
	frm.submit();
}

function goSearch()
{
	//get 방식으로 서버로 보낼때는 한글은 반드시 인코딩을 해서 보내야  안깨진다 

	var key = document.getElementById("key").value;
	key = encodeURI(key);//인코딩 
	var frm = document.mform;
	frm.method="get";
	frm.cmd.value="list";
	frm.key.value=key;
	frm.action="/video.do";
	frm.submit();
}


function goView(id)
{
	//get 방식으로 서버로 보낼때는 한글은 반드시 인코딩을 해서 보내야  안깨진다 

	var key = document.getElementById("key").value;
	key = encodeURI(key);//인코딩 
	var frm = document.mform;
	frm.method="get";
	frm.cmd.value="view";
	frm.id.value=id;
	frm.key.value=key;
	frm.action="/video.do";
	frm.submit();
}
</script>

</html>



