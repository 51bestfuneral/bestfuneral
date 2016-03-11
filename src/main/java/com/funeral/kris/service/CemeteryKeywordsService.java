package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.TCemeteryKeywords;

public interface CemeteryKeywordsService {
	public void addResource(TCemeteryKeywords tCemeteryKeywords);
	public void updateResource(TCemeteryKeywords tCemeteryKeywords);
	public TCemeteryKeywords getResource(int id);
	public void deleteResource(int id);
	public List<TCemeteryKeywords> getResources(HttpServletRequest request);
}
