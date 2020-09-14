<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
HttpSession ss = request.getSession();
String userId = (String)ss.getAttribute("userId");
String userName = (String)ss.getAttribute("userName");
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
if (window.location.hash === "#_=_"){
	console.log(111111);
    history.replaceState 
        ? history.replaceState(null, null, window.location.href.split("#")[0])
        : window.location.hash = "";
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
userId = <%=userId %><br>
<%=userName%>님 반갑습니다.<br>
회원가입 여부 체크해주세요<br>
<form action="/check" method="post">
<input type="submit" value="체크">
</form>
</body>
</html>