<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>


<html lang="">
<meta charset="UTF-8"> 

<!--<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">-->
<link href="/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">  


<body id="top">

        <%String userid = (String)session.getAttribute("userid");%>
        <%String username = (String)session.getAttribute("username");%>
        <%String password = (String)session.getAttribute("password");%>
        <%String email = (String)session.getAttribute("email");%>
        <%String phone = (String)session.getAttribute("phone");%>
        <%String address1 = (String)session.getAttribute("address1");%>
        <%String schoolnum = (String)session.getAttribute("schoolnum");%>
        <%String schoolsub = (String)session.getAttribute("schoolsub");%>
        <%String enteryear = (String)session.getAttribute("enteryear");%>
        
        <%
        int level;
        if(session.getAttribute("level")!=null){
        	level = (int)session.getAttribute("level");
        } else {
        	level = 0;
        }
        %>


<div class="wrapper row0">
  <div id="topbar" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
    <div class="fl_left">
      <ul class="nospace"> 
        <li style="cursor: pointer;" onclick="openInNewTab('https://www.ajou.ac.kr/kr/index.do');"><i class="fas fa-home fa-lg"></i></li>
        <li style="cursor: pointer;" onclick="openInNewTab('https://www.facebook.com/groups/AjouKumdo/');"><i class="fab fa-facebook"></i></li>
      </ul>
    </div>
    <div class="fl_right">
      <ul class="nospace">
        <%	
        if(userid==null) userid="";
	    //세션에 있는 값을 읽어와서 userid가 있는지 확인한다.
	    %>		 
	    <%if(userid.equals("")){%>
        <li><a href="/index.do?cmd=member&job=logon_form">login</a></li>		 		
	    <li><a href="/index.do?cmd=member&job=write">회원가입</a></li>				  			             
	    <%} else { %>
	    <li><span><%=username%>&nbsp;님 안녕하세요</span></li>		  	
	    <li><a href ="/member/member_list.jsp">회원정보</a></li>  				
	    <li><a href ="/index.do?cmd=member&job=logout">로그아웃</a></li>	    
	    <%} %>
        <%if(level==2){%>
        <li>관리자 모드입니다.</li>
	    <%}%>
      </ul>
    </div>
  </div>
</div>

<div class="wrapper row1">
  <header id="header" class="hoc clear" style="padding-left:3%;padding-right:3%;">
    <div id="logo">   
    	<a href="/index.do"><img src="/images/mainlogo.png"  style="width:400px"></a>
    </div>

  </header>
  
  <nav id="mainav" class="hoc clear"> 
    <ul class="clear">
      <li style="padding:10px"></li>
      <li><a class="drop" href="/index.jsp">아주대 검도부</a>
        <ul>
          <li><a href="/pages/history.jsp">검도부소개</a></li>          
          <li><a href="#" onclick="check_memberList(this)">역대부원</a></li>          
          <li><a href="/pages/awardHistory.jsp">수상이력</a></li>
          <li><a href="/pages/facilitiesInformation.jsp">시설안내</a></li>
          <li><a href="/pages/directions.jsp">오시는길</a></li>
        </ul>
      </li>
      <li><a href="/pages/kendoDefinition.jsp">검도란?</a></li>
      <li><a class="drop" href="#">수련안내</a>
         <ul>
          <li><a href="/pages/trainingInformation.jsp">운동시간 및 회비안내</a></li>
          <li><a href="/pages/trainingInformation02.jsp">기초수련과정안내</a></li>
          <li><a href="/pages/trainingInformation03.jsp">심화수련과정안내</a></li>
         </ul>      
      </li>
       <li><a class="drop" href="#">자유게시판</a>
      <ul> 
	      <li><a href="#" onclick="check_yblist(this)">YB게시판</a></li>
	      <li><a href="#" onclick="check_oblist(this)">OB게시판</a></li>
	   </ul>
	   </li>   
      <li><a href="#" onclick="check_jobtalk(this)">공지사항</a></li>   
      <li><a href="#" onclick="check_calendar(this)">주요일정</a></li>      
      <li><a href="#" onclick="check_gallery(this)">갤러리</a></li> 
      <li><a href="#" onclick="check_video(this)">영상자료</a></li> 
      <li><a href="#" onclick="check_jobreport(this)">검도자료</a></li>
      <%if(level==2){%>
      <li><a href="/memberManage.do">회원관리</a></li>
      <%}%>
    </ul>
  </nav>
</div> 

</body>

<!-- JAVASCRIPTS -->
<script src="/layout/scripts/jquery.min.js"></script>
<script src="/layout/scripts/jquery.backtotop.js"></script>
<script src="/layout/scripts/jquery.mobilemenu.js"></script>


 
<script>
   function openWindowPop(url, name){
     var options = 'top=10, left=10, width=480, height=560, status=no, menubar=no, toolbar=no, resizable=no';
     window.open(url, name, options);
   }

   function openInNewTab(url) {
       var win = window.open(url, '_blank');
       win.focus();
   }
   
   
   function check_jobreport() {
		
	    if (<%=level%>==1||<%=level%>==2){
	    	location.href="/jobreport.do";       
	    } else if (<%=level%>==3) {
	    	alert("가등록 상태입니다. 관리자 승인을 기다려주세요.");
	    }	else {
	    	alert("권한이 없습니다.");
	    }  
	 }  
   
   function check_memberList() {
		
	    if (<%=level%>==1||<%=level%>==2){
	    	location.href="/memberList.do";       
	    } else if (<%=level%>==3) {
	    	alert("가등록 상태입니다. 관리자 승인을 기다려주세요.");
	    }	else {	  
	    	alert("권한이 없습니다.");
	    }  
	 }  
   
   function check_gallery() {
		
	    if (<%=level%>==1||<%=level%>==2){
	    	location.href="/gallery.do";       
	    } else if (<%=level%>==3) {
	    	alert("가등록 상태입니다. 관리자 승인을 기다려주세요.");
	    }	else {
	    	alert("권한이 없습니다.");
	    }  
	 }
   
   function check_jobtalk() {
			
		    if (<%=level%>==1||<%=level%>==2){
		    	location.href="/jobtalk.do";       
		    } else if (<%=level%>==3) {
		    	alert("가등록 상태입니다. 관리자 승인을 기다려주세요.");
		    }	else {
		    	alert("권한이 없습니다.");
		    }  
		 }
   
   function check_yblist() {
		
	    if (<%=level%>==1||<%=level%>==2){
	    	location.href="/yblist.do";       
	    } else if (<%=level%>==3) {
	    	alert("가등록 상태입니다. 관리자 승인을 기다려주세요.");
	    }	else {
	    	alert("권한이 없습니다.");
	    }  
	 }
   
   function check_oblist() {
		
	    if (<%=level%>==1||<%=level%>==2){
	    	location.href="/oblist.do";       
	    } else if (<%=level%>==3) {
	    	alert("가등록 상태입니다. 관리자 승인을 기다려주세요.");
	    }	else {
	    	alert("권한이 없습니다.");
	    }  
	 }
   
   function check_calendar() {
		
	    if (<%=level%>==1||<%=level%>==2){
	    	location.href="/calendar.do";       
	    } else if (<%=level%>==3) {
	    	alert("가등록 상태입니다. 관리자 승인을 기다려주세요.");
	    }	else {
	    	alert("권한이 없습니다.");
	    }  
	 }
   
   
   
   function check_video() {
		
	    if (<%=level%>==1||<%=level%>==2){
	    	location.href="/video.do";       
	    } else if (<%=level%>==3) {
	    	alert("가등록 상태입니다. 관리자 승인을 기다려주세요.");
	    }	else {
	    	alert("권한이 없습니다.");
	    }  
	 }
   
   
</script>       
     

</html>