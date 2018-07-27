package book.view;

public class ErrorView implements BookView {

	@Override
	public void display(Object object) {
		// 오류 발생
		String errorMsg = (String)object;
		System.err.println(errorMsg);
	}

}
