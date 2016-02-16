package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.Question;

public interface QuestionService {
	
	public void addResource(Question question);
	public void updateResource(Question question);
	public Question getResource(int id);
	public void deleteResource(int id);
	public List<Question> getResources(HttpServletRequest request);
	public List<Question> findNextResource(HttpServletRequest request);

}
