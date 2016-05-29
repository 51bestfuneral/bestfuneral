package com.funeral.kris.busModel;

import java.math.BigDecimal;

public class WishListJson {
	private String wishName;
	private String wishDesc;
	private Integer wishId;
	private BigDecimal price;
	private Integer amount;
	private String imageUrl;
	private Integer wishDetailId;
	private Integer wishlistId;
	private BigDecimal originalPrice;
	private BigDecimal selectedPrice;
	private Integer goodsAmount;
	private Integer servicesAmount;
	private  Integer level;
	private String levelDesc;



	public Integer getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public Integer getServicesAmount() {
		return servicesAmount;
	}
	public void setServicesAmount(Integer servicesAmount) {
		this.servicesAmount = servicesAmount;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getLevelDesc() {
		return levelDesc;
	}
	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
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
	public Integer getWishDetailId() {
		return wishDetailId;
	}
	public void setWishDetailId(Integer wishDetailId) {
		this.wishDetailId = wishDetailId;
	}
	public Integer getWishlistId() {
		return wishlistId;
	}
	public void setWishlistId(Integer wishlistId) {
		this.wishlistId = wishlistId;
	}
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	
}
