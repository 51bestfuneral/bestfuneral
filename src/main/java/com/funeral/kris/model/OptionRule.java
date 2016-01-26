package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="option_rules")
public class OptionRule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="option_id")
	private String optionId;

	@Id
	@Column(name="rule_id")
	private String ruleId;
	
	@Column(name="rule_type")
	private String ruleType;

	@Column(name="rule")
	private String rule;

	@Column(name="seq")
	private Integer sequence;

	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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
