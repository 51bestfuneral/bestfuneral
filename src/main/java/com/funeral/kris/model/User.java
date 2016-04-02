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
	
	@Column(name="age")
	private Integer age;

	@Column(name="address")
	private String address;

	@Column(name="work")
	private String work;

	@Column(name="district")
	private String district;

	@Column(name="for_self")
	private Integer forSelf;

	@Column(name="name")
	private String name;

	@Column(name="gender")
	private Integer gender;
	
	@Column(name="birthday")
	private String birthday;
	
	@Column(name="birth_place")
	private String birthPlace;
	
	@Column(name="contactor")
	private String contactor;
	
	@Column(name="contactor_mail")
	private String contactorMail;
	
	@Column(name="contactor_phone")
	private String contactorPhone;
	
	@Column(name="contactor_address")
	private String contactorAddress;
	
	@Column(name="contactor_relate")
	private String contactorRelate;
	@Column(name="identifier")
	private String identifier;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getForSelf() {
		return forSelf;
	}

	public void setForSelf(Integer forSelf) {
		this.forSelf = forSelf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
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

	public String getContactorAddress() {
		return contactorAddress;
	}

	public void setContactorAddress(String contactorAddress) {
		this.contactorAddress = contactorAddress;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactorRelate() {
		return contactorRelate;
	}

	public void setContactorRelate(String contactorRelate) {
		this.contactorRelate = contactorRelate;
	}
	
}
