package com.funeral.kris.bean;

import java.math.BigDecimal;
import java.util.List;

import com.funeral.kris.busModel.CartlistJson;
import com.funeral.kris.busModel.ExpressBean;
import com.funeral.kris.busModel.WishListJson;
import com.funeral.kris.model.OrderDetail;

public class ShoppingCart {
	
	private Integer setWishOrderId;

	public Integer getSetWishOrderId() {
		return setWishOrderId;
	}

	public void setSetWishOrderId(Integer setWishOrderId) {
		this.setWishOrderId = setWishOrderId;
	}

	private WishListJson wishListJson;

	
	public WishListJson getWishListJson() {
		return wishListJson;
	}

	public void setWishListJson(WishListJson wishListJson) {
		this.wishListJson = wishListJson;
	}

	private ExpressBean expressBean;

	public ExpressBean getExpressBean() {
		return expressBean;
	}

	public void setExpressBean(ExpressBean expressBean) {
		this.expressBean = expressBean;
	}

	private Integer userId;
	private Integer count;
	private Integer payMethod;

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getGrossFee() {
		return grossFee;
	}

	public List<Integer> getSelectedCartDetailIdList() {
		return selectedCartDetailIdList;
	}

	public void setSelectedCartDetailIdList(List<Integer> selectedCartDetailIdList) {
		this.selectedCartDetailIdList = selectedCartDetailIdList;
	}

	public void setGrossFee(BigDecimal grossFee) {
		this.grossFee = grossFee;
	}

	public BigDecimal getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(BigDecimal expressFee) {
		this.expressFee = expressFee;
	}

	public BigDecimal getNetFee() {
		return netFee;
	}

	public void setNetFee(BigDecimal netFee) {
		this.netFee = netFee;
	}

	public Integer getAllSelected() {
		return allSelected;
	}

	public void setAllSelected(Integer allSelected) {
		this.allSelected = allSelected;
	}

	private BigDecimal grossFee;
	private BigDecimal expressFee;
	private BigDecimal netFee;
	private BigDecimal setFee;

	public BigDecimal getSetFee() {
		return setFee;
	}

	public void setSetFee(BigDecimal setFee) {
		this.setFee = setFee;
	}



	
	public List<OrderDetail> getOrderDetailForWishList() {
		return orderDetailForWishList;
	}

	public void setOrderDetailForWishList(List<OrderDetail> orderDetailForWishList) {
		this.orderDetailForWishList = orderDetailForWishList;
	}

	public List<OrderDetail> getOrderDetailForCartList() {
		return orderDetailForCartList;
	}

	public void setOrderDetailForCartList(List<OrderDetail> orderDetailForCartList) {
		this.orderDetailForCartList = orderDetailForCartList;
	}

	public List<CartlistJson> getCartlistJsonList() {
		return cartlistJsonList;
	}

	public void setCartlistJsonList(List<CartlistJson> cartlistJsonList) {
		this.cartlistJsonList = cartlistJsonList;
	}

	private List<Integer> selectedCartDetailIdList;
	private List<CartlistJson> cartlistJsonList;
	private List<OrderDetail> orderDetailForWishList;
	private List<OrderDetail> orderDetailForCartList;
	private Integer allSelected;

}
