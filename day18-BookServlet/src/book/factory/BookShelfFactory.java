package book.factory;

import book.dao.BookShelf;
import book.dao.MybatisBookShelf;

public class BookShelfFactory {
	public static BookShelf getBookShelf(String type) {
		BookShelf bookShelf = null;
		if ("mybatis".equals(type)) {
			bookShelf = new MybatisBookShelf();
		}
		return bookShelf;
	}
}
