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

<title>갤러리</title>

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
  
</head>
<body>

<%@include file="/include/top.jsp" %>

<div class="wrapper bgded overlay" style="background-image:url('/images/gallery.jpg');">
  <div id="breadcrumb" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
   <h6 class="heading text-white">갤러리</h6>
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
           <option value="1"    <%if(sel.equals("1")){%>     selected    <%}%> >제목</option>
          </select>
        </label>   
        <input type="text" class="btmspace-15" style="float:left;height:20px;" placeholder="검색어를 입력하세요" id="key" name="key" value="<%=key%>">
        <button  type="button" style="float:left" onclick="goSearch()" class="fas fa-search" type="button"></button> 
      </div> 
    
   </form>

	
	<!-- 이미지 시작부 -->
	<section class="section portfolio pb-0">	
				
		<div class="container-fluid">		
			<div class="row portfolio-gallery">
		
			 <%
		     
			 if(request.getAttribute("list")!=null){
				 
			      List<GalleryDto> list = (List<GalleryDto>)request.getAttribute("list");
			      for(int i=0; i<list.size(); i++){
			    	  GalleryDto tempDto = list.get(i);
				     int num = tempDto.getNum();
				     int depth = tempDto.getGroup_depth();
			
		
			      %>
			      
			     <div class="col-lg-4 col-md-6">
					<div class="portflio-item position-relative mb-4">
						<a href="/images/gallery/<%=tempDto.getFileName1()%>" class="popup-gallery">
							<img src="/images/gallery/<%=tempDto.getFileName1()%>" style="height:250px;object-fit:cover;" alt="" class="img-fluid w-100" >
							<i class="overlay-item"></i>
							<div class="portfolio-item-content">
								<h3 class="mb-0 text-white"><%=tempDto.getTitle()%></h3>
								<p class="text-white-50"><%=tempDto.getWdate()%></p>
							</div>
						</a>
					</div>
				</div>		           
			           
			     <%} 	 
			 }%>	
		


			</div>
		</div>
		
		
		
	</section>
          
     <!--  페이징 처리  -->
     <div class="group center" style="width:50px">
     <%=Pager.makeTag(request, 9, totalCount)%>
     </div>    
     
     <div class="form-group" style="float:right"> 
	 <button type="button" onclick="goWrite()" class="btn btn-success">업로드</button>
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
	frm.action="/gallery.do";
	frm.submit();
}

function  goWrite()
{
	var frm = document.mform;
	frm.cmd.value="write";
	frm.sel.value="";
	frm.pg.value=1;
	frm.key.value="";
	frm.action="/gallery.do";
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
	frm.action="/gallery.do";
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
	frm.action="/gallery.do";
	frm.submit();
}
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

