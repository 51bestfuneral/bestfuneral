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
@Table(name = "wishlist_details")
public class WishlistDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "Wishlist_detail_id")
	private Integer wishlistDetailId;

	@Column(name = "wishlist_id")
	private Integer wishlistId;

	@Column(name = "wish_type")
	private String wishType;

	@Column(name = "wish_id")
	private Integer wishId;
	
	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_id")
	private Integer sourceId;

	@Column(name = "count")
	private Integer count;
	@Column(name = "selected")
	private Integer selected;

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	@Column(name = "price")
	private BigDecimal price;
	
	public BigDecimal getSelectedPrice() {
		return selectedPrice;
	}

	public void setSelectedPrice(BigDecimal selectedPrice) {
		this.selectedPrice = selectedPrice;
	}

	@Column(name = "selected_price")
	private BigDecimal selectedPrice;

	@Column(name = "original_price")
	private BigDecimal originalPrice;

	@Column(name = "createdate")
	private Date createDate;

	@Column(name = "updateddate")
	private Date updatedDate;

	public Integer getWishlistDetailId() {
		return wishlistDetailId;
	}

	public void setWishlistDetailId(Integer wishlistDetailId) {
		this.wishlistDetailId = wishlistDetailId;
	}

	public Integer getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(Integer wishlistId) {
		this.wishlistId = wishlistId;
	}

	public String getWishType() {
		return wishType;
	}

	public void setWishType(String wishType) {
		this.wishType = wishType;
	}

	public Integer getWishId() {
		return wishId;
	}

	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
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

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
}
