<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/signup" method="post">
		<input type="text" name="nickname1" placeholder="닉네임" required>
		<input type="text" name="friendCode1" placeholder="친구코드" required
			pattern="\d{12}"> <input type="submit" value="확인">
	</form>
</body>
</html>