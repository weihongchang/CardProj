package tools.core;

public class MessageInfo {

	private String messageName;

	private int messageId;

	private MessageType type;

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

}

enum MessageType {
	REQUEST, RESPONSE;
}
