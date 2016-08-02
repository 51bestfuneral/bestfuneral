package com.funeral.kris.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_wish_category_sub")
public class TWishCategorySub {
	@Id
	@GeneratedValue
	@Column(name = "wish_id")
	private Long wishId;

	@Column(name = "cate_id")
	private Long cateId;

	public Long getCateId() {
		return cateId;
	}
	
	
	

	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}

	public Long getWishId() {
		return wishId;
	}

	public void setWishId(Long wishId) {
		this.wishId = wishId;
	}

	public String getWishName() {
		return wishName;
	}

	public void setWishName(String wishName) {
		this.wishName = wishName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDisplayPrice() {
		return displayPrice;
	}

	public void setDisplayPrice(BigDecimal displayPrice) {
		this.displayPrice = displayPrice;
	}

	public String getWishDesc() {
		return wishDesc;
	}

	public void setWishDesc(String wishDesc) {
		this.wishDesc = wishDesc;
	}

	@Column(name = "wish_name")
	private String wishName;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "display_price")
	private BigDecimal displayPrice;

	@Column(name = "wish_desc")
	private String wishDesc;
	@Column(name = "url01")
	private String url01;
	@Column(name = "url02")
	private String url02;
	@Column(name = "url03")
	private String url03;
	public String getUrl01() {
		return url01;
	}




	public void setUrl01(String url01) {
		this.url01 = url01;
	}




	public String getUrl02() {
		return url02;
	}




	public void setUrl02(String url02) {
		this.url02 = url02;
	}




	public String getUrl03() {
		return url03;
	}




	public void setUrl03(String url03) {
		this.url03 = url03;
	}




	public String getUrl04() {
		return url04;
	}




	public void setUrl04(String url04) {
		this.url04 = url04;
	}

	@Column(name = "url04")
	private String url04;

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	@Column(name = "valid_flag")
	private Integer validFlag;

	@Column(name = "createdate")
	private Date createDate;

	@Column(name = "updateddate")
	private Date updatedDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
