package com.funeral.kris.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_Cemetery_Grave_Style")
public class TCemeteryGraveStyle {
	
	@Id
	@GeneratedValue
	@Column(name="_id")
	private Integer id;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="cemetery_id")
	private Integer cemeteryId;
	
	public Integer getCemeteryId() {
		return cemeteryId;
	}

	public void setCemeteryId(Integer cemeteryId) {
		this.cemeteryId = cemeteryId;
	}

	@Column(name="size")
	private Integer size;
	
	@Column(name="description")
	private String description;

	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;

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
