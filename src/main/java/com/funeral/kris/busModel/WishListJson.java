package com.funeral.kris.busModel;

public class WishListJson {
	private String wishName;
	private String wishDesc;
	private String wishId;
	private Double price;
	private Integer amount;
	private String imageUrl;
	private Integer wishDetailId;

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
	public String getWishId() {
		return wishId;
	}
	public void setWishId(String wishId) {
		this.wishId = wishId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
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
	
}
