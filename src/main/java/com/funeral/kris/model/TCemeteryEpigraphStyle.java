package com.funeral.kris.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_Cemetery_Epigraph_Style")
public class TCemeteryEpigraphStyle {
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCemeteryId() {
		return cemeteryId;
	}

	public void setCemeteryId(Integer cemeteryId) {
		this.cemeteryId = cemeteryId;
	}

	@Column(name="cemetery_id")
	private Integer cemeteryId;
	
	@Column(name="style")
	private String style;
	
	@Column(name="description")
	private String description;

	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;

	public Date getCreateDate() {
		return createDate;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
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
