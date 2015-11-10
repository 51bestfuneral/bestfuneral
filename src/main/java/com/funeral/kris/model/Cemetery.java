package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="wish_types")
public class Cemetery {
	
	@Id
	@GeneratedValue
	@Column(name="cemetery_id")
	private String cemeteryId;

	@Column(name="cemetery_name")
	private String cemeteryName;
	
	@Column(name="cemetery_desc")
	private String cemeteryDesc;
	
	@Column(name="price")
	private String price;
	
	@Column(name="address")
	private String address;
	
	@Column(name="url")
	private String url;
	
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

	public String getCemeteryDesc() {
		return cemeteryDesc;
	}

	public void setCemeteryDesc(String cemeteryDesc) {
		this.cemeteryDesc = cemeteryDesc;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
