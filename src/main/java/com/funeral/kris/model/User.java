package com.funeral.kris.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_user")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer usrId;

	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_type")
	private Integer userType;
	
	@Column(name="user_ref")
	private String userRef;
	
	@Column(name="e_mail")
	private String email;
	
	

	@Column(name="pwd")
	private String password;
	
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(name="phone")
	private String phone;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;
	@Column(name="status")
	private Integer status;
	@Column(name="last_loginIp")
	private String lastLoginIp;
	@Column(name="last_loginTime")
	private Date lastLoginTime;
	@Column(name="invalid_loginTimes")
	private Integer invalidLoginTimes;
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		System.out.print("chuan success lastLoginTime="+lastLoginTime);
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getInvalidLoginTimes() {
		return invalidLoginTimes;
	}

	public void setInvalidLoginTimes(Integer invalidLoginTimes) {
		
		System.out.print("chuan success invalidLoginTimes="+invalidLoginTimes);

		this.invalidLoginTimes = invalidLoginTimes;
	}

	
	public Integer getUsrId() {
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		System.out.print("chuan success userName="+userName);
		this.userName = userName;
	}

	
	public String getUserRef() {
		return userRef;
	}

	public void setUserRef(String userRef) {
		System.out.print("chuan success userRef="+userRef);
		this.userRef = userRef;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPassword() {
		System.out.print("chuan getPassword password="+password);

		return password;
	}

	public void setPassword(String password) {
		System.out.print("chuan setPassword password="+password);

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

	

}
