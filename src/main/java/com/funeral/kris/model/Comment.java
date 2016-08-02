package com.funeral.kris.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Table(name="comments")
public class Comment {
	
	@Id
	@GeneratedValue
	@Column(name="comment_id")
	private Integer commentId;

	@Column(name="wish_id")
	private String wishId;

	@Column(name="user_id")
	private String userId;

	@Column(name="type")
	private String type;

	@Column(name="createdate")
	private Date createDate;
	
	@Column(name="updateddate")
	private Date updatedDate;

	@Column(name="comment")
	private String comment;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getWishId() {
		return wishId;
	}

	public void setWishId(String wishId) {
		this.wishId = wishId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
