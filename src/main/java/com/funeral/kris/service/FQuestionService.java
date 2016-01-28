package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TFQuestion;

public interface FQuestionService {
	public void addResource(TFQuestion fQuestiion);

	public void updateResource(TFQuestion fQuestiion);

	public TFQuestion getResource(long  questionId);

	public void deleteResource(long questionId);

	public List<TFQuestion> getResources();
	
	public Long count();

	
}
