package bean;

public class Feedback {
	private int idfeedback;
	private String designQns;
	private String friendlyQns;
	private String favourFeatures;
	private String comments;
	private String fk_groupCode;
	
	public int getIdfeedback() {
		return idfeedback;
	}
	public void setIdfeedback(int idfeedback) {
		this.idfeedback = idfeedback;
	}
	public String getDesignQns() {
		return designQns;
	}
	public void setDesignQns(String designQns) {
		this.designQns = designQns;
	}
	public String getFriendlyQns() {
		return friendlyQns;
	}
	public void setFriendlyQns(String friendlyQns) {
		this.friendlyQns = friendlyQns;
	}
	public String getFavourFeatures() {
		return favourFeatures;
	}
	public void setFavourFeatures(String favourFeatures) {
		this.favourFeatures = favourFeatures;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFk_groupCode() {
		return fk_groupCode;
	}
	public void setFk_groupCode(String fk_groupCode) {
		this.fk_groupCode = fk_groupCode;
	}
	
}
