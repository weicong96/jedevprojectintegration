package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the submissionchanged database table.
 * 
 */
@Entity
public class Submissionchanged implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int submissionID;

	@Lob
	private String additional;

	@Lob
	private String compiled;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSubmitted;

	@Lob
	private String description;

	private String group_groupCode;

	@Lob
	private String icon;

	private String name;

	@Lob
	private String screenshot;

	@Lob
	private String sourceCode;

	@Lob
	private String video;

	@Column(name="version")
	private int version;
	
	@Column(name="originalSubID")
	private int originalSubID;
	
	public Submissionchanged() {
	}

	public Submissionchanged(Submission sub) {
		//String name,int submissionID, String additional, String compiled,String dateSubmitted, String description, String icon, , String screenshot,String sourceCode, String video,String group_groupCode
		this.originalSubID = sub.getSubmissionID();
		this.name = sub.getName();
		this.description = sub.getDescription();
		this.compiled = sub.getCompiled()+"old";
		this.icon = sub.getIcon()+"old";
		this.screenshot = sub.getScreenshot()+"old";
		this.sourceCode = sub.getSourceCode()+"old";
		this.video = sub.getVideo()+"old";
		this.dateSubmitted = sub.getDateSubmitted();
		this.additional = sub.getAdditional();
		this.group_groupCode = sub.getGroup().getGroupCode();
	}
	public int getSubmissionID() {
		return this.submissionID;
	}

	public void setSubmissionID(int submissionID) {
		this.submissionID = submissionID;
	}

	public String getAdditional() {
		return this.additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
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

	public String getGroup_groupCode() {
		return this.group_groupCode;
	}

	public void setGroup_groupCode(String group_groupCode) {
		this.group_groupCode = group_groupCode;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getOriginalSubID() {
		return originalSubID;
	}

	public void setOriginalSubID(int originalSubID) {
		this.originalSubID = originalSubID;
	}

}