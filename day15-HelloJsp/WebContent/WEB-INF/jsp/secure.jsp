<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02 Secure JSP</title>
</head>
<body>
	WEB-INF 아래 위치하는 안전한 경로의 JSP입니다.<br/>
	
	<% for (int idx = 0; idx < 5; idx++) { %>
		안녕하세요, 여기는 안전한 위치의 JSP입니다. <%= idx %>
	<% } %>
	
</body>
</html>