package com.funeral.kris.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_fee_collection")
public class TFeeCollection {
	
	@Id
	@GeneratedValue
	@Column(name="collection_id ")
	private Integer collectionId;
	
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	@Column(name="order_id ")
	private Integer orderId;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public BigDecimal getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(BigDecimal currencyRate) {
		this.currencyRate = currencyRate;
	}
	public Integer getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	@Column(name="order_no")
	private String orderNo;
	@Column(name="subject ")
	private String subject;
	@Column(name="out_trade_no ")
	private String outTradeNo;
	@Column(name="trade_no ")
	private String TradeNo;
	@Column(name="gmt_create ")
	private String gmtCreate;
	@Column(name="gmt_payment")
	private String gmtPayment;
	public String getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getGmtPayment() {
		return gmtPayment;
	}
	public void setGmtPayment(String gmtPayment) {
		this.gmtPayment = gmtPayment;
	}
	@Column(name="description")
	private String description;
	@Column(name="payer_id")
	private Integer payerId;
	@Column(name="amount ")
	private BigDecimal amount;
	@Column(name="currency_rate")
	private BigDecimal currencyRate;
	@Column(name="currency_id")
	private Integer currencyId;
	@Column(name="status_id ")
	private Integer statusId;
	@Column(name="collection_type")
	private Integer collectionType;
	public Integer getPayerId() {
		return payerId;
	}
	public void setPayerId(Integer payerId) {
		this.payerId = payerId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(Integer collectionType) {
		this.collectionType = collectionType;
	}
	public String getNotifyId() {
		return notifyId;
	}
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	public String getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getExterface() {
		return exterface;
	}
	public void setExterface(String exterface) {
		this.exterface = exterface;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getTradeNo() {
		return TradeNo;
	}
	public void setTradeNo(String tradeNo) {
		TradeNo = tradeNo;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	@Column(name="notify_id")
	private String notifyId;
	@Column(name="seller_id")
	private String sellerId;
	@Column(name="sign")
	private String sign;
	@Column(name="sign_type")
	private String signType;
	@Column(name="notify_type")
	private String notifyType;
	@Column(name="notify_time")
	private String notifyTime;
	@Column(name="exterface")
	private String exterface;
	@Column(name="trade_status")
	private String tradeStatus;
	@Column(name="buyer_email")
	private String buyerEmail;
	@Column(name="seller_email")
	private String sellerEmail;
	@Column(name="quantity")
	private String quantity;
	
	@Column(name="use_coupon")
	private String useCoupon;
	
	@Column(name="discount")
	private BigDecimal discount;
	@Column(name="buyer_id")
	private Integer buyerId;
 
	public String getBuyerEmail() {
		return buyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	
	public String getSellerEmail() {
		return sellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getUseCoupon() {
		return useCoupon;
	}
	public void setUseCoupon(String useCoupon) {
		this.useCoupon = useCoupon;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public String getIsTotalFeeAdjust() {
		return isTotalFeeAdjust;
	}
	public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
		this.isTotalFeeAdjust = isTotalFeeAdjust;
	}
	@Column(name="is_total_fee_adjust")
	private String isTotalFeeAdjust;
	
	
	

			  

	

}
