package com.funeral.kris.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cemeterys")
public class Cemetery {

	@Id
	@GeneratedValue
	@Column(name = "cemetery_id")
	private Integer cemeteryId;

	@Column(name = "cemetery_name")
	private String cemeteryName;

	@Column(name = "cemetery_desc")
	private String cemeteryDesc;
	@Column(name = "traffic_Info")
	private String trafficInfo;

	

	public String getTrafficInfo() {
		return trafficInfo;
	}

	public void setTrafficInfo(String trafficInfo) {
		this.trafficInfo = trafficInfo;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	@Column(name = "district")
	private String district;

	@Column(name = "feature")
	private String feature;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "address")
	private String address;

	@Column(name = "url")
	private String url;
	
	@Column(name = "desc_img_url")
	private String descImgUrl;
	
	@Column(name = "feature_img_url")
	private String featureImgUrl;
	
	public String getDescImgUrl() {
		return descImgUrl;
	}

	public void setDescImgUrl(String descImgUrl) {
		this.descImgUrl = descImgUrl;
	}

	public String getFeatureImgUrl() {
		return featureImgUrl;
	}

	public void setFeatureImgUrl(String featureImgUrl) {
		this.featureImgUrl = featureImgUrl;
	}

	public String getLocationImgUrl() {
		return locationImgUrl;
	}

	public void setLocationImgUrl(String locationImgUrl) {
		this.locationImgUrl = locationImgUrl;
	}

	@Column(name = "location_img_url")
	private String locationImgUrl;
	
	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	@Column(name = "map_url")
	private String mapUrl;

	@Column(name = "createdate")
	private Date createDate;

	@Column(name = "updateddate")
	private Date updatedDate;

	
	public String getCemeteryName() {
		return cemeteryName;
	}

	public Integer getCemeteryId() {
		return cemeteryId;
	}

	public void setCemeteryId(Integer cemeteryId) {
		this.cemeteryId = cemeteryId;
	}

	public void setCemeteryName(String cemeteryName) {
		this.cemeteryName = cemeteryName;
	}

	public String getCemeteryDesc() {
		return cemeteryDesc;
	}

	public void setCemeteryDesc(String cemeteryDesc) {
		this.cemeteryDesc = cemeteryDesc;
	}

	public String getAddress() {
		return address;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setAddress(String address) {
		this.address = address;
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

}
