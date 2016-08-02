package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.Cemetery;

public interface CemeteryService {
	
	public void addResource(Cemetery cemetery);
	public void updateResource(Cemetery cemetery);
	public Cemetery getResource(int id);
	public void deleteResource(int id);
	public List<Cemetery> getResources();
	public List<String> getAllCss();
}
