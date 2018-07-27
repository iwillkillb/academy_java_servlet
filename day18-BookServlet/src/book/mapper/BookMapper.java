package book.mapper;

import java.util.List;

import book.vo.Book;

public interface BookMapper {
	int insert(Book book);
	Book selectOne(Book book);
	int update(Book book);
	int deleteOne(Book book);
	List<Book> selectAll();
	String isExists(Book book);
}
