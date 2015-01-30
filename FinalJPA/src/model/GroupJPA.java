package model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the group database table.
 * 
 */
@Entity
@Table(name="group")
public class GroupJPA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String teamCode;

	private String teamName;

    @Lob()
	private String teamPic;
    @Transient
    private String groupCode;
    @Transient
    private String groupName;
    
	//bi-directional many-to-one association to Conversation
	@OneToMany(mappedBy="group")
	private Set<ConversationJPA> conversations;
	
	@OneToMany(mappedBy="group")
	private List<Submission> submissions;

	@OneToOne(mappedBy="group_groupCode")
	private Deadline deadline;
    public GroupJPA() {
    }
  

	public String getTeamCode() {
		return this.teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
		this.groupCode = teamCode;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
		this.groupName = teamName;
	}

	public String getTeamPic() {
		return this.teamPic;
	}

	public void setTeamPic(String teamPic) {
		this.teamPic = teamPic;
	}

	public Set<ConversationJPA> getConversations() {
		return this.conversations;
	}

	public void setConversations(Set<ConversationJPA> conversations) {
		this.conversations = conversations;
	}
	public String getGroupCode(){
		return teamCode;
	}
	public String getGroupName() {
		// TODO Auto-generated method stub
		return teamName;
	}

	public List<Submission> getSubmissions() {
		return this.submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}

	public Submission addSubmission(Submission submission) {
		getSubmissions().add(submission);
		submission.setGroup(this);

		return submission;
	}

	public Submission removeSubmission(Submission submission) {
		getSubmissions().remove(submission);
		submission.setGroup(null);

		return submission;
	}

	public Deadline getDeadline() {
		return this.deadline;
	}
	
	public void setDeadline(Deadline deadline) {
		this.deadline = deadline;
	}


	
}