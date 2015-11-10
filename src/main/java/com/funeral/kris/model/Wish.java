package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="wishs")
public class Wish {
	
	@Id
	@GeneratedValue
	@Column(name="wish_id")
	private String wishId;

	@Column(name="wish_name")
	private String wishName;
	
	@Column(name="wish_type")
	private String wishType;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="count")
	private Integer count;
	
	@Column(name="wish_desc")
	private String wishDesc;
	
	@Column(name="url")
	private String url;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;

	public String getWishId() {
		return wishId;
	}

	public void setWishId(String wishId) {
		this.wishId = wishId;
	}

	public String getWishName() {
		return wishName;
	}

	public void setWishName(String wishName) {
		this.wishName = wishName;
	}

	public String getWishType() {
		return wishType;
	}

	public void setWishType(String wishType) {
		this.wishType = wishType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getWishDesc() {
		return wishDesc;
	}

	public void setWishDesc(String wishDesc) {
		this.wishDesc = wishDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
