package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.OptionRule;

public interface OptionRuleService {
	
	public void addResource(OptionRule optionRule);
	public void updateResource(OptionRule optionRule);
	public OptionRule getResource(int id);
	public void deleteResource(int id);
	public List<OptionRule> getResources();

}
