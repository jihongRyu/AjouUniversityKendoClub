<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>오시는 길</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="icon" type="image/png" sizes="16x16" href="../images/ajouicon.ico"> 
<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">

</head>
<body>

<%@include file="/include/top.jsp" %>

<div class="wrapper bgded overlay" style="background-image:url('../images/directions.png');">
  <div id="breadcrumb" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
   <h6 class="heading">오시는 길</h6>
  </div>
</div>

<div class="wrapper row2" style="background-image: url('../images/meetings-page-bg.jpg'); ">
  <main class="hoc container clear"> 
    <div class="content group center"  style="color:black; background-color:white; padding:30px;border-radius: 20px;"> 
      <h1>[운동장소]</h1>
      <h1>아주대학교 체육관 2층 연습실</h1>
      <h1>[동아리방]</h1>
      <h1>구학생회관 305호</h1>
      <img class="borderedbox inspace-5" src="../images/Gym.jpg" style="width:500px;pointer-events : none;" alt=""><br>
      
      <br><br><br>
      
     

    </div>
    <div class="clear"></div>
  </main>
   <div style="color:white;">
  <%@include file="/include/bottom.jsp" %>
  </div>
</div>




</body>

</html>