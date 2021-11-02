<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>시설안내</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="icon" type="image/png" sizes="16x16" href="../images/ajouicon.ico">  
<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">

</head>
<body>

<%@include file="/include/top.jsp" %>

<div class="wrapper bgded overlay" style="background-image:url('../images/facility.jpg');">
  <div id="breadcrumb" class="hoc clear" style="padding-left:3%;padding-right:3%"> 
   <h6 class="heading">시설안내</h6>
  </div>
</div>

<div class="wrapper row2"  style="background-image: url('../images/meetings-page-bg.jpg'); ">
  <main class="hoc container clear"> 
    <div class="content group center" style="color:black; background-color:white; padding:30px;border-radius: 20px;"> 
      <h1>[연습실 및 창고 리모델링 완료]</h1>
      <br>
      <div class="borderedbox inspace-5">
      <img src="../images/gym02.jpg" style="width:500px;pointer-events : none;" alt="">
      <img src="../images/warehouse.jpg" style="width:500px;pointer-events : none;" alt="">
      </div>
      <br><br>
      <h1>[동아리방 리모델링 완료]</h1>
      <img class="borderedbox inspace-5" src="../images/room.jpg" style="width:500px;pointer-events : none;" alt="">
      <br><br><br><br><br><br>
    </div>
    <div class="clear"></div>
  </main>
   <div style="color:white;">
  <%@include file="/include/bottom.jsp" %>
  </div>
</div>




</body>

</html>