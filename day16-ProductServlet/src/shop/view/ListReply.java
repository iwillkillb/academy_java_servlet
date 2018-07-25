package shop.view;

import java.util.List;

import shop.vo.Product;

/**
 * 제품 전체 목록을 보여주는 업무 진행의
 * 응답을 구현
 * @author PC38206
 *
 */
public class ListReply implements Reply {

	@Override
	public void reply(Object object) {
		// object 로 제품정보 목록이 넘어온다.
		List<Product> products = (List<Product>)object;
		for (Product product: products) {
			product.print();
		}

	}

}