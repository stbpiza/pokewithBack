<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/reuser" method="post">
		<input type="text" name="nickname1" placeholder="닉네임1"> <input
			type="text" name="friendCode1" placeholder="친구코드1" pattern="\d{12}">
		<input type="text" name="nickname2" placeholder="닉네임2"> <input
			type="text" name="friendCode2" placeholder="친구코드2" pattern="\d{12}">
		<input type="text" name="nickname3" placeholder="닉네임3"> <input
			type="text" name="friendCode3" placeholder="친구코드3" pattern="\d{12}">
		<input type="text" name="nickname4" placeholder="닉네임4"> <input
			type="text" name="friendCode4" placeholder="친구코드4" pattern="\d{12}">
		<input type="text" name="nickname5" placeholder="닉네임5"> <input
			type="text" name="friendCode5" placeholder="친구코드5" pattern="\d{12}">
		<input type="submit" value="확인">
	</form>
</body>
</html>