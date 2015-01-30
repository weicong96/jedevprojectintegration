package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import model.GroupJPA;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * The persistent class for the deadline database table.
 * 
 */
@Entity
public class Deadline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deadLine;
	
	private String reason;
	
	@Id
	@OneToOne
	@JoinColumn(name="group_groupCode")
	private GroupJPA group_groupCode;

	public Deadline() {
	}

	public Date getDeadLine() {
		return this.deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public GroupJPA getGroup_groupCode() {
		return this.group_groupCode;
	}

	public void setGroup_groupCode(GroupJPA group_groupCode) {
		this.group_groupCode = group_groupCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}