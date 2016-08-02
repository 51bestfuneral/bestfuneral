package com.funeral.kris.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_wish_level_relation")
public class TWishLevelRelation {

	@Id
	@GeneratedValue
	@Column(name = "relation_id")
	private Long relationId;

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public Long getWishIid() {
		return wishIid;
	}

	public void setWishIid(Long wishIid) {
		this.wishIid = wishIid;
	}

	@Column(name = "wish_id")
	private Long wishIid;

	@Column(name = "level_id")
	private Long levelId;

	@Column(name = "level_name")
	private String levelName;

	@Column(name = "level_desc")
	private String levelDesc;

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelDesc() {
		return levelDesc;
	}

	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDisplayPrice() {
		return displayPrice;
	}

	public void setDisplayPrice(String displayPrice) {
		this.displayPrice = displayPrice;
	}

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "display_price")
	private String displayPrice;
	@Column(name = "valid_flag")
	private Integer validFlag;

	@Column(name = "createdate")
	private Date createDate;

	@Column(name = "updateddate")
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
