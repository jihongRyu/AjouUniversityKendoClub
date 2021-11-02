<%@page language="java"   contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
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

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/simple-sidebar.css" rel="stylesheet">
  <title>공지사항</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 

  <!-- Custom styles for this template -->
  <link href="css/simple-sidebar.css" rel="stylesheet">
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

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

String key=Util.encoding(request.getParameter("key"));
String sel=Util.nullTo(request.getParameter("sel"));
String id=Util.nullToValue(request.getParameter("id"), "1");
String pg=Util.nullToValue(request.getParameter("pg"), "1");
String contextPath=request.getContextPath();
%>


<%@include file="/include/top.jsp" %>

<div class="wrapper bgded overlay" style="background-image:url('/images/notice.jpg');">
  <div id="breadcrumb" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
   <h6 class="heading">공지사항</h6>
  </div>
</div>

<%
JobtalkDto dto =  (JobtalkDto)request.getAttribute("dto");
JobtalkCommentDto comDto = (JobtalkCommentDto)request.getAttribute("comDto");
%>

<div class="wrapper row2" style="background-image: url('../images/meetings-page-bg.jpg');">

  <form action="" name="myform" method="get">
  	<input type="hidden" name="cmd" value="modify" >
  	<input type="hidden" name="pg" value="<%=pg%>" >
  	<input type="hidden" name="mode" value="modify" >
  	<input type="hidden" name="id" id="id" value="<%=id%>" >
  </form>
  
  <main class="hoc container clear"> 
  <div class="content group clear"  style="color:black; background-color:white; padding:30px;border-radius: 20px;">
	    <div class="container col-md-6">
	    <div class="card">
	        <div class="card-body">
	            <h4 class="card-title mb-3"  style="font-size:30px;"><%=dto.getTitle()%></h4><br>
	            <h6 class="card-subtitle text-muted mb-4" style="font-size:18px;">
	                <i class="far fa-user"></i> <%=dto.getWriter()%>  
	                <i class="far fa-clock"></i> <%=dto.getWdate()%>          
	                <i class="fas fa-align-justify"></i><%=dto.getHit()%>
	            </h6><br>
	            <p class="card-text"><%=dto.getContents()%></p>
	        </div>
	    </div>
	    </div>
   
        <h6 class="card-subtitle text-muted mb-4" style="font-size:18px;">첨부파일</h6>
        <a href="/download.do?path=jobreport&filename=<%=dto.getFileName1()%>"><%=dto.getFileName1()%></a>
        <a href="/download.do?path=jobreport&filename=<%=dto.getFileName2()%>"><%=dto.getFileName2()%></a>
        <a href="/download.do?path=jobreport&filename=<%=dto.getFileName3()%>"><%=dto.getFileName3()%></a>
   

    <div class="form-group" style="float:right;padding:10px;">    	  
       <button type="button" onclick="goReply()" 	   	class="btn btn-success">답글달기</button>
   	   <button type="button" onclick="goList()" 	   	class="btn btn-success">목록</button>
	  			  
	   <button type="button" onclick="goModify()" 	   	class="btn btn-success">수정</button>
	   <button type="button" onclick="goDelete()" class="btn btn-success">삭제</button>
  
       <%if(level==2){ %>			  
	   <button type="button" onclick="goDelete()" class="btn btn-success">관리자권한삭제</button>
      <%} %>
   </div> 
   
 


   
    <table id="mytable" class="table">
			<colgroup>
				<col width=70%>
				<col width=30%>
			</colgroup>
			<thead>
	 		 <tr>
	 		   <th colspan="2">댓글</th>
			  </tr>
  			</thead>
  			<tbody></tbody>
			</table>	

			<section>
			  <form name="frmComment" >
			  <div class="col-md-12" style= "border: 3px solid #dddddd;">	
				  	<input type="text" readonly 
			  		style= "margin:10px;border:none;border-right:0px;border-top:0px;boder-left:0px;boder-bottom:0px;"
 					name="writer" id="writer" value="<%=userid%>">
					<div class="form-group" style="padding-left:3%;padding-right:3%">
				      <label for="comment" ></label>
				      <textarea class="form-control" rows="5" id="contents" name="contents" style="width:100%;border: none"></textarea>
				    </div><br>
				    <button style="margin:10px;float: right;" type="button" class="btn btn-success button color2 btn-md" onclick="goCommentWrite()" >댓글등록</button>
			  </div>
			  </form>
			 </section>
   
	   </div>
	   </main>
	   
	</div>
 <%@include file="/include/bottom.jsp" %>
</body>
</html>

<script>

function goReply()
{
	var frm = document.myform;
	frm.cmd.value="reply";
	frm.mode.value="reply";
	
	frm.action="/jobtalk.do";
	frm.submit();
}

function goList()
{
	var frm = document.myform;
	frm.cmd.value="list";
	
	frm.action="/jobtalk.do";
	frm.submit();
}

function goModify()
{
	var frm = document.myform;
	frm.cmd.value="modify";
	frm.mode.value="modify";

	frm.action="/jobtalk.do";
	frm.submit();
	
	//location.href=url;
}

function goDelete()
{
	if(confirm('삭제하시겠습니까?'))
		{
			var frm = document.myform;
			frm.cmd.value="delete";
			frm.mode.value="delete";
		
			frm.action="/jobtalk.do";
			frm.submit();
		}
}

$(document).ready(function(){
	loadData();
});

function loadData() {
	var board_id = $("#id").val();
	$.ajax({
		url: "/jobtalkcomment.do", // 호출할 파일명
		data:{'cmd':'list', 'board_id':board_id},
		method: "POST",
		dataType: "json",	// 내가 받아야 할 결과 형태가 text, html, xml, json
		success:function(data) {
			console.log(data);
			$('#mytable > tbody:first *').remove();
			
			for(i=0; i<data.length; i++)
			{
				console.log(data[i].writer);
				var temp="<tr>";
				temp +="<td>"+ data[i].contents+"</td>";
				temp +="<td>"+ "아이디: " + data[i].writer ;
				temp += "  IP: " + data[i].ip + "<br/>";
				temp += "등록날짜: " + data[i].wdate ;
				var writer ='<%=session.getAttribute("userid")%>';
				//if( writer == data[i].writer){
					//temp +="<button type='button' class='btn btn-primary' onclick='goCommentModify(\"" + data[i].comment_id + "\")'>수정</button>";
					//temp +="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					temp +="<button type='button' style='margin-left:10px' class='btn btn-success button color2' onclick='goCommentDelete(\"" + data[i].comment_id + "\")'>삭제</button>";
				//}
				temp +="<td>";
				temp += "</tr>";
			    $('#mytable > tbody:first').append(temp);
			    console.log("데이터 로딩 성공");
			}
			
		},
		error:function(){
			console.log("데이터 로딩 실패");
		}
	});
}

function goCommentWrite()
{
	
	var board_id = $("#id").val();
	var writer= $("#writer").val();
	var contents = $("#contents").val();
	
	if( contents.trim().length<10)
	{
		alert("10글자 이상 내용을 입력해주세요");
		$("#contents").focus();
		return false;
	}
	

	$.ajax({
		url: "/jobtalkcomment.do", // 호출할 파일명
		data:{'cmd':'save', 'board_id':board_id, 'writer':writer, 'contents':contents},
		method: "POST",
		dataType: "text",	// 내가 받아야 할 결과 형태가 text, html, xml, json
		success:function(data) {
			console.log(data);
			
			if(data=='OK'){
				alert('등록');
				$("#contents").val("");
				loadData();
			}
		},
		error:function(){
			console.log("데이터 로딩 실패");
		}
	});

}

function goCommentModify(id)
{
	alert(id);

}

function goCommentDelete(id)
{
	alert(id);
	
	$.ajax({
		url: "/jobtalkcomment.do", // 호출할 파일명
		data:{'cmd':'delete',  'comment_id':id},
		method: "POST",
		dataType: "text",	// 내가 받아야 할 결과 형태가 text, html, xml, json
		success:function(data) {
			console.log(data);
			
			if(data=='OK'){
				alert('삭제완료');
				loadData();
			}
			
		},
		error:function(){
			console.log("데이터 로딩 실패");
		}
	});
}

</script>
