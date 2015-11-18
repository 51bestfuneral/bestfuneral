package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="wishlist_details")
public class WishlistDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="Wishlist_detail_id")
	private String wishlistDetailId;

	@Column(name="wishlist_id")
	private String wishlistId;

	@Column(name="wish_type")
	private String wishType;
	
	@Column(name="wish_id")
	private String wishId;
	
	@Column(name="count")
	private Integer count;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;

	public String getWishlistDetailId() {
		return wishlistDetailId;
	}

	public void setWishlistDetailId(String wishlistDetailId) {
		this.wishlistDetailId = wishlistDetailId;
	}

	public String getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(String wishlistId) {
		this.wishlistId = wishlistId;
	}

	public String getWishType() {
		return wishType;
	}

	public void setWishType(String wishType) {
		this.wishType = wishType;
	}

	public String getWishId() {
		return wishId;
	}

	public void setWishId(String wishId) {
		this.wishId = wishId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

