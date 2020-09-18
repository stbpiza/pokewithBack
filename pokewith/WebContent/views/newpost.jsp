<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
HttpSession ss = request.getSession();
String userId = (String)ss.getAttribute("userId");
String m = (String)request.getAttribute("m");
if (m==null){
	m = "";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	레이드 초대 등록 페이지 입니다.
	<br>
	<form action="/index" method="post">
		<input type="hidden" name="userId" value=<%=userId%>> <input
			type="text" name="pokemon" placeholder="포켓몬번호" required
			pattern="\d{1,4}"> <input type="text" name="raidLevel"
			placeholder="레이드레벨" required> <input type="time"
			name="startTime" placeholder="시작시간" required> <input
			type="time" name="endTime" placeholder="종료시간" required> <input
			type="text" name="nPass" placeholder="프리미엄패스" required
			pattern="\d{1}"> <input type="text" name="rPass"
			placeholder="리모트패스" required pattern="\d{1}"> <input
			type="text" name="minLevel" placeholder="최소레벨" pattern="\d{2}">
		<input type="submit" value="확인">
	</form>
	<%=m %>
</body>
</html>