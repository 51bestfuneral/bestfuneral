package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.Comment;

public interface CommentService {
	
	public void addResource(Comment comment);
	public void updateResource(Comment comment);
	public Comment getResource(int id);
	public void deleteResource(int id);
	public List<Comment> getResources(HttpServletRequest request);

}
