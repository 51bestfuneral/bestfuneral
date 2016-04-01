package com.funeral.kris.bean;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {
	
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
	public List<Integer> getSelectedWishDetailIdList() {
		return selectedWishDetailIdList;
	}
	public void setSelectedWishDetailIdList(List<Integer> selectedWishDetailIdList) {
		this.selectedWishDetailIdList = selectedWishDetailIdList;
	}
	public BigDecimal getGrossFee() {
		return grossFee;
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
	private List<Integer> selectedWishDetailIdList;
	private Integer allSelected;
	

}
