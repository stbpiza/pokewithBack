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
var obj = { "p_id": "6", "c_id" : "{'9', '10'}" }; 
function test() { 
	$.ajax({ 
		url: "<c:url value="/mypost" />", 
		type: "put", data: JSON.stringify(obj), 
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
