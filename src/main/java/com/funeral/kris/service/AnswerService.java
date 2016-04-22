package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.Answer;

public interface AnswerService {
	
	public void addResource(Answer answer);
	public void updateResource(Answer answer);
	public Answer getResource(int id);
	public void deleteResource(int id);
	public List<Answer> getResources();

}
