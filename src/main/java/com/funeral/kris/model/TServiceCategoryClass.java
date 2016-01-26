package com.funeral.kris.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_service_category_class")
public class TServiceCategoryClass {
	@Id
	@GeneratedValue
	@Column(name = "_id")
	private String Id;
	  public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassDis() {
		return classDis;
	}

	public void setClassDiis(String classDis) {
		this.classDis = classDis;
	}

	@Column(name = "low_price")
		private BigDecimal lowPrice; 
	  
	  @Column(name = "currency_id")
		private int currencyId; 
	  
	  
	  
	  @Column(name = "price")
			private BigDecimal price; 
		  
	  
	
	  @Column(name = "high_price")
		private BigDecimal highPrice; 
	  
	  
	  
	  @Column(name = "class_id")
		private int classId; 
	  
	  @Column(name = "class_dis")
		private String classDis;
	  
	
	

	@Column(name = "cate_id")
	private String cateId;
	
	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
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

	
	@Column(name = "insert_date")
	private Date insertDate;

	@Column(name = "update_date")
	private Date updateDate;

}
