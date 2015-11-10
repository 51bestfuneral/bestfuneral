package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="wish_types")
public class WishType {
	
	@Id
	@Column(name="wish_type")
	private String cemeteryId;

	@Column(name="wish_type_desc")
	private String cemeteryName;
	
	@Column(name="necessary")
	private boolean cemeteryDesc;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;

	public String getCemeteryId() {
		return cemeteryId;
	}

	public void setCemeteryId(String cemeteryId) {
		this.cemeteryId = cemeteryId;
	}

	public String getCemeteryName() {
		return cemeteryName;
	}

	public void setCemeteryName(String cemeteryName) {
		this.cemeteryName = cemeteryName;
	}

	public boolean isCemeteryDesc() {
		return cemeteryDesc;
	}

	public void setCemeteryDesc(boolean cemeteryDesc) {
		this.cemeteryDesc = cemeteryDesc;
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
