package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * The persistent class for the submission database table.
 * 
 */
@Entity
public class Submission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int submissionID;

	private String compiled;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSubmitted;

	private String description;

	private String icon;

	private String name;

	private String screenshot;

	private String sourceCode;

	private String video;

	private String additional;
	
	private String status;
	//bi-directional many-to-one association to Group
	
	@ManyToOne
	@JoinColumn(name="group_groupCode")
	private GroupJPA group;

	@OneToOne(mappedBy="submission_submissionID",cascade = CascadeType.ALL)
	private Fieldsupdated fieldsupdated;

	public Submission() {
		
	}

	public int getSubmissionID() {
		return this.submissionID;
	}

	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
	}

	public String getCompiled() {
		return this.compiled;
	}

	public void setCompiled(String compiled) {
		this.compiled = compiled;
	}

	public Date getDateSubmitted() {
		return this.dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenshot() {
		return this.screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}

	public String getSourceCode() {
		return this.sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public GroupJPA getGroup() {
		return this.group;
	}

	public void setGroup(GroupJPA group) {
		this.group = group;
	}

	public Fieldsupdated getFieldsupdated() {
		return this.fieldsupdated;
	}

	public void setFieldsupdated(Fieldsupdated fieldsupdated) {
		this.fieldsupdated = fieldsupdated;
	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}