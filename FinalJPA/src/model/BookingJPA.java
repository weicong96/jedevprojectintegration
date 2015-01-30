package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
@Table(name="booking")
public class BookingJPA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idbooking;

	private String bookDate;

	private String bookTime;

	private String bookVenue;

	private String fk_groupCode;

	private String fk_staffID;

    public BookingJPA() {
    }

	public int getIdbooking() {
		return this.idbooking;
	}

	public void setIdbooking(int idbooking) {
		this.idbooking = idbooking;
	}

	public String getBookDate() {
		return this.bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public String getBookTime() {
		return this.bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}

	public String getBookVenue() {
		return this.bookVenue;
	}

	public void setBookVenue(String bookVenue) {
		this.bookVenue = bookVenue;
	}

	public String getFk_groupCode() {
		return this.fk_groupCode;
	}

	public void setFk_groupCode(String fk_groupCode) {
		this.fk_groupCode = fk_groupCode;
	}

	public String getFk_staffID() {
		return this.fk_staffID;
	}

	public void setFk_staffID(String fk_staffID) {
		this.fk_staffID = fk_staffID;
	}

}