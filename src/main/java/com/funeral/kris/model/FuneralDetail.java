package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="funeral_details")
public class FuneralDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="funeral_id")
	private String funeralId;

	@Id
	@Column(name="element")
	private String element;
	
	@Column(name="value")
	private String value;

	@Column(name="seq")
	private String sequence;

	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;

	public String getFuneralId() {
		return funeralId;
	}

	public void setFuneralId(String funeralId) {
		this.funeralId = funeralId;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
