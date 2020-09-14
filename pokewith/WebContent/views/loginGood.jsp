<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
HttpSession ss = request.getSession();
String userId = (String)ss.getAttribute("userId");
String nickname1 = (String)ss.getAttribute("nickname1");
String u_like = (String)ss.getAttribute("u_like");
String u_hate = (String)ss.getAttribute("u_hate");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=nickname1%>님 환영합니다<br>
좋아요 : <%=u_like %><br>
싫어요 : <%=u_hate %>
</body>
</html>