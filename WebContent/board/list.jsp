<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.multi.newmember.*" %>
     <%@page import="java.util.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<boardDto> list = (List<boardDto>)request.getAttribute("boardList");
%>
<table width="1024px" border="1px" cellspacing = "0" cellpadding = "0">
	<colgroup>
		<col width = "5%"> 
		<col width = "*"> 
		<col width = "10%"> 
		<col width = "10%"> 
		<col width = "5%"> 
	</colgroup>
	<thead>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</thead>
	<tbody>
		<%for(int i=0; i<list.size(); i++) {
		boardDto dto = list.get(i);
		%>
		<tr>
			<td><%=dto.getId()%></td>
			<td>
				<a href="/MVC1/index.do?cmd=board&job=view&id=<%=dto.getId()%>">
				<%=dto.getTitle()%>
			</td>
			<td><%=dto.getWriter()%></td>
			<td><%=dto.getRegdate()%></td>
			<td><%=dto.getHit()%></td>			
		</tr>
		<%} %>
	</tbody>
		
</table>

<br>
<a href ="/MVC1/index.do?cmd=board&job=write">글쓰기</a>
</body>
</html>