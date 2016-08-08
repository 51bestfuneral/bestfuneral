package com.funeral.kris.bean;

import java.math.BigDecimal;
import java.util.List;

import com.funeral.kris.model.TCemeteryEpigraphStyle;
import com.funeral.kris.model.TCemeteryGraveStyle;
import com.funeral.kris.model.TCemeteryGraveZone;
import com.funeral.kris.model.TCemeteryKeywords;

public class CemeteryBean {
	
	private Integer cemeteryId;
	private String cemeteryName;
	private BigDecimal price;
	private String district;
	private String mapUrl;
	private String cemeteryDesc;
	private String descImgUrl;
	private String featureImgUrl;
	private String locationImgUrl;
	private String style;
	private Integer type;
	private String feature;
	private String TrafficInfo;
	private String address;
	private List<TCemeteryKeywords> keywordsList;
	private List<TCemeteryGraveStyle> graveStyleList;
	private List<TCemeteryGraveZone> graveZoneList;
	private List<TCemeteryEpigraphStyle> epigraphStyleList;
	private String css;
	private String pinyin;

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
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMapUrl() {
		return mapUrl;
	}
	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}
	public String getCemeteryDesc() {
		return cemeteryDesc;
	}
	public void setCemeteryDesc(String cemeteryDesc) {
		this.cemeteryDesc = cemeteryDesc;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getTrafficInfo() {
		return TrafficInfo;
	}
	public void setTrafficInfo(String trafficInfo) {
		TrafficInfo = trafficInfo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<TCemeteryKeywords> getKeywordsList() {
		return keywordsList;
	}
	public void setKeywordsList(List<TCemeteryKeywords> keywordsList) {
		this.keywordsList = keywordsList;
	}
	public List<TCemeteryGraveStyle> getGraveStyleList() {
		return graveStyleList;
	}
	public void setGraveStyleList(List<TCemeteryGraveStyle> graveStyleList) {
		this.graveStyleList = graveStyleList;
	}
	public List<TCemeteryGraveZone> getGraveZoneList() {
		return graveZoneList;
	}
	public void setGraveZoneList(List<TCemeteryGraveZone> graveZoneList) {
		this.graveZoneList = graveZoneList;
	}
	public List<TCemeteryEpigraphStyle> getEpigraphStyleList() {
		return epigraphStyleList;
	}
	public void setEpigraphStyleList(List<TCemeteryEpigraphStyle> epigraphStyleList) {
		this.epigraphStyleList = epigraphStyleList;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
}
