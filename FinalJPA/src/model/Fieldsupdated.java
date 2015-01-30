package model;

import java.io.Serializable;
import javax.persistence.*;
import model.Submission;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * The persistent class for the fieldsupdated database table.
 * 
 */
@Entity
public class Fieldsupdated implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFieldsUpdatedEntry;

	private String fields;

	@OneToOne
	@JoinColumn(name="submission_submissionID")
	private Submission submission_submissionID;

	public Fieldsupdated() {
	}

	public int getIdFieldsUpdatedEntry() {
		return this.idFieldsUpdatedEntry;
	}

	public void setIdFieldsUpdatedEntry(int idFieldsUpdatedEntry) {
		this.idFieldsUpdatedEntry = idFieldsUpdatedEntry;
	}

	public String getFields() {
		return this.fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public Submission getSubmission_submissionID() {
		return this.submission_submissionID;
	}

	public void setSubmission_submissionID(Submission submission_submissionID) {
		this.submission_submissionID = submission_submissionID;
	}

}