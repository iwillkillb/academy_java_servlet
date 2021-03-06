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
import shop.exception.NotFoundException;
import shop.vo.Product;

/**
 * 제품코드(prodCode) 를 파라미터 받아서
 * 제품 한 건의 정보를 상세조회하고
 * 상세보기 뷰로 이동하는 서블릿
 */
@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request
			           , HttpServletResponse response) 
			        		   throws ServletException, IOException {
		// 1. 한글처리
		// (1) 요청 한글 처리
		request.setCharacterEncoding("utf-8");
		// (2) 응답 한글 처리
		response.setContentType("text/html;charset=utf-8");
		
		// 2. 모델 생성
		// (1) 화면에서 넘어온 파라미터 추출
		//     조회할 제품 코드
		//     list.jsp 의 제품 이름을 클릭했을 때 넘어온 값
		String prodCode = request.getParameter("prodCode");
		
		// (2) 추출한 제품코드를 가지는 Product 객체로 포장
		Product product = new Product(prodCode);
		
		// (3) DB 조회에 사용할 객체 준비
		GeneralWarehouse warehouse;
		warehouse = getWarehouse("mybatis");
		
		// 3. View 객체 선언
		String view = null;
		String next = null;
		String message = null;
		
		try {
			// 2.(4) DB 에서 1건 상세 조회
			Product found = warehouse.get(product);
			
			// 2.(5) 조회된 상제 정보를 request 에 속성 추가
			request.setAttribute("product", found);
			
			// 3.(1) 1차 뷰 선택
			view = "detailJsp";
			
		} catch (NotFoundException e) {
			// 조회하려는 제품이 없을 때 메시지 생성
			message = e.getMessage();
			
			// request 에 속성 추가
			request.setAttribute("message", message);
			
			// 3.(1) 1차 뷰 선택
			view = "messageJsp";
			
			// 3.(2) 2차 뷰 선택 & 속성으로 추가
			next = "list";
			request.setAttribute("next", next);
			
			e.printStackTrace();
		}
		
		// 3.(3) 모델에 맞는 뷰를 생성 후 이동
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request
			            , HttpServletResponse response) 
			            		throws ServletException, IOException {
		doGet(request, response);
	}

}