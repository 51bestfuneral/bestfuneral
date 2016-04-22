package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.FuneralTemplate;

public interface FuneralTemplateService {
	
	public void addResource(FuneralTemplate funeralTemplate);
	public void updateResource(FuneralTemplate funeralTemplate);
	public FuneralTemplate getResource(int id);
	public void deleteResource(int id);
	public List<FuneralTemplate> getResources();

}
