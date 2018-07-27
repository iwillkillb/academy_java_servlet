<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="book.vo.Book" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 조회</title>
<style type="text/css">
	table, tr, th, td {
		border: 1px solid black;
	}
</style>
</head>
<body>
<h3>도서 상세 조회</h3>
<hr>
<table>
	<tr>
		<th>아이디</th>
		<td>${book.bookId}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${book.title}</td>
	</tr>
	<tr>
		<th>저자</th>
		<td>${book.author}</td>
	</tr>
	<tr>
		<th>가격</th>
		<td>${book.price}</td>
	</tr>
	<tr>
		<th>ISBN</th>
		<td>${book.isbn}</td>
	</tr>
	<tr>
		<th>출판사</th>
		<td>${book.publish}</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<a href="${contextPath}/main/list">목록보기</a>
			<a href="${contextPath}/main/update?prodCode=${book.prodCode}">수정</a>
			<a href="${contextPath}/main/delete?prodCode=${book.prodCode}">삭제</a>
		</td>
	</tr>
</table>
</body>
</html>