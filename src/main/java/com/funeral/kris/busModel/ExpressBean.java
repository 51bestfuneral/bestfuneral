package com.funeral.kris.busModel;

import java.math.BigDecimal;

import com.funeral.kris.model.ExpressInfo;

public class ExpressBean   {
	private ExpressInfo expressInfo;
	private String expressDescription;
	private String expressTitle;
	private String province;
	public ExpressInfo getExpressInfo() {
		return expressInfo;
	}
	public void setExpressInfo(ExpressInfo expressInfo) {
		this.expressInfo = expressInfo;
	}
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
