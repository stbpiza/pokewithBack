<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
HttpSession ss = request.getSession();
String userId = (String)ss.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/testlog" method="post">
		<select name="userId">
			<option value="3268944226555507">sun</option>
			<option value="1668589466621909">2eebug</option>
			<option value="1649416911892763">noarmy</option>
			<option value="1283902841946955">2020232421</option>
		</select>
		<input type="submit" value="확인">
	</form>
	현재 userId = <%=userId %>
</body>
</html>