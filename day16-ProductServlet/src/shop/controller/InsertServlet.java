package shop.controller;

import static shop.factory.WarehouseFactory.getWarehouse;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GeneralWarehouse;
import shop.exception.DuplicateException;
import shop.vo.Product;

/**
 * 신규 제품 등록을 처리하는 서블릿
 * 이 클래스에 GET방식 / POST방식으로 진입할 때
 * 각기 다르게 작동
 * 
 * GET : 등록받는 화면 이동
 * POST : 등록 화면에서 전달된 데이터를 DB insert 처리
 * 
 * @author PC38208
 *
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * menu.jsp에서 링크를 클릭했을 때 발생하는 신규 제품 등록 요청을 처리한다. GET 처리. insert.jsp로 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 뷰를 결정
		String view = "insertJsp";
		
		// RequestDispatcher로 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
	}

	/**
	 * insert.jsp에서 등록하기 버튼이 클릭되었을 때 post요청을 처리한다.
	 * 내부적으로 insert.jsp의 form으로 전달된 파라미터를 추출하여 Product객체로 포장하고 insert 쿼리를 실행
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글처리
		// (1) 요철 한글 처리
		request.setCharacterEncoding("utf-8");
		// (2) 응답 한글 처리
		response.setContentType("text/html;charset=utf-8");
		
		// 2. 모델 생성
		// (1) insert.jsp에서 넘어온 파라미터 추출
		String prodCode = request.getParameter("prodCode");
		String prodName = request.getParameter("prodName");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		// (2) Product 타입으로 포장
		Product product = new Product(prodCode, prodName, price, quantity);
		
		// (3) DB입력에 필요한 객체들
		//  - 2 메시지 출력 후 이동할 뷰페이지
		String next = "";
		
		// DB입력 성공/실패 시 발생하는 메시지
		String message = null;
		
		GeneralWarehouse warehouse;
		warehouse = getWarehouse("mybatis");
		
		// (4) INSERT 쿼리 수행
		try {
			warehouse.add(product);
			message = String.format("제품정보[%s] 추가 성공", product.getProdCode());
			
			// 추가 성공 후 전체 목록으로 자동 이동
			next = "list";
		} catch (DuplicateException e) {
			message = String.format("제품정보[%s] 추가 실패", product.getProdCode());
			
			// 실패 시 다시 입력하는 화면으로 자동 이동
			next = "insert";
			e.printStackTrace();
		}
		
		
		
		
		// (5) 추가 성공/실패에 따른 발생 메시지를 request에 속성으로 추가
		request.setAttribute("message", message);
		
		
		// 3. View 결졍
		// (1) 추가 성공 / 실패 메시지를 출력할 1차 뷰
		String view = "messageJsp";
		
		request.setAttribute("next", next);
		
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		reqd.forward(request, response);
	}

}
