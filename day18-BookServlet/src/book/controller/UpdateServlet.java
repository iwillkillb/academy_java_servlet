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
 * 제품 1건 수정 요청 처리하는 서블릿
 * 
 * 1. GET : detail.jsp에서 [수정] 링크를 통해 들어온 요청
 *          - 현재 상세보기 중이던 제품을 조회하여 수정할 수 있는 화면인 update.jsp로 전달.
 *          - 수정을 위한 화면 이동.
 *          - 이미 있는 화면을 요청하기에 GET으로 처리.
 * 
 * 2. POST : update.jsp에서 수정된 내용을 [저장]버튼을 통해
 *           들어온 요청을 처리.
 *           - 변경된 입력 내용을 실제 update 쿼리를 통해 수행하여 DB에 영구반영.
 *           - 수정 성공 / 실패를 알리는 메시지 발생.
 *           - 메시지를 처리할 뷰 선택.
 */
@WebServlet({"/update", "/main/update"})
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * datail.jsp에서 update?prodCode=XXX으로 발생한 GET요청을 처리.
	 * 1. 전달된 요청 파라미터(prodCode)를 추출.
	 * 2. 해당 제품 정보 조회.
	 * 3. 조회된 정보를 request 추가.
	 * 4. 수정 가능한 뷰를 선택 후 화면 이동.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글 처리 (요청, 응답) : 여기서는 필수가 아님.
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		
		// 2. 모델 생성
		// (1) 전달된 요청 파라미터(prodCode)를 추출.
		String prodCode = request.getParameter("prodCode");
		// (2) prodCode만으로 Book 포장
		Book Book = new Book(prodCode);
		
		// (3). DB에 사용할 객체 준비.
		BookShelf bookShelf = (BookShelf) getServletContext().getAttribute("bookShelf");
		
		// 3. View 선택
		// (1) view 저장 변수 선언
		String view = null;
		String next = null;
		String message = null;
		try {
			// 2.(4) 수정을 위한 제품 정보 조회.
			Book found = bookShelf.select(Book);
			
			// 2.(5) request에 수정제품 정보 속성 추가
			request.setAttribute("Book", found);
			
			// 3.(2) view 선택
			view = "/updateJsp";
			
		} catch (NotFoundException e) {
			// 2.(6) 수정 제품코드가 없으면 실패메시지
			message = e.getMessage();
			request.setAttribute("message", message);
			
			// 3.(2) view 선택
			view = "/messageJsp";
			
			// 3.(3) 2차 뷰 선택
			next = "list";
			request.setAttribute("next", next);
			
			e.printStackTrace();
		}
		
		// 4. 결정된 뷰로 화면 이동.
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		reqd.forward(request, response);
	}

	/**
	 * update.jsp에서 form의 submit이 일어났을 때 POST로 요청되는 저장 처리.
	 * 1. 수정할 전체 제품 정보의 요청 파라미터 추출
	 * 2. 변경내용으로 update 쿼리를 수행
	 * 3. 수정 성공 / 실패 뷰 처리
	 * 4. 성공 / 실패 뷰 처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 1. 한글 처리 (수정 데이터는 제품 이름에 한글이 존재하므로.)
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		
		// 2. 모델 생성
		// (1) 요철 파라미터 추출
		String bookId = request.getParameter("bookId");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String isbn = request.getParameter("isbn");
		String publish = request.getParameter("publish");
		
		// (2) 요청 파라미터 Book로 포장
		Book book = new Book(bookId, title, author, price, isbn, publish);
		
		// (3) update 수행을 위하여 DB객체 얻기
		BookShelf bookShelf = (BookShelf) getServletContext().getAttribute("bookShelf");

		// 3. view
		// (1) 관련 변수 선언
		String view = null;
		String next = null;
		String message = null;
		
		try {
			// 2.(4) update 수행
			bookShelf.update(book);
			
			// 2.(5) 메시지 발생 - 성공
			message = String.format("제품정보[%s] 수정 성공.", book.getBookId());
			
			// 3.(3) 2차 뷰 선택 : 수정된 정보 상세보기 진입
			next = "main/detail?prodCode=" + bookId;
			
		} catch (NotFoundException e) {
			// 2.(5) 메시지 발생 - 실패
			message = e.getMessage();
			
			// 3.(3) 수정 실패시 2차 뷰 : 목록으로 진입
			next = "main/list";
			
			e.printStackTrace();
		}
		
		// 2.(6) 메시지 request에 속성으로 추가
		request.setAttribute("message", message);
		
		// 3. view 선택
		// (2) 수정 실패 / 성공 모두 messageJsp로 전송
		view = "/messageJsp";
		
		// (4) 2차 뷰 request에 속성으로 추가
		request.setAttribute("next", next);
		
		// 4. 결정된 view로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		reqd.forward(request, response);
	}

}
