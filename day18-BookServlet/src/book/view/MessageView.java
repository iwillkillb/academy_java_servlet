package book.view;

public class MessageView implements BookView {

	@Override
	public void display(Object object) {
		// 일정의 양식의 응답 == String 타입
		String message = (String) object;
		System.out.println(message);
	}

}
