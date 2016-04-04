package com.funeral.kris.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tao_config")
public class TaoConfig {
	@Id
	@GeneratedValue
	@Column(name="config_id")
    private Integer configId;

	@Column(name="tao_code")
    private String taoCode;

	@Column(name="wish_cata_code")
    private String wishCataCode;

	public Integer getConfigId() {
		return configId;
	}
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
	public String getTaoCode() {
		return taoCode;
	}
	public void setTaoCode(String taoCode) {
		this.taoCode = taoCode;
	}
	public String getWishCataCode() {
		return wishCataCode;
	}
	public void setWishCataCode(String wishCataCode) {
		this.wishCataCode = wishCataCode;
	}
    
}
