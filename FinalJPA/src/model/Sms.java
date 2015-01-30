package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the sms database table.
 * 
 */
@Entity
@Table(name="sms")
public class Sms implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idsms;

	private String contact;

	@Lob
	private String smsMessage;

	@Temporal(TemporalType.TIMESTAMP)
	private Date smsTimeRequested;

	private Timestamp smsTimeSent;

	private String purpose;
	public Sms() {
	}

	public int getIdsms() {
		return this.idsms;
	}

	public void setIdsms(int idsms) {
		this.idsms = idsms;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getSmsMessage() {
		return this.smsMessage;
	}

	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}

	public Date getSmsTimeRequested() {
		return this.smsTimeRequested;
	}

	public void setSmsTimeRequested(Date smsTimeRequested) {
		this.smsTimeRequested = smsTimeRequested;
	}

	public Timestamp getSmsTimeSent() {
		return this.smsTimeSent;
	}

	public void setSmsTimeSent(Timestamp smsTimeSent) {
		this.smsTimeSent = smsTimeSent;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

}