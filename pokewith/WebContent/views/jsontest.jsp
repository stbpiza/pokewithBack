<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<button onclick="test()" type="button">Ajax</button>
	<script> 
var obj = { "userId": "3268944226555507" }; 
function test() { 
	$.ajax({ 
		url: "<c:url value="/newsignjson" />", 
		type: "post", data: JSON.stringify(obj), 
		dataType: "json", 
		contentType: "application/json", 
		success: function(data) {
			console.log(data);
			alert("성공"); 
		}, 
		error: function(errorThrown) { 
			alert(errorThrown.statusText); 
		} 
	}); 
			
} 

</script>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</body>
</html>
