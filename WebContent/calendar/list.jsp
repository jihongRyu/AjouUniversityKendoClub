<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.multi.model.*" %>
<%@page import="com.multi.dto.*" %>
<%@page import="com.multi.common.*" %>
<%@page import="java.net.URLDecoder" %>
<%@ page language="java" import="java.util.Date"%>
<%@ page import="java.sql.*"%>



    
<!DOCTYPE html>
<html>
<head>

<title>주요일정</title>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 


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
  <link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 
  
</head>
<body>

<%@include file="/include/top.jsp" %>

<div class="wrapper bgded overlay" style="background-image:url('/images/calendar.jpg');">
  <div id="breadcrumb" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
   <h6 class="heading text-white">주요일정</h6>
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
	  
	  java.util.Calendar cal=java.util.Calendar.getInstance(); //Calendar객체 cal생성
	  int currentYear=cal.get(java.util.Calendar.YEAR); //현재 날짜 기억
	  int currentMonth=cal.get(java.util.Calendar.MONTH);
	  int currentDate=cal.get(java.util.Calendar.DATE);
	  
	  String Year=request.getParameter("year"); //나타내고자 하는 날짜
	  String Month=request.getParameter("month");
	  int year, month;
	  if(Year == null && Month == null){ //처음 호출했을 때
	  year=currentYear;
	  month=currentMonth;
	  }
	  else { //나타내고자 하는 날짜를 숫자로 변환
	   year=Integer.parseInt(Year);
	   month=Integer.parseInt(Month);
	   if(month<0) { month=11; year=year-1; } //1월부터 12월까지 범위 지정.
	   if(month>11) { month=0; year=year+1; }
	  }
	  %>
	  
	
	  <div class="container-fluid" style="font-size:150%;text-align:center;padding:2%">	    
	    <!-- 년 도-->
	    <a href="/calendar.do?year=<%out.print(year-1);%>&month=<%out.print(month);%>">◀</a>
	    <% out.print(year); %>년
	    <a href="/calendar.do?year=<%out.print(year+1);%>&month=<%out.print(month);%>">▶</a>
	    <a href="/calendar.do?year=<%out.print(year);%>&month=<%out.print(month-1);%>">◀</a>
	    <% out.print(month+1); %>월
	    <a href="/calendar.do?year=<%out.print(year);%>&month=<%out.print(month+1);%>">▶</a>
	 	
	 	<input type = "hidden" id="year"  name= "year"  value=<%= year %>>
		<input type = "hidden" id="month" name= "month" value='<%= month+1 %>'>
	
	  </div>
	  
	  <table> <!-- 달력 부분 -->
	   <tr style="font-weight:bold">
	    <td width=100>일</td> <!-- 일=1 -->
	    <td width=100>월</td> <!-- 월=2 -->
	    <td width=100>화</td> <!-- 화=3 -->
	    <td width=100>수</td> <!-- 수=4 -->
	    <td width=100>목</td> <!-- 목=5 -->
	    <td width=100>금</td> <!-- 금=6 -->
	    <td width=100>토</td> <!-- 토=7 -->
	   </tr>
	   <tr height=100 >
	   <%
	   
	   cal.set(year, month, 1); //현재 날짜를 현재 월의 1일로 설정
	   int startDay=cal.get(java.util.Calendar.DAY_OF_WEEK); //현재날짜(1일)의 요일
	   int end=cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); //이 달의 끝나는 날
	   int br=0; //7일마다 줄 바꾸기
	   for(int i=0; i<(startDay-1); i++) { //빈칸출력
	    out.println("<td width=100>&nbsp;</td>");
	    br++;
	    if((br%7)==0) {
	     out.println("<br>");
	    }
	   }
	   
	   List<CalendarDto> list = (List<CalendarDto>)request.getAttribute("list");
	 
	   for(int i=1; i<=end; i++) {  //날짜출력 %>
	   
	    <td width=100 style="color:black" onmouseover="this.style.color='gray'" onmouseout="this.style.color='black'"><%=i %><br><br>
	    
	    <%  //메모(일정) 추가 부분
	      
	 
	      for(int j=0; j<list.size(); j++){

	    	  CalendarDto tempDto = list.get(j);	    	  
	         
	    	  String memoyear=tempDto.getCalendarmemoYear();
	    	  String memomonth=tempDto.getCalendarmemoMonth();
	    	  String memoday=tempDto.getCalendarmemoDay();
		      if(year==Integer.parseInt(memoyear) && month+1==Integer.parseInt(memomonth) && i==Integer.parseInt(memoday) ) {
		         out.println(tempDto.getCalendarmemoContents()+"<br>"); 
		        }	
	     }
	  
	    out.println("</td>");
	    br++;
	    if((br%7)==0 && i!=end) {
	     out.println("</tr><tr height=100>");
	    }
	   }
	   
	   while((br++)%7!=0) //말일 이후 빈칸출력
	    out.println("<td width=100>&nbsp;</td>");
	   %>
	   </tr>
	  </table>
	
	

	
    </div>
   <% if (level==2){%>
		  
		 <a href="/calendar/insert.jsp"><button type="button" class = "btn btn-primary" style="float:right;font-size:15px;" >일정등록</button></a>

	 <% }%>
  	
  
 </main>
 
</div>

<%@include file="/include/bottom.jsp" %>

</body>


<script>



</script>

<!-- Main jQuery -->
    <script src="/layout2/plugins/jquery/jquery.js"></script>
    <script src="/layout2/js/contact.js"></script>
    <!-- Bootstrap 4.3.1 -->
    <script src="/layout2/plugins/bootstrap/js/popper.js"></script>
    <script src="/layout2/plugins/bootstrap/js/bootstrap.min.js"></script>
   <!--  Magnific Popup-->
    <script src="/layout2/plugins/magnific-popup/dist/jquery.magnific-popup.min.js"></script>
    <!-- Slick Slider -->
    <script src="/layout2/plugins/slick-carousel/slick/slick.min.js"></script>
    <!-- Counterup -->
    <script src="/layout2/plugins/counterup/jquery.waypoints.min.js"></script>
    <script src="/layout2/plugins/counterup/jquery.counterup.min.js"></script>

    <!-- Google Map -->
    <script src="/layout2/plugins/google-map/map.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkeLMlsiwzp6b3Gnaxd86lvakimwGA6UA&callback=initMap"></script>    
    
    <script src="/layout2/js/script.js"></script>

</html>

		