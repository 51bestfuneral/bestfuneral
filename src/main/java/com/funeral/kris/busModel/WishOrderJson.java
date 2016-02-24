package com.funeral.kris.busModel;

import java.math.BigDecimal;
import java.util.List;

public class WishOrderJson {
     private Integer userId;
     private Integer wishOrderId;
     private BigDecimal totalPrice;
     private List<WishOrderDetailJson> detailJson;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getWishOrderId() {
		return wishOrderId;
	}
	public void setWishOrderId(Integer wishOrderId) {
		this.wishOrderId = wishOrderId;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<WishOrderDetailJson> getDetailJson() {
		return detailJson;
	}
	public void setDetailJson(List<WishOrderDetailJson> detailJson) {
		this.detailJson = detailJson;
	}
}