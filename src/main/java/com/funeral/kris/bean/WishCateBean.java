package com.funeral.kris.bean;

import java.util.List;

import com.funeral.kris.model.TWishCategorySub;

public class WishCateBean {
	
	private String fontStyle;
	
	public String getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}

	private String style;
	
	

	private Integer subCateSize;

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public void setSubCateSize(Integer subCateSize) {
		this.subCateSize = subCateSize;
	}

	public int getSubCateSize() {
		return subCateSize;
	}

	public void setSubCateSize(int subCateSize) {
		this.subCateSize = subCateSize;
	}

	

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	
	public Integer getValidFlag() {
		return validFlag;
	}

	public List<TWishCategorySub> getSubwishList() {
		return subwishList;
	}

	public void setSubwishList(List<TWishCategorySub> subwishList) {
		this.subwishList = subwishList;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	private Integer cateId;
	private String cateName;
	private Integer validFlag;

	private List<TWishCategorySub> subwishList;

}
