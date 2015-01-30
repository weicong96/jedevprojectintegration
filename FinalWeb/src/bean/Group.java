package bean;

import java.util.ArrayList;

public class Group {
	private String teamCode;
	private String teamName;
	private String teamPic;
	private ArrayList<Registration> students;
	public Group(){
		
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamPic() {
		return teamPic;
	}
	public void setTeamPic(String teamPic) {
		this.teamPic = teamPic;
	}
	public ArrayList<Registration> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Registration> students) {
		this.students = students;
	}
	

}
