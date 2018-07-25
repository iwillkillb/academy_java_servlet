<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table, tr, th, td {
		border: 1px solid black;
	}
</style>
</head>
<body>

<h3>제품 전체 목록</h3>
<hr>
<table>
<!-- tr>(th*4) -->
	<tr>
		<th>제품코드</th>
		<th>제품이름</th>
		<th>가격</th>
		<th>재고</th>
	</tr>

<c:if test="${not empty products}">
	<c:forEach items="${products}" var="product">
		<tr>
			<td>${product.prodCode}</td>
			<td>
				<a href="detail?prodCode=${product.prodCode}">
					${product.prodName}
				</a>
			</td>
			<td>${product.price}</td>
			<td>${product.quantity}</td>
		</tr> 
	</c:forEach>
</c:if>

<c:if test="${empty products}">
	<c:forEach items="${products}" var="product">
		<tr>
			<td colspan="4">제품정보 없음.</td>
		</tr> 
	</c:forEach>
</c:if>

</table>

</body>
</html>