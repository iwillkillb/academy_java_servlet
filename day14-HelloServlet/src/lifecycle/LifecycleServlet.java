package lifecycle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿의 라이프사이클 메소드
 * init(), service(), destroy()를 테스트하는 서블릿.
 * @author PC38208
 *
 */
@WebServlet({ "/lifecycle" })
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count = 0;

	/**
	 * 웹 컨테이너(tomcat)에 의해 서블릿 초기화과정에서 최초로 1회만 호출되는 라이프 사이클 메소드
	 * 파라미터로 전달되는 config는 웹 컨테이너에 의해 자동으로 생성되어 전달된다.
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Lifecycle Servlet 인스턴스 생성 후");
		System.out.println("init() 메소드가");
		System.out.println("웹 컨테이너(tomcat)에 의해");
		System.out.println("자동으로 호출된다.");
	}

	/**
	 * 웹 컨테이너(tomcat)에 의해 서비스 종료 과정에서 마지막 1회만 호출되는 라이프 사이클 메소드
	 */
	public void destroy() {
		System.out.println("Lifecycle Servlet 종료 직전");
		System.out.println("destroy() 메소드가");
		System.out.println("웹 컨테이너(tomcat)에 의해");
		System.out.println("자동으로 호출된다.");
	}

	/**
	 * 응답횟수를 출력하는 간단한 서비스 메소드
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 카운트 추가
		count++;
		
		// 1. 응답 문서 한글 설정
		response.setContentType("text/html;charset=utf-8");
		
		// 2. 출력
		
		// (1) 기본 출력
		System.out.println("응답 횟수 : " + count);
		
		// (2) 브라우저 출력
		PrintWriter out = response.getWriter();
		out.println("<HTML><BODY>");
		out.println("요청 횟수 : " + count + "<br/>");
		out.println("<BODY><HTML>");
		
		// 3. 닫기
		out.close();
	}

}
