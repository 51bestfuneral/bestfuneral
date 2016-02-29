package com.funeral.kris.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wishs")
public class Wish {
	
	@Id
	@GeneratedValue
	@Column(name="wish_id")
	private Integer wishId;
	@Column(name="status_id")
	private Integer statusId;
	public Integer getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Integer purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	@Column(name="purchase_time")
	private Integer purchaseTime;
	
	
public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name="general_code")
	private String generalCode;

	@Column(name="general_cate")
	private String generalCate;

	@Column(name="sub_cate_code")
	private String subCateCode;
	@Column(name="size")
	private String size;
	
public String getImgUrl() {
		return ImgUrl;
	}

	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}

	@Column(name="sub_category")
	private String subCategory;
	@Column(name="img_url")
	private String ImgUrl;
	
	@Column(name="material")
	private String material;

	@Column(name="wish_Name")
	private String wishName;

    public String getGeneralCode() {
		return generalCode;
	}

	public void setGeneralCode(String generalCode) {
		this.generalCode = generalCode;
	}

	public String getGeneralCate() {
		return generalCate;
	}

	public void setGeneralCate(String generalCate) {
		this.generalCate = generalCate;
	}

	public String getSubCateCode() {
		return subCateCode;
	}

	public void setSubCateCode(String subCateCode) {
		this.subCateCode = subCateCode;
	}

	

	public String getMaterial() {
		return material;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

	public Integer getSupplier() {
		return supplier;
	}

	public void setSupplier(Integer supplier) {
		this.supplier = supplier;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	@Column(name="model")
	private String model;
	public String getSize() {
		return size;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getKaimoTime() {
		return kaimoTime;
	}

	public void setKaimoTime(int kaimoTime) {
		this.kaimoTime = kaimoTime;
	}

	public BigDecimal getOtherFeeA() {
		return otherFeeA;
	}

	public void setOtherFeeA(BigDecimal otherFeeA) {
		this.otherFeeA = otherFeeA;
	}

	public BigDecimal getOtherFeeB() {
		return otherFeeB;
	}

	public void setOtherFeeB(BigDecimal otherFeeB) {
		this.otherFeeB = otherFeeB;
	}

	public BigDecimal getOtherFeeC() {
		return otherFeeC;
	}

	public void setOtherFeeC(BigDecimal otherFeeC) {
		this.otherFeeC = otherFeeC;
	}

	public BigDecimal getBookPrice1() {
		return bookPrice1;
	}

	public void setBookPrice1(BigDecimal bookPrice1) {
		this.bookPrice1 = bookPrice1;
	}

	public Integer getBookCount1() {
		return bookCount1;
	}

	public void setBookCount1(Integer bookCount1) {
		this.bookCount1 = bookCount1;
	}

	public BigDecimal getBookPrice2() {
		return bookPrice2;
	}

	public void setBookPrice2(BigDecimal bookPrice2) {
		this.bookPrice2 = bookPrice2;
	}

	public Integer getBookCount2() {
		return bookCount2;
	}

	public void setBookCount2(Integer bookCount2) {
		this.bookCount2 = bookCount2;
	}

	public BigDecimal getBookPrice3() {
		return bookPrice3;
	}

	public void setBookPrice3(BigDecimal bookPrice3) {
		this.bookPrice3 = bookPrice3;
	}

	public Integer getBookCount3() {
		return bookCount3;
	}

	public void setBookCount3(Integer bookCount3) {
		this.bookCount3 = bookCount3;
	}

	public Integer getPurchaseCapacity() {
		return purchaseCapacity;
	}

	public void setPurchaseCapacity(Integer purchaseCapacity) {
		this.purchaseCapacity = purchaseCapacity;
	}

	public Integer getReturnCount() {
		return returnCount;
	}

	public void setReturnCount(Integer returnCount) {
		this.returnCount = returnCount;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public BigDecimal getXianenPrice() {
		return xianenPrice;
	}

	public String getXianenDiffPrice() {
		return xianenDiffPrice;
	}

	public void setXianenDiffPrice(String xianenDiffPrice) {
		this.xianenDiffPrice = xianenDiffPrice;
	}

	public void setXianenPrice(BigDecimal xianenPrice) {
		this.xianenPrice = xianenPrice;
	}

	

	public BigDecimal getXiangheTotalPrice() {
		return xiangheTotalPrice;
	}

	public void setXiangheTotalPrice(BigDecimal xiangheTotalPrice) {
		this.xiangheTotalPrice = xiangheTotalPrice;
	}

	

	public BigDecimal getKaimoFee() {
		return kaimoFee;
	}

	public String getXiangheDiffPrice() {
		return xiangheDiffPrice;
	}

	public void setXiangheDiffPrice(String xiangheDiffPrice) {
		this.xiangheDiffPrice = xiangheDiffPrice;
	}

	public void setKaimoFee(BigDecimal kaimoFee) {
		this.kaimoFee = kaimoFee;
	}

	public BigDecimal getSalesIncome() {
		return salesIncome;
	}

	public void setSalesIncome(BigDecimal salesIncome) {
		this.salesIncome = salesIncome;
	}

	public BigDecimal getTotalProcurementCost() {
		return totalProcurementCost;
	}

	public void setTotalProcurementCost(BigDecimal totalProcurementCost) {
		this.totalProcurementCost = totalProcurementCost;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getBadDebt() {
		return badDebt;
	}

	public void setBadDebt(BigDecimal badDebt) {
		this.badDebt = badDebt;
	}

	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}

	public BigDecimal getHoldingCost() {
		return holdingCost;
	}

	public void setHoldingCost(BigDecimal holdingCost) {
		this.holdingCost = holdingCost;
	}

	public BigDecimal getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}

	public BigDecimal getProfitLossRate() {
		return profitLossRate;
	}

	public void setProfitLossRate(BigDecimal profitLossRate) {
		this.profitLossRate = profitLossRate;
	}

	public Integer getHoldingCount() {
		return holdingCount;
	}

	public void setHoldingCount(Integer holdingCount) {
		this.holdingCount = holdingCount;
	}

	public Integer getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(Integer salesChannel) {
		this.salesChannel = salesChannel;
	}

	public Integer getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(Integer operator_id) {
		this.operator_id = operator_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getProcurementCost() {
		return procurementCost;
	}

	public void setProcurementCost(BigDecimal procurementCost) {
		this.procurementCost = procurementCost;
	}

	@Column(name="kaimo_time")
	private int kaimoTime;
	
//	供应商
	@Column(name="supplier")
	private String supplier;
	
	
//	单位
	@Column(name="unit")
	private String unit;
	
	

	@Column(name="other_feeA")
	private BigDecimal otherFeeA;
	@Column(name="other_feeB")
	private BigDecimal otherFeeB;
	@Column(name="other_feeC")
	private BigDecimal otherFeeC;
	@Column(name="book_price1")
	private BigDecimal bookPrice1;
	@Column(name="book_count1")
	private Integer bookCount1;
	@Column(name="book_price2")
	private BigDecimal bookPrice2;
	@Column(name="book_count2")
	private Integer bookCount2;
	@Column(name="book_price3")
	private BigDecimal bookPrice3;
	@Column(name="book_count3")
	private Integer bookCount3;
	@Column(name="purchase_capacity")
	private Integer purchaseCapacity;
	@Column(name="return_count")
	private Integer returnCount;
	@Column(name="sales_volume")
	private Integer salesVolume;
	
	
//  销售价	
	@Column(name="selling_price")
	private BigDecimal sellingPrice;

	@Column(name="xianen_price")
	private BigDecimal xianenPrice;
	@Column(name="xianen_diff_price")
	private String xianenDiffPrice;
	@Column(name="xianghe_total_price")
	private BigDecimal xiangheTotalPrice;
	@Column(name="xianghe_diff_price")
	private String xiangheDiffPrice;
	@Column(name="kaimo_fee")
	private BigDecimal kaimoFee;
	@Column(name="sales_income")
	private BigDecimal salesIncome;
	@Column(name="total_procurement_cost")
	private BigDecimal totalProcurementCost;
	@Column(name="commission_rate")
	private BigDecimal commissionRate;
	@Column(name="commission")
	private BigDecimal commission;
	@Column(name="bad_debt")
	private BigDecimal badDebt;
	@Column(name="gross_profit")
	private BigDecimal grossProfit;
	@Column(name="holding_cost")
	private BigDecimal holdingCost;
	@Column(name="net_profit")
	private BigDecimal netProfit;
	@Column(name="profit_loss_rate")
	private BigDecimal profitLossRate;
	@Column(name="holding_count")
	private Integer holdingCount;
	@Column(name="sales_channel")
	private Integer salesChannel;
	@Column(name="operator_id")
	private Integer operator_id;
	

	
	@Column(name="remark")
	private String remark;
	

	@Column(name="procurement_cost")
	private BigDecimal procurementCost;
	
	
	
	@Column(name="wish_desc")
	private String wishDesc;
	
	@Column(name="url")
	private String url;
	
	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="feature")
	private Integer feature;
	
	
	@Column(name="updateddate")
	private Date updatedDate;

	public Integer getWishId() {
		return wishId;
	}

	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}

	public String getWishName() {
		return wishName;
	}

	public void setWishName(String wishName) {
		this.wishName = wishName;
	}
	

	

	public String getWishDesc() {
		return wishDesc;
	}

	public void setWishDesc(String wishDesc) {
		this.wishDesc = wishDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public Integer getFeature() {
		return feature;
	}

	public void setFeature(Integer feature) {
		this.feature = feature;
	}

	
}
