package com.funeral.kris.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wishs")
public class Wish {
	
	@Id
	@GeneratedValue
	@Column(name="wish_id")
	private Integer wishId;
	@Column(name="status_id")
	private Integer statusId;
	
	
public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	//	总类编号
	@Column(name="general_code")
	private String generalCode;
//	总类	
	@Column(name="general_cate")
	private String generalCate;
//  分类编号	
	@Column(name="sub_cate_code")
	private String subCateCode;
	
public String getImgUrl() {
		return ImgUrl;
	}

	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}

	//	分类
	@Column(name="category")
	private String category;
//	產品圖片
	@Column(name="img_url")
	private String ImgUrl;
	
//	材质	
	@Column(name="material")
	private String material;

	@Column(name="wish_Name")
	private String wishName;
public String getGeneralCode() {
		return generalCode;
	}

	public void setGeneralCode(String generalCode) {
		this.generalCode = generalCode;
	}

	public String getGeneralCate() {
		return generalCate;
	}

	public void setGeneralCate(String generalCate) {
		this.generalCate = generalCate;
	}

	public String getSubCateCode() {
		return subCateCode;
	}

	public void setSubCateCode(String subCateCode) {
		this.subCateCode = subCateCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getSupplier() {
		return supplier;
	}

	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	//	型号或款式	
	@Column(name="model")
	private int model;
	
//	供应商
	@Column(name="supplier")
	private int supplier;
	
	
//	单位
	@Column(name="unit")
	private int unit;
	
	
//  采购成本价	
	@Column(name="cost_price")
	private BigDecimal costPrice;
	
	
//  销售价	
	@Column(name="selling_price")
	private BigDecimal sellingPrice;

	
	@Column(name="wish_type")
	private String wishType;
	//單價
	@Column(name="price")
	private Double price;
	
	@Column(name="count")
	private Integer count;
	
	@Column(name="wish_desc")
	private String wishDesc;
	
	@Column(name="url")
	private String url;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="feature")
	private Integer feature;
	
	
	@Column(name="updateddate")
	private Date updatedDate;

	public Integer getWishId() {
		return wishId;
	}

	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}

	public String getWishName() {
		return wishName;
	}

	public void setWishName(String wishName) {
		this.wishName = wishName;
	}

	public String getWishType() {
		return wishType;
	}

	public void setWishType(String wishType) {
		this.wishType = wishType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getWishDesc() {
		return wishDesc;
	}

	public void setWishDesc(String wishDesc) {
		this.wishDesc = wishDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public Integer getFeature() {
		return feature;
	}

	public void setFeature(Integer feature) {
		this.feature = feature;
	}

	
}
