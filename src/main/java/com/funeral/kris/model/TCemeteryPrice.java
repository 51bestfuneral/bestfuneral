package com.funeral.kris.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_Cemetery_Price")
public class TCemeteryPrice {
	
	@Id
	@GeneratedValue
	@Column(name="_id")
	private Integer id;
	
	@Column(name="graveStyle_id")
	private Integer graveStyleId;
	@Column(name="epigraphStyle_id")
	private Integer epigraphStyleId;
	@Column(name="price")
	private BigDecimal price;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGraveStyleId() {
		return graveStyleId;
	}

	public void setGraveStyleId(Integer graveStyleId) {
		this.graveStyleId = graveStyleId;
	}

	public Integer getEpigraphStyleId() {
		return epigraphStyleId;
	}

	public void setEpigraphStyleId(Integer epigraphStyleId) {
		this.epigraphStyleId = epigraphStyleId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
