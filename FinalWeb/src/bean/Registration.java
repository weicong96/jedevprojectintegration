package bean;

public class Registration {
	private String memberName;
	private String gender;
	private String dob;
	private String nric;
	private String contactNo;
	private String tic;
	private String foodPre;
	private String foodOtherCom;
	private String emailAdd;
	private String attendance;
	private String leaderStatus;
	public Registration (){
		
	}
	public Registration(String memberName, String gender, String dob, String nric,
			String contactNo, String tic, String foodPre, String emailAdd,
			String attendance,String leaderStatus) {
		super();
		this.memberName = memberName;
		this.gender = gender;
		this.dob=dob;
		this.nric = nric;
		this.contactNo = contactNo;
		this.tic = tic;
		this.foodPre = foodPre;
		this.emailAdd = emailAdd;
		this.attendance = attendance;
		this.leaderStatus = leaderStatus;
	}
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getTic() {
		return tic;
	}
	public void setTic(String tic) {
		this.tic = tic;
	}
	public String getFoodPre() {
		return foodPre;
	}
	public void setFoodPre(String foodPre) {
		this.foodPre = foodPre;
	}
	public String getEmailAdd() {
		return emailAdd;
	}
	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getDOB() {
		return dob;
	}
	public void setDOB(String dob) {
		this.dob = dob;
	}
	public String getFoodOtherCom() {
		return foodOtherCom;
	}
	public void setFoodOtherCom(String foodOtherCom) {
		this.foodOtherCom = foodOtherCom;
	}
	public String getLeaderStatus() {
		return leaderStatus;
	}
	public void setLeaderStatus(String leaderStatus) {
		this.leaderStatus = leaderStatus;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	

}
