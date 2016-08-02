package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TServiceCategory;

public interface ServiceCategoryService {
	public void addResource(TServiceCategory serviceCate);
	public void updateResource(TServiceCategory serviceCate);
	public TServiceCategory getResource(int cateId);
	public void deleteResource(int cateId);
	public List<TServiceCategory> getResources();
}
