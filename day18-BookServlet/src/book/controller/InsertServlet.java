package book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookShelf;
import book.exception.DuplicateException;
import book.vo.Book;

/**
 * 신규 제품 등록을 처리하는 서블릿
 * 이 클래스에 GET 방식 / POST 방식 으로 진입할 때
 * 각기 다르게 작동
 * ---------------------------------------------------
 * GET  : 등록 받는 화면 이동
 * POST : 등록 화면에서 전달된 데이터를 DB insert 처리 
 */
@WebServlet({"/insert", "/main/insert"})
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * menu.jsp 에서 링크를 클릭했을 때 발생하는
	 * 신규 제품 등록 GET 요청을 처리한다.
	 * insert.jsp 로 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 뷰를 결정
		String view = "/insertJsp";
		
		// RequestDispatcher 로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
	}

	/**
	 * insert.jsp 에서 등록하기 버튼이 클릭되었을 때
	 * post 요청을 처리한다.
	 * 내부적으로 insert.jsp 의 form 으로 전달된 
	 * 파라미터를 추출하여 Book 객체로 포장하고
	 * insert 쿼리를 실행
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 한글처리
//		// (1) 요청 한글 처리
//		request.setCharacterEncoding("utf-8");
//		// (2) 응답 한글 처리
//		response.setContentType("text/html;charset=utf-8");
		
		// 2. 모델 생성
		// (1) insert.jsp 에서 넘어온 파라미터 추출
		String bookId = request.getParameter("bookId");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String isbn = request.getParameter("isbn");
		String publish = request.getParameter("publish");
		
		// (2) Book 타입으로 포장
		Book book = new Book(bookId, title, author, price, isbn, publish);
		
		// (3) DB 입력에 필요한 객체 선언
		BookShelf bookShelf = (BookShelf) getServletContext().getAttribute("bookShelf");
		
		// DB 입력 성공/실패 시 발생하는 메시지 변수
		String message = null;	
		
		// 3.(2) 메지시 출력 후 이동할 뷰페이지 
		String next = "";
		
		try {
			// (4) insert 쿼리 수행
			bookShelf.insert(book);
			message = String.format("제품 정보[%s]추가에 성공하였습니다."
					, book.getBookId());
			
			// 추가 성공 후 전체 목록으로 자동 이동
			next = "main/list";
			
		} catch (DuplicateException e) {
			message = String.format(e.getMessage());
			
			// 실패시 다시 입력하는 화면으로 자동 이동
			next = "main/insert";
			
			e.printStackTrace();
		}
		
		// (5) 추가 성공 / 실패에 따른 발생 메시지를
		//     request 에 속성으로 추가
		request.setAttribute("message", message);
		
		// 3. View 결정
		// (1) 추가 성공 / 실패 메시지를 출력할 1차 뷰
		String view = "/messageJsp";
		// (2) 2차 뷰 선택된 내용을 request 에 속성으로 추가
		request.setAttribute("next", next);
		
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
		
	}

}