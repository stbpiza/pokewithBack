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
	레이드 초대 참여 페이지 입니다.
	<br>
	<form action="/index/comment" method="post">
		<input type="hidden" name="userId" value=<%=userId%>> <input
			type="text" name="p_id" placeholder="글번호" required
			pattern="\d{1,4}"> <input type="text" name="checkNum"
			placeholder="계정선택" required> 
		<input type="submit" value="확인">
	</form>
	<%=m %>
</body>
</html>