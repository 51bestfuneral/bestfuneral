package com.funeral.kris.busModel;

import java.math.BigDecimal;

public class CartlistJson {
	private String wishName;
	private String wishDesc;
	private Integer wishId;
	private BigDecimal price;
	private Integer amount;
	private String imageUrl;
	private Integer cartDetailId;
	private Integer cartId;
	private BigDecimal originalPrice;
	private BigDecimal selectedPrice;
	private BigDecimal sumPrice;
	private Integer selected;

	public Integer getSelected() {
		return selected;
	}
	public void setSelected(Integer selected) {
		this.selected = selected;
	}
	public BigDecimal getSelectedPrice() {
		return selectedPrice;
	}
	public void setSelectedPrice(BigDecimal selectedPrice) {
		this.selectedPrice = selectedPrice;
	}
	public String getWishName() {
		return wishName;
	}
	public void setWishName(String wishName) {
		this.wishName = wishName;
	}
	public String getWishDesc() {
		return wishDesc;
	}
	public void setWishDesc(String wishDesc) {
		this.wishDesc = wishDesc;
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
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Integer getCartDetailId() {
		return cartDetailId;
	}
	public void setCartDetailId(Integer cartDetailId) {
		this.cartDetailId = cartDetailId;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public BigDecimal getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}
}
