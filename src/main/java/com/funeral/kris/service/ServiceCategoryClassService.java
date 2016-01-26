package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TServiceCategoryClass;

public interface ServiceCategoryClassService {
	
	public void addResource(TServiceCategoryClass serviceCateClass);
	public void updateResource(TServiceCategoryClass serviceCateClass);
	public TServiceCategoryClass getResource(int id);
	public void deleteResource(int id);
	public List<TServiceCategoryClass> getResources();
	public TServiceCategoryClass getServiceCategoryClassByCateIdAndClassId(Integer cateId,int classId);
	
	

}
