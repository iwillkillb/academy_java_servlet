package shop.factory;

import shop.view.ErrorReply;
import shop.view.ListReply;
import shop.view.MessageReply;
import shop.view.ProductReply;
import shop.view.Reply;

/**
 * 매니저가 특정 응답을 할 때,
 * 응답 객체를 Reply 타입으로 생성하는 클래스
 * @author PC38206
 *
 */
public class ReplyFactory {

	public static Reply getReply(String type) {
		Reply reply = null;
		
		if ("list".equals(type)) {
			reply = new ListReply();
			
		} else if ("message".equals(type)) {
			reply = new MessageReply();
			
		} else if ("error".equals(type)) {
			reply = new ErrorReply();
			
		} else if ("single".equals(type)) {
			reply = new ProductReply();
		}
		
		return reply;
	}
}
