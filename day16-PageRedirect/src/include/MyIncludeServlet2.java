package include;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 페이지 이동 방식 중 include방식을 이용하여 페이지 이동을 테스트하는 클래스.
 * -----------------------------------------------------------
 * include 방식으로 페이지를 이동시키면 이동된 두번째 서블릿의 실행결과를 이 서블릿이
 * 다시 받아서 포함하여 여기서 응답을 일으킨다.
 * 
 * @author PC38208
 *
 */
@WebServlet("/include2")
public class MyIncludeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 응답 객체에 한글 설정
		response.setContentType("text/html;charset=utf-8");
		
		// 1. request 객체에 name 속성을 추가
		request.setAttribute("name", "전현찬");
		
		// 2. include로 next 서블릿 이동
		// (1) RequestDispatcher를 얻어냄.
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher("nextJsp");
		
		// (2) 얻어진 dispatcher 객체에 include() 적용
		//     이 때, 최초의 request, response를 전달
		reqd.include(request, response);
	}

}
