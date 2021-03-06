package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="answers")
public class Answer {

	@Id
	@GeneratedValue
	@Column(name="answer_id")
	private String answerId;

	@Column(name="user_id")
	private String userId;

	@Column(name="ans_list_id")
	private String ansListId;

	@Column(name="question_id")
	private Integer questionId;

	@Column(name="answer_desc")
	private String answerDesc;

	@Column(name="createdate")
	private Date createDate;

	@Column(name="updateddate")
	private Date updatedDate;

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAnsListId() {
		return ansListId;
	}

	public void setAnsListId(String ansListId) {
		this.ansListId = ansListId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getAnswerDesc() {
		return answerDesc;
	}

	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
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

}
