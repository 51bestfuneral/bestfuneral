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
	private String wishType;

	@Column(name="wish_type_desc")
	private String wishTypeDesc;

	@Column(name="necessary")
	private boolean necessary;

	@Column(name="level")
	private Integer level;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;

	public String getWishType() {
		return wishType;
	}

	public void setWishType(String wishType) {
		this.wishType = wishType;
	}

	public String getWishTypeDesc() {
		return wishTypeDesc;
	}

	public void setWishTypeDesc(String wishTypeDesc) {
		this.wishTypeDesc = wishTypeDesc;
	}

	public boolean isNecessary() {
		return necessary;
	}

	public void setNecessary(boolean necessary) {
		this.necessary = necessary;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
