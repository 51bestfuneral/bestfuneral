package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.Option;

public interface OptionService {
	
	public void addResource(Option option);
	public void updateResource(Option option);
	public Option getResource(int id);
	public void deleteResource(int id);
	public List<Option> getResources(HttpServletRequest request);

}
