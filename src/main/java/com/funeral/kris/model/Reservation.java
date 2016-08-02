package com.funeral.kris.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_reservation")
public class Reservation {
	@Id
	@GeneratedValue
	@Column(name="reserv_id")
	private Integer reservId;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="cemetery_id")
	private Integer cemeterId;
	
	@Column(name="reserv_dte")
	private String reservDte;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;

	public Integer getReservId() {
		return reservId;
	}

	public void setReservId(Integer reservId) {
		this.reservId = reservId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getCemeterId() {
		return cemeterId;
	}

	public void setCemeterId(Integer cemeterId) {
		this.cemeterId = cemeterId;
	}

	public String getReservDte() {
		return reservDte;
	}

	public void setReservDte(String reservDte) {
		this.reservDte = reservDte;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
