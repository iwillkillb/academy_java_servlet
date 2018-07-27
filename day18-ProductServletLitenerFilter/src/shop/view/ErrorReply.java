package shop.view;

/**
 * 찾는 제품이 없거나 하는 등의 
 * 비정상 상황에 대한 응답을 구현
 * @author PC38206
 *
 */
public class ErrorReply implements Reply {

	@Override
	public void reply(Object object) {
		// 비정상 상황의 응답은 String 타입을 발생
		String errorMsg = (String)object;
		System.err.println(errorMsg);
	}

}