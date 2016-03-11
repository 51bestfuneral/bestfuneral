package com.funeral.kris.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="level_wish_types")
public class LevelWishType implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="wish_type")
	private String wishType;

	@Id
	@Column(name="wish_type_level")
	private Integer wishTypeLevel;

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

	public Integer getWishTypeLevel() {
		return wishTypeLevel;
	}

	public void setWishTypeLevel(Integer wishTypeLevel) {
		this.wishTypeLevel = wishTypeLevel;
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
