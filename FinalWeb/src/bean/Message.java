package bean;

public class Message {
	private int idmessage;
	private String messageDetails;
	private String messageDate;
	private String messageTime;
	private int conversation_idconversation;
	private Conversation conversation;
	
	public int getIdmessage() {
		return idmessage;
	}
	public void setIdmessage(int idmessage) {
		this.idmessage = idmessage;
	}
	public String getMessageDetails() {
		return messageDetails;
	}
	public void setMessageDetails(String messageDetails) {
		this.messageDetails = messageDetails;
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	public String getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}
	public int getConversation_idconversation() {
		return conversation_idconversation;
	}
	public void setConversation_idconversation(int conversation_idconversation) {
		this.conversation_idconversation = conversation_idconversation;
	}
	public Conversation getConversation() {
		return conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}
