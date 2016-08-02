package com.funeral.kris.busModel;

import java.math.BigDecimal;

public class WishOrderDetailJson {
    private Integer wishOrderId;
    private Integer orderDetailId;
    private Integer wishId;
    private String wishName;
    private Integer count;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private BigDecimal sumPirce;
    private String wishDesc;
    private String imgUrl;

	public Integer getWishId() {
		return wishId;
	}
	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}
	public Integer getWishOrderId() {
		return wishOrderId;
	}
	public void setWishOrderId(Integer wishOrderId) {
		this.wishOrderId = wishOrderId;
	}
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public String getWishName() {
		return wishName;
	}
	public void setWishName(String wishName) {
		this.wishName = wishName;
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
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	public BigDecimal getSumPirce() {
		return sumPirce;
	}
	public void setSumPirce(BigDecimal sumPirce) {
		this.sumPirce = sumPirce;
	}
	public String getWishDesc() {
		return wishDesc;
	}
	public void setWishDesc(String wishDesc) {
		this.wishDesc = wishDesc;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
