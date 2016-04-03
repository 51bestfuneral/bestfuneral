package com.funeral.kris.busModel;

import java.math.BigDecimal;

public class ExpressBean {
	
	private String expressDescription;
	private String expressTitle;
	private String province;
	public String getExpressTitle() {
		return expressTitle;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setExpressTitle(String expressTitle) {
		this.expressTitle = expressTitle;
	}
	private Integer expressMethod;
	private BigDecimal expressFee;
	public String getExpressDescription() {
		return expressDescription;
	}
	public void setExpressDescription(String expressDescription) {
		this.expressDescription = expressDescription;
	}
	public Integer getExpressMethod() {
		return expressMethod;
	}
	public void setExpressMethod(Integer expressMethod) {
		this.expressMethod = expressMethod;
	}
	public BigDecimal getExpressFee() {
		return expressFee;
	}
	public void setExpressFee(BigDecimal expressFee) {
		this.expressFee = expressFee;
	}
	

}
