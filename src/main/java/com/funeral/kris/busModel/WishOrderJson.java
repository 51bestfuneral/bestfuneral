package com.funeral.kris.busModel;

import java.math.BigDecimal;
import java.util.List;

public class WishOrderJson {
     private Integer userId;
     private Integer wishOrderId;
     private Integer statusId;
     private String statusDisception;
     private String style;
     private String orderNo;
     public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getOrderId() {
		return OrderId;
	}
	public void setOrderId(Integer orderId) {
		OrderId = orderId;
	}
	private Integer OrderId;


     public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusDisception() {
		return statusDisception;
	}
	public void setStatusDisception(String statusDisception) {
		this.statusDisception = statusDisception;
	}
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