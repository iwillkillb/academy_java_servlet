<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2초 후에 명시된 url로 자동이동하는 meta태그 사용 -->
<meta http-equiv="refresh" content="2;url=${next}">
<title>시스템 메시지</title>
</head>
<body>

<%-- 
이 페이지로 이동되었을 때, request객체에 message라는 이름의 속성이 설정된 상태.
request 객체에 message라는 이름의 속성이 설정된 상태. message 속성을 EL을 이용.
 --%>
${message}
</body>
</html>