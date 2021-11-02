<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>운동시간 및 회비안내</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<link rel="icon" type="image/png" sizes="16x16" href="../images/ajouicon.ico"> 
</head>
<body>

<%@include file="/include/top.jsp" %>

<div class="wrapper bgded overlay" style="background-image:url('../images/training.jpg');">
  <div id="breadcrumb" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
   <h6 class="heading">운동시간 및 회비안내</h6>   
  </div>
</div>

<div class="wrapper row2" style="background-image: url('../images/meetings-page-bg.jpg'); ">
 <main class="hoc container clear"> 
 
    <div class="sectiontitle" style="font-weight:bold;color:black;background-color:white; padding:20px;border-radius: 20px;width:100%;">
      <h6 class="heading">2021년도 1학기 운동시간 안내</h6><br><br>
       <table style="width:100%;height:50px;">
          <thead>
            <tr>
              <th>월요일</th>
              <th>화요일</th>
              <th>수요일</th>
              <th>목요일</th>
              <th>금요일</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>오후5시-7시</td>
              <td>오후6시-8시</td>
              <td>오후5시-7시</td>
              <td>오후6시-8시</td>
              <td>오후5시-7시</td>
             </tr>
          </tbody>
        </table>
        <br><br><br><br><br>
        <h1>회비</h1><br>
        <h6 class="heading">학기당 2만원</h6><br>
    </div>

 </main>   
  <div style="color:white;">
  <%@include file="/include/bottom.jsp" %>
  </div>
</div>



</body>


<script>
      //This code loads the IFrame Player API code asynchronously.
      var tag = document.createElement('script');

      tag.src = "https://www.youtube.com/iframe_api";
      var firstScriptTag = document.getElementsByTagName('script')[0];
      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

      //This function creates an <iframe> (and YouTube player)
      //after the API code downloads.
      var player;
      function onYouTubeIframeAPIReady() {
        player = new YT.Player('player', {
          height: '360',
          width: '640',
          videoId: 'EnX0CggniFM',
        });
        player = new YT.Player('player02', {
            height: '360',
            width: '640',
            videoId: 'Ae3ISLm541k',
          });      
       player = new YT.Player('player03', {
           height: '360',
           width: '640',
           videoId: 'FtSbz_0xcH0',
         });
       player = new YT.Player('player04', {
           height: '360',
           width: '640',
           videoId: 'JHpwcR0xtTY',
         });
       player = new YT.Player('player05', {
           height: '360',
           width: '640',
           videoId: 'oTqSc-bhQ5U',
         });
       player = new YT.Player('player06', {
           height: '360',
           width: '640',
           videoId: 'XlmB5Ufuoac',
         });
       player = new YT.Player('player07', {
           height: '360',
           width: '640',
           videoId: 'zhFetCwmERs',
         });
       player = new YT.Player('player08', {
           height: '360',
           width: '640',
           videoId: 'zNRBRSiZToE',
         });
       player = new YT.Player('player09', {
           height: '360',
           width: '640',
           videoId: '2jKevqjIzIw',
         });
       player = new YT.Player('player10', {
           height: '360',
           width: '640',
           videoId: 'rUUCcPqEhNw',
         });
       player = new YT.Player('player11', {
           height: '360',
           width: '640',
           videoId: '8qY90blWVAM',
         });
       
     }
      
</script>



</html>