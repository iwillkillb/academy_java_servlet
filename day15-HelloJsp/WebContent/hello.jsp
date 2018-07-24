<%-- 
	1. JSP 주석 : JSP가 서블릿으로 변환될 때 무시됨.
	2. <%@    : Directive Tag. 현재 페이지에 대한 설정 지정.
				이 페이지에서 필요한 import가 있으면 처리.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01 Hello JSP</title>
</head>
<body>
	<%!
		// 5. <%!는 Declaration Tag : 멤버변수 선언, 메소드 선언이 들어가는 위치.
		
		// 멤버 변수 선언.
		private int result;
		
		// int값 2개를 받아서 add()하고 결과를 리턴하는 메소드 정의.
		public int add(int x, int y) {
			return x + y;
		}
	%>
	
	<%
	// 3. <% ... : Scriptlet Tag : JSP안에서 순수 자바코드를 쓸 수 있는 태그.
	//						  	    너무 많이 사용하면 가독성 감소.
	//						  	    이 태그 안에는 완전한 자바 문장이 들어간다.
	//						  	    문장은 ;으로 종료되는 한 줄을 말한다.
	//						  	    (1) 할당문 : 변수에 값을 저장
	//						  	    (2) 메소드 호출문 : object.ToString()
	//						  	    (3) 제어구조 : if, while, for...
	//						  	    (4) 지역 변수 선언문
	//						  	    문장이 아닌 것(메소드 선언문, 클래스 선언문 등)은 안된다.
		int age = 55;
			
		List<Integer> ages = new ArrayList<>();
		ages.add(12);
		ages.add(20);
		ages.add(37);
		ages.add(45);
		ages.add(age);
		
		for (int age2: ages) {
			System.out.println("구성원 나이 : " + age2);
		
	%>
	<!-- 여기에는 화면에 반복 출력할 HTML 구성 -->
	<%-- 4. <%= : Expression Tag : 변수 값 하나를 출력할 때 사용. ;을 쓰지 않는다. --%>
			구성원 나이 : <%=age2 %> <br/>
	<%
		} // 스크립틀릿 안에서는 자바주석 사용 가능.
		// for 구조가 스크립틀릿 안에서 시작하므로, 닫을때도 스크립틀릿 안에서 닫혀야 한다.
		
		for (int idx = 0; idx < 5; idx++) {
		
	%>
	<!-- Scriptlet의 바깥부분. 출력할 HTML 구성 가능. -->
			안녕하세요, JSP! 반복실행 <%=idx %> <br/>
	<%
		}
	%>
	
	<%
		// 메소드 선언된 add()를 사용
		result = add(100, 200);
	%>
		add(100, 200) = <%=result %> <br/>
</body>
</html>