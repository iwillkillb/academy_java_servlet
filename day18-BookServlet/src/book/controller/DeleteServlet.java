package book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookShelf;
import book.exception.NotFoundException;
import book.vo.Book;

/**
 * detail.jsp에서 삭제 링크로 발생하는 요청(delete?bookId=XXX)을 처리하는 서블릿.
 * 
 */
@WebServlet({"/delete", "/main/delete"})
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 제품 상세 조회에서 삭제 링크를 통해 발생하는 get요청을 처리.
	 * 1. 요청 파라미터 bookId를 추출.
	 * 2. delete쿼리를 수행
	 * 3. 성공/실패 메시지
	 * 4. 성공/실패 뷰 선택 후 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 1. 한글 처리 (요청, 응답) : 여기서는 필수가 아님.
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		
		// 2. 모델 생성 : 삭제
		// (1) 요청 파라미터 추출
		String bookId = request.getParameter("bookId");
		
		// (2) Book 객체로 포장
		Book book = new Book(bookId);
		
		// (3) DB객체 선언/얻기
		BookShelf bookShelf = (BookShelf) getServletContext().getAttribute("bookShelf");
		
		// 3. view
		// (1) 관련 변수들 선언
		String view = null;
		String next = null;
		String message = null;
		
		try {
			// 2.(4) delete 수행
			bookShelf.delete(book);
			
			// 2.(5) 삭제 성공 메시지
			message = String.format("제품정보[%s] 삭제 성공.", book.getBookId());
			
		} catch (NotFoundException e) {
			// 2.(5) 삭제 실패 메시지
			message = e.getMessage();
			
			e.printStackTrace();
		}
		// 2.(6) 메시지 request에 속성으로 추가
		request.setAttribute("message", message);
		
		// 3. view 선택
		// (2) 1차 뷰 선택
		view = "/messageJsp";
		// (3) 2차 뷰 선택
		next = "main/list";
		request.setAttribute("next", next);
		
		// 4. 결정된 view로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);		
		reqd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
