package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.Option;

public interface OptionService {
	
	public void addResource(Option option);
	public void updateResource(Option option);
	public Option getResource(Long id);
	public void deleteResource(Long id);
	public List<Option> getResources();
	public List<Option> getResources(HttpServletRequest request);
    public List<Option> getOptionListByQuestionId(String id);
}
