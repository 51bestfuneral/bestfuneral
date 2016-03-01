package com.funeral.kris.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_order_detail")
public class OrderDetail {

	@Id
	@GeneratedValue
	@Column(name="order_detail_id")
	private Integer orderDetailId;

	public String getWishName() {
		return wishName;
	}

	public void setWishName(String wishName) {
		this.wishName = wishName;
	}

	@Column(name="wish_id")
	private Integer wishId;
	public Integer getWishListId() {
		return wishListId;
	}

	public void setWishListId(Integer wishListId) {
		this.wishListId = wishListId;
	}

	@Column(name="wish_name")
	private String wishName;

	@Column(name="wish_list_id")
	private Integer wishListId;
	
	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name="source_id")
	private Integer sourceId;
	
	@Column(name="order_id")
	private Integer orderId;

	@Column(name="price")
	private BigDecimal price;

	@Column(name="original_price")
	private BigDecimal originalPrice;

	@Column(name="count")
	private Integer count;

	@Column(name="created_date")
	private Date createdDate;

	@Column(name="updated_date")
	private Date updatedDate;

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Integer getWishId() {
		return wishId;
	}

	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}
