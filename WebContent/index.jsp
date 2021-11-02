<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.*" %>
<%@page import="com.multi.model.*" %>
<%@page import="com.multi.dto.GalleryDto" %>
<%@page import="com.multi.common.*" %>

<!DOCTYPE html>
<html lang="">
<head>

<title>아주대검도부</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="description" content="아주대학교 검도부 홈페이지입니다." />
<meta property="og:type" content="website" />
<meta property="og:title" content="아주대학교검도부" />
<meta property="og:description" content="경기도 수원시 영통구 월드컵로 206 아주대학교 검도부 웹사이트입니다." />
<meta property="og:url" content="http://a4885827.cafe24.com" />
<meta name="Robots" content="INDEX, FOLLOW" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8">

<link rel="icon" type="image/png" sizes="16x16" href="images/ajouicon.ico"> 

<link href="/layout/styles/templatemo-edu-meeting.css" rel="stylesheet" type="text/css" media="all"> 

</head>

<body>


<%@include file="/include/top.jsp" %>


<div class="wrapper bgded overlay" style="background-image:url('images/main.jpg');">
  <div id="pageintro" class="hoc clear" style="padding-left:3%;padding-right:3%;color:white;"> 
    <article>
     <h3 class="heading">아주대 검도부</h3>
      <p style="color:white;">2022년도 신입부원 모집</p>
      <footer><a class="btn" onclick="openInNewTab('https://docs.google.com/forms/d/1HYUhlBd7IlDgDBlVUTTVsBVtjXdx-XIyHifCDkAKsj4/edit');">입부신청</a></footer>       
    </article>
  </div>
</div>

 <div class="wrapper gradient" style="background-image: url('images/meetings-page-bg.jpg');color:white; ">
  	<br><br>
    <section class="hoc"> 
   	<div class="sectiontitle">
      <p class="heading"style="font-weight:bold;color:white;">아주대 검도부 기본정보</p>
    </div>
    <div class="group center">
      <article class="one_third first"><a class="ringcon btmspace-50" href="#" onclick="check_memberList(this)"><i class="fas fa-people-carry"></i></a>
        <h6 class="heading"style="font-weight:bold">부원수</h6>
        <p style="color:white;">50여명</p>
      </article>
      <article class="one_third"><a class="ringcon btmspace-50" href="/pages/directions.jsp"><i class="fas fa-warehouse"></i></a>
        <h6 class="heading"style="font-weight:bold">운동장소</h6>
        <p style="color:white;">체육관 2층 연습실</p>
      </article>
      <article class="one_third"><a class="ringcon btmspace-50" href="/pages/trainingInformation.jsp"><i class="far fa-calendar-times"></i></a>
        <h6 class="heading"style="font-weight:bold">운동시간</h6>
        <p style="color:white;">매주 월요일-금요일</p>
      </article>
    </div>
  </section>
  <br>
</div>

<section class="section main-banner" id="top" data-section="section1" >
      <video autoplay muted loop id="bg-video">
          <source src="images/main.mp4" type="video/mp4" />
      </video>
      <div class="video-overlay header-text" >
          <div class="container">
            <div class="row">
            <div class="col-lg-12">
            <div class="caption" style="padding-left:3%;padding-right:3%">           
              <p style="font-size:30px;font-weight:bold">견학 예약하기</p><br>              
              <p style="font-size:15px;">견학을 원하시는 분들은 아래 '신청하기'버튼을 통해 견학신청을 하실 수 있습니다. <br>언제든지 환영합니다. 부담없이 신청해주세요!</p>
              <div class="main-button-red">
                  <a  class="btn" onclick="openInNewTab('https://docs.google.com/forms/d/1Zm1qO_V7XjBDVwn3NSEukR3ZweTQ2MBSkp-eH5HSgao');">신청하기</a>
              </div>
            </div>
            </div>
            </div>
          </div>
      </div>
  </section> 
  
<div style="background-image: url('images/meetings-page-bg.jpg'); ">	
	  <section class="hoc container clear"> 
	  	<div id="comments" style="color:black; background-color:white; padding:30px;border-radius: 20px;">
	        <h2>문의하기</h2>	    
	        <p>궁금하신 사항이 있으시면 부담없이 언제든지 문의주시기 바랍니다!</p>                  
	        <hr><br>	      
	      
	          <div class="one_third first">
	            <label for="name">성함<span>*</span></label>
	            <input type="text" name="name" id="name" value="" size="22" required>
	          </div>
	          <div class="one_third">
	            <label for="sub">이메일<span>*</span></label>
	            <input type="email" name="email" id="email" value="" size="22" required>
	          </div>
	          <div class="one_third">
	            <label for="phone">핸드폰번호<span>*</span></label>
	            <input type="text" name="phone" id="phone" value="" size="22">
	          </div>
	          <div class="block clear">
	            <label for="comment">문의내용<span>*</span></label>
	            <textarea name="comment" id="comment" cols="25" rows="10"></textarea>
	          </div>
	          <div>
	            <input type="submit" name="submit" value="보내기">
	          </div>	   
	         
		 </div>
		</section>	
</div>

<%@include file="/include/bottom.jsp" %>

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/emailjs-com@3/dist/email.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		emailjs.init("user_iWTk1UpqWEM6pP0OUuHoK");		
        //"user_xxxxx"이 부분은 사용자마다 다르니 반드시 emailJS의 installation 화면을 확인
        $('input[name=submit]').click(function(){ 
        	
        	
        	if (!document.getElementById("name").value)
 		    {
 		        alert("성함을 입력하세요!");
 		        // 화면 커서 이동
 		        return;
 		        
 		    } 
        	
        	if (!document.getElementById("email").value)
		    {
		        alert("이메일을 입력하세요!");
		        // 화면 커서 이동
		        return;
		        
		    } 
        	
        	if (!document.getElementById("phone").value)
		    {
		        alert("핸드폰번호를 입력하세요!");
		        // 화면 커서 이동
		        return;
		    }
        	
        	if (!document.getElementById("comment").value)
		    {
		        alert("문의내용을 입력하세요!");
		        // 화면 커서 이동
		        return;
		    }
        	        
          
          var templateParams = {	
          //각 요소는 emailJS에서 설정한 템플릿과 동일한 명으로 작성!
                name: $('input[name=name]').val(),
                email : $('input[name=email]').val(),
                phone: $('input[name=phone]').val(),               
                comment : $('textarea[name=comment]').val()
           		};
                    
                	
         emailjs.send('service_9zanssj', 'template_mb1wpk3', templateParams)
         //emailjs.send('service ID', 'template ID', 보낼 내용이 담긴 객체)
         	    .then(function(response) {
         	    	alert('이메일을 보냈습니다.');
         	    }, function(error) {
         	   		alert('이메일을 보내지 못하였습니다.');
         	    });
         	       


        });
        
	  });
    

	</script>




<script>

   function openInNewTab(url) {
       var win = window.open(url, '_blank');
       win.focus();
   }
   
   function goView(id)   {
   	//get 방식으로 서버로 보낼때는 한글은 반드시 인코딩을 해서 보내야  안깨진다 

   	var key = document.getElementById("key").value;
   	key = encodeURI(key);//인코딩 
   	var frm = document.mform;
   	frm.method="get";
   	frm.cmd.value="view";
   	frm.id.value=id;
   	frm.key.value=key;
   	frm.action="/jobtalk.do";
   	frm.submit();
   }
   
	 
   
</script> 

</body>
</html>