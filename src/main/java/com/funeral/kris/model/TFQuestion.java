package com.funeral.kris.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_f_question")
public class TFQuestion {
	@Id
	@GeneratedValue
	@Column(name = "qusetion_id")
	private long qusetionId;

	public int getOptionSize() {
		return optionSize;
	}

	public void setOptionSize(int optionSize) {
		this.optionSize = optionSize;
	}

	public int getOptionForm() {
		return optionForm;
	}

	public void setOptionForm(int optionForm) {
		this.optionForm = optionForm;
	}

	@Column(name = "options_size")
	private int optionSize;

	@Column(name = "option_form")
	private int optionForm;

	public long getQusetionId() {
		return qusetionId;
	}

	public void setQusetionId(long qusetionId) {
		this.qusetionId = qusetionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(int ageRange) {
		this.ageRange = ageRange;
	}

	public int getGenderRange() {
		return genderRange;
	}

	public void setGenderRange(int genderRange) {
		this.genderRange = genderRange;
	}

	public int getAnswerCate() {
		return answerCate;
	}

	public void setAnswerCate(int answerCate) {
		this.answerCate = answerCate;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getMandatoryFlag() {
		return mandatoryFlag;
	}

	public void setMandatoryFlag(int mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(long updatorId) {
		this.updatorId = updatorId;
	}

	public long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
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

	@Column(name = "title")
	private String title;

	@Column(name = "question_content")
	private String questionContent;

	@Column(name = "priority_id")
	private int priorityId;
	@Column(name = "display_form")
	private int displayForm;

	public int getDisplayForm() {
		return displayForm;
	}

	public void setDisplayForm(int displayForm) {
		this.displayForm = displayForm;
	}

	@Column(name = "module_id")
	private int moduleId;

	@Column(name = "age_range")
	private int ageRange;
	@Column(name = "gender_range")
	private int genderRange;

	@Column(name = "answer_cate")
	private int answerCate;
	@Column(name = "status_id")
	private int statusId;

	@Column(name = "mandatory_flag")
	private int mandatoryFlag;
	@Column(name = "display_order")
	private int displayOrder;

	@Column(name = "creator_id")
	private long creatorId;
	@Column(name = "updator_id")
	private long updatorId;
	@Column(name = "operator_id")
	private long operatorId;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "updated_date")
	private Date updatedDate;

}
