package book.view;

/**
 * 책의 내용, 목록, 메시지등의 출력을 담당하는 인터페이스
 * @author PC38206
 *
 */
public interface BookView {
	
	/**
	 * object로 전달된 내용을 출력한다.
	 * @param object
	 */
	void display(Object object);

}
