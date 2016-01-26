package com.funeral.kris.busModel;

import java.util.List;

import com.funeral.kris.model.Comment;


public class CommentJson {
	private String score;
	
	private List<CommentJson> subCommentList;
	
	private Comment comment;
	
	private String userName;

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public List<CommentJson> getSubCommentList() {
		return subCommentList;
	}

	public void setSubCommentList(List<CommentJson> subCommentList) {
		this.subCommentList = subCommentList;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
