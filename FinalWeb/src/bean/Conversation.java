package bean;

public class Conversation {
	private int idconversation;
	private String sender;
	private String reciever;
	private String staff_idstaff;
	private String group_teamCode;
	
	public int getIdconversation() {
		return idconversation;
	}
	public void setIdconversation(int idconversation) {
		this.idconversation = idconversation;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getStaff_idstaff() {
		return staff_idstaff;
	}
	public void setStaff_idstaff(String staff_idstaff) {
		this.staff_idstaff = staff_idstaff;
	}
	public String getGroup_teamCode() {
		return group_teamCode;
	}
	public void setGroup_teamCode(String group_teamCode) {
		this.group_teamCode = group_teamCode;
	}
	
	
}
