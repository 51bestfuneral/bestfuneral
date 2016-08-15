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
	@Column(name="cemetery_id")
	private Integer cemeteryId;

	@Column(name = "cemetery_name")
	private String cemeteryName;

	@Column(name = "cemetery_desc")
	private String cemeteryDesc;

	@Column(name = "traffic_Info")
	private String trafficInfo;

	@Column(name = "head_img")
	private String headImg;

	@Column(name = "original_price")
	private BigDecimal originalPrice;

	@Column(name = "map_url")
	private String mapUrl;

	@Column(name = "createdate")
	private Date createDate;

	@Column(name = "updateddate")
	private Date updatedDate;

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

	@Column(name = "location_img_url")
	private String locationImgUrl;

	@Column(name = "type")
	private Integer type;

	@Column(name = "desc_img_url")
	private String descImgUrl;

	@Column(name = "feature_img_url")
	private String featureImgUrl;

	@Column(name = "filter1")
	private String filter1;

	@Column(name = "filter2")
	private String filter2;

	@Column(name = "filter3")
	private String filter3;

	@Column(name = "valid")
	private String valid;

	@Column(name = "firm")
	private String firm;

	@Column(name = "level")
	private String level;

	@Column(name = "size")
	private String size;

	@Column(name = "views")
	private String views;

	@Column(name = "stop")
	private int stop;

	@Column(name = "famous")
	private String famous;

	@Column(name = "text_styles")
	private String textStyle;

	@Column(name = "open_time")
	private String openTime;

	@Column(name = "close_time")
	private String closeTime;

	@Column(name = "free_service")
	private String freeService;

	@Column(name = "special_service")
	private String specialService;

	@Column(name = "fund")
	private String fund;

	@Column(name = "notices")
	private String notices;

	public Integer getCemeteryId() {
		return cemeteryId;
	}

	public void setCemeteryId(Integer cemeteryId) {
		this.cemeteryId = cemeteryId;
	}

	public String getCemeteryName() {
		return cemeteryName;
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

	public String getTrafficInfo() {
		return trafficInfo;
	}

	public void setTrafficInfo(String trafficInfo) {
		this.trafficInfo = trafficInfo;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
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

	public String getLocationImgUrl() {
		return locationImgUrl;
	}

	public void setLocationImgUrl(String locationImgUrl) {
		this.locationImgUrl = locationImgUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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

	public String getFilter1() {
		return filter1;
	}

	public void setFilter1(String filter1) {
		this.filter1 = filter1;
	}

	public String getFilter2() {
		return filter2;
	}

	public void setFilter2(String filter2) {
		this.filter2 = filter2;
	}

	public String getFilter3() {
		return filter3;
	}

	public void setFilter3(String filter3) {
		this.filter3 = filter3;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}

	public String getFamous() {
		return famous;
	}

	public void setFamous(String famous) {
		this.famous = famous;
	}

	public String getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getFreeService() {
		return freeService;
	}

	public void setFreeService(String freeService) {
		this.freeService = freeService;
	}

	public String getSpecialService() {
		return specialService;
	}

	public void setSpecialService(String specialService) {
		this.specialService = specialService;
	}

	public String getFund() {
		return fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public String getNotices() {
		return notices;
	}

	public void setNotices(String notices) {
		this.notices = notices;
	}

}
