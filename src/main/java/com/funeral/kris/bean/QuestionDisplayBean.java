package com.funeral.kris.bean;

import java.util.List;

import com.funeral.kris.model.TFQuestion;

public class QuestionDisplayBean  {
	
	private String dataX;
	private String dataY;
	
	public String getDisplayPercent() {
		return displayPercent;
	}

	public void setDisplayPercent(String displayPercent) {
		this.displayPercent = displayPercent;
	}


	private String displayPercent;
	
	public String getDataX() {
		return dataX;
	}

	public void setDataX(String dataX) {
		this.dataX = dataX;
	}

	public String getDataY() {
		return dataY;
	}

	public void setDataY(String dataY) {
		this.dataY = dataY;
	}

	public Long getSelectedOptionId() {
		return selectedOptionId;
	}
	
	
	private String style;
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}


	private String otherComments;

	public String getOtherComments() {
		return otherComments;
	}

	public void setOtherComments(String otherComments) {
		this.otherComments = otherComments;
	}

	public void setSelectedOptionId(Long selectedOptionId) {
		this.selectedOptionId = selectedOptionId;
	}

	private Long selectedOptionId;
	
	private TFQuestion question;
	
	public TFQuestion getQuestion() {
		return question;
	}

	public void setQuestion(TFQuestion question) {
		this.question = question;
	}

	public List getOptionList() {
		return optionList;
	}

	public void setOptionList(List optionList) {
		this.optionList = optionList;
	}

	private List optionList;

}
