package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer usrId;

	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_type")
	private String userType;
	
	@Column(name="user_ref")
	private String userRef;
	
	@Column(name="e_mail")
	private String email;
	
	@Column(name="address")
	private String address;

	@Column(name="pwd")
	private String password;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;
	
	@Column(name="contactor")
	private String contactor;

	@Column(name="contactor_mail")
	private String contactorMail;
	
	@Column(name="contactor_phone")
	private String contactorPhone;

	public Integer getUsrId() {
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		System.out.print("chuan success");
		this.usrId = usrId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		System.out.print("chuan success");
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		System.out.print("chuan success");
		this.userType = userType;
	}

	public String getUserRef() {
		return userRef;
	}

	public void setUserRef(String userRef) {
		System.out.print("chuan success");
		this.userRef = userRef;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		System.out.print("chuan success");
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		System.out.print("chuan success");
		this.address = address;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getContactorMail() {
		return contactorMail;
	}

	public void setContactorMail(String contactorMail) {
		this.contactorMail = contactorMail;
	}

	public String getContactorPhone() {
		return contactorPhone;
	}

	public void setContactorPhone(String contactorPhone) {
		this.contactorPhone = contactorPhone;
	}

}
