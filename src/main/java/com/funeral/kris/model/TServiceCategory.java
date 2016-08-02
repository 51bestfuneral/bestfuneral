package com.funeral.kris.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_service_category")
public class TServiceCategory {
	@Id
	@GeneratedValue
	@Column(name = "cate_id")
	private String cateId;

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getServiceDis() {
		return serviceDis;
	}

	public void setServiceDis(String serviceDis) {
		this.serviceDis = serviceDis;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "title")
	private String title;

	@Column(name = "service_dis")
	private String serviceDis;
	@Column(name = "insert_date")
	private Date insertDate;

	@Column(name = "update_date")
	private Date updateDate;

}
