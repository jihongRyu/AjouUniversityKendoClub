<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>상세보기</h1>
	<table border="1px" cellspacing = "1px" cellpadding = "1px">
		<colgroup>
			<col width="30%">
			<col width="20%">
			<col width="30%">			
			<col width="*">
		</colgroup>
		<tr>
		<td>제목</td>
		<td colspan="3">${boardDto.title}</td>
		</tr>
		<tr>
		<td>작성자</td>
		<td>${boardDto.writer}</td>
		<td>작성일</td>
		<td>${boardDto.regdate}</td>
		</tr>
		<tr>
		<td>내용</td>
		<td colspan="3">${boardDto.contents}</td>
		</tr>
	</table>

</body>
</html>