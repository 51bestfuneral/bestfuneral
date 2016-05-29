package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="wishlists")
public class Wishlist {
	
	@Id
	@GeneratedValue
	@Column(name="wishlist_id")
	private Integer wishlistId;

	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="ans_list_id")
	private String ansListId;
	
	@Column(name="comment")
	private String comment;

	@Column(name="user_id")
	private Integer userId;

	@Column(name="status")
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	@Column(name="level")
	private Integer level;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;

	@Column(name="original_price")
	private BigDecimal originalPrice;

	public Integer getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(Integer wishlistId) {
		this.wishlistId = wishlistId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getAnsListId() {
		return ansListId;
	}

	public void setAnsListId(String ansListId) {
		this.ansListId = ansListId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPirce(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
}

