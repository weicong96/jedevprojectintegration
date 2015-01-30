package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the staff database table.
 * 
 */
@Entity
@Table(name="staff")
public class StaffJPA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idstaff;

	private String staffContactNo;

	private String staffEmailNo;

	private String staffName;

	private String staffType;

	//bi-directional many-to-one association to Conversation
	@OneToMany(mappedBy="staff")
	private Set<ConversationJPA> conversations;

    public StaffJPA() {
    }

	public String getIdstaff() {
		return this.idstaff;
	}

	public void setIdstaff(String idstaff) {
		this.idstaff = idstaff;
	}

	public String getStaffContactNo() {
		return this.staffContactNo;
	}

	public void setStaffContactNo(String staffContactNo) {
		this.staffContactNo = staffContactNo;
	}

	public String getStaffEmailNo() {
		return this.staffEmailNo;
	}

	public void setStaffEmailNo(String staffEmailNo) {
		this.staffEmailNo = staffEmailNo;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffType() {
		return this.staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public Set<ConversationJPA> getConversations() {
		return this.conversations;
	}

	public void setConversations(Set<ConversationJPA> conversations) {
		this.conversations = conversations;
	}
	
}