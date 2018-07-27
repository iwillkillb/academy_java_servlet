package book.view;

import java.util.List;

import book.vo.Book;


public class ListView implements BookView {

	@Override
	public void display(Object object) {
		// object 로 제품정보 목록이 넘어온다.
				List<Book> books = (List<Book>)object;
				for (Book book: books) {
					book.toString();
				}
	}

}
