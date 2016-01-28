package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.TFAnswer;

public interface FAnswerService {
	
	public void addResource(TFAnswer tFAnswer);
	public void updateResource(TFAnswer tFAnswer);
	public TFAnswer getResource(Long id);
	public void deleteResource(Long id);
	public List<TFAnswer> getResources();
	public List<TFAnswer> getResources(HttpServletRequest request);
	public void deleteAnswer(Long userId,Long questionId);
	public TFAnswer getAnswer(Long userId,Long questionId,Long option);
	public TFAnswer getAnswerByQuestionId(Long questionId);
	
}
