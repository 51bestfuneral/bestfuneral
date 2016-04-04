package com.funeral.kris.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_contact_info")
public class ContactInfo {

	@Id
	@GeneratedValue
	@Column(name = "contact_id")
	private Integer contactId;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "contact_name")
	private String contactName;
	@Column(name = "gender")
	private Integer gender;
	@Column(name = "status_id")
	private Integer statusId;
	@Column(name = "express_method")
	private Integer expressMethod;
	public Integer getExpressMethod() {
		return expressMethod;
	}

	public void setExpressMethod(Integer expressMethod) {
		this.expressMethod = expressMethod;
	}

	@Column(name = "province")
	private String province;
	@Column(name = "city")
	private String city;
	@Column(name = "detail_address")
	private String detailAddress;
	@Column(name = "phone")
	private String phone;
	@Column(name = "backup_name")
	private String backupName;
	@Column(name = "backup_phone")
	private String backupPhone;

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBackupName() {
		return backupName;
	}

	public void setBackupName(String backupName) {
		this.backupName = backupName;
	}

	public String getBackupPhone() {
		return backupPhone;
	}

	public void setBackupPhone(String backupPhone) {
		this.backupPhone = backupPhone;
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

	@Column(name = "createdate")
	private Date createDate;

	@Column(name = "updateddate")
	private Date updatedDate;

}
