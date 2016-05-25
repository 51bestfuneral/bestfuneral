package com.funeral.kris.bean;

import java.math.BigDecimal;
import java.util.List;

import com.funeral.kris.busModel.ExpressBean;
import com.funeral.kris.model.OrderDetail;

public class ShoppingCart {

	private ExpressBean expressBean;

	public ExpressBean getExpressBean() {
		return expressBean;
	}

	public void setExpressBean(ExpressBean expressBean) {
		this.expressBean = expressBean;
	}

	private Integer userId;
	private Integer count;

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

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	private List<Integer> selectedCartDetailIdList;
	private List<OrderDetail> orderDetailList;
	private Integer allSelected;

}
