package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the extrafields database table.
 * 
 */
@Entity
@Table(name="extrafields")
public class Extrafield implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idadditionalSettings;

	private String camelCaseName;

	@Lob
	private String format;

	@Lob
	private String name;

	private String required;

	public Extrafield() {
	}	

	public int getIdadditionalSettings() {
		return this.idadditionalSettings;
	}

	public void setIdadditionalSettings(int idadditionalSettings) {
		this.idadditionalSettings = idadditionalSettings;
	}

	public String getCamelCaseName() {
		return this.camelCaseName;
	}

	public void setCamelCaseName(String camelCaseName) {
		this.camelCaseName = camelCaseName;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequired() {
		return this.required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

}