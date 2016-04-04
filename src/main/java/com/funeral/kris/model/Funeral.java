package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="funerals")
public class Funeral {
	
	@Id
	@Column(name="funeral_id")
	private String funeralId;

	@Column(name="user_id")
	private String userId;
	
	@Column(name="total_price")
	private String totalPrice;

	@Column(name="wishlist_id")
	private String WishlistId;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getWishlistId() {
		return WishlistId;
	}

	public void setWishlistId(String wishlistId) {
		WishlistId = wishlistId;
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
