<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 글쓰기 화면</h1>
	<hr>
	
	<form name="mform">
	<table width = 900px border=1 cellspacing =0 cellpadding=0>
		<colgroup>
			<col width ="30%">
			<col width ="*">
		</colgroup>
		<tbody>
			<tr>
			<td>제목</td>
			<td><input type="text" name="title" size=40 maxlength=100 /></td>
			</tr>
			<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" size=40 maxlength=100 /></td>
			</tr>
			<tr>
			<td>내용</td>
			<td><textarea name="contents" cols="40"  row="5"></textarea></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan = "2">
					<a href="#none" onclick="goSave()">등록하기</a>
					&nbsp;&nbsp;&nbsp;
					<a href="#none" onclick="goSave()">취소하기</a>
				</td>
			</tr>
		</tbody>
	</table>		
	</form>

</body>
</html>

<script>
function goSave() {
	//폼 객체에 대한 참조를 가져온다.
	var frm = document.mform; //form태그에 속성에 name = mform (13번째줄에 있다)
	frm.method ="post"; //한글도 안깨지고, 많은 양의 데이터를 전송할 수 있다.
								  //중요한 자료는 무조건 post 방식으로
	frm.action ="/MVC1/index.do?cmd=board&job=save"; //서버쪽에서 처리를 받을 url정보전달
	frm.submit(); //서버로 정보를 보낸다.
	//서버쪽에서 index.do?cmd=board&job=save에 대한 처리가 이루어져야한다.
	
}

function goList() {
	//폼 객체에 대한 참조를 가져온다.
	var frm = document.mform; //form태그에 속성에 name = mform (13번째줄에 있다)
	frm.action ="/MVC1/index.do?cmd=board&job=list"; //서버쪽에서 처리를 받을 url정보전달
	frm.submit(); //서버로 정보를 보낸다.
	//서버쪽에서 index.do?cmd=board&job=save에 대한 처리가 이루어져야한다.
	
}

</script>