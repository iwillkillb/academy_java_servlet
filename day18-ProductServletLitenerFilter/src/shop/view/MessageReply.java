package shop.view;

/**
 * 매니저가 제품 정보를 등록, 수정, 삭제
 * 한 후 일정한 양식의 응답을 구현
 * @author PC38206
 *
 */
public class MessageReply implements Reply {

	@Override
	public void reply(Object object) {
		// 일정의 양식의 응답 == String 타입
		String message = (String)object;
		System.out.println(message);
	}

}