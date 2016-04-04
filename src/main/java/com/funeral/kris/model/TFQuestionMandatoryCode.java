package com.funeral.kris.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_f_question_mandatory_code")
public class TFQuestionMandatoryCode {
	@Id
	@GeneratedValue
	@Column(name = "mandatory_Id")
	private int mandatoryId;

	@Column(name = "mandatory_Code")

	private String mandatoryCode;

	@Column(name = "discription")

	private String discription;

	public int getMandatoryId() {
		return mandatoryId;
	}

	public void setMandatoryId(int mandatoryId) {
		this.mandatoryId = mandatoryId;
	}

	public String getMandatoryCode() {
		return mandatoryCode;
	}

	public void setMandatoryCode(String mandatoryCode) {
		this.mandatoryCode = mandatoryCode;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "create_date")

	private Date createDate;
	@Column(name = "updated_date")

	private Date updateDate;

}
