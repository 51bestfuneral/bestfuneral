package com.funeral.kris.busModel;

import java.util.Date;

public class ReservationJson {
	private Integer reservId;
    private Integer userId;
    private String userName;
    private Integer cemeteryId;
    private String address;
    private String cemeteryName;
    private String cemeteryDesc;
    private String feature;
    private String imgUrl;
    private String phoneNumber;
    private String reservDate;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getCemeteryId() {
		return cemeteryId;
	}
	public void setCemeteryId(Integer cemeteryId) {
		this.cemeteryId = cemeteryId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getReservDate() {
		return reservDate;
	}
	public void setReservDate(String reservDate) {
		this.reservDate = reservDate;
	}
	public Integer getReservId() {
		return reservId;
	}
	public void setReservId(Integer reservId) {
		this.reservId = reservId;
	}
}
