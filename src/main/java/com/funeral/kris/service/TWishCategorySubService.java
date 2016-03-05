package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TWishCategorySub;

public interface TWishCategorySubService {
	
	
	public void addResource(TWishCategorySub tWishCategorySub);
	public void updateResource(TWishCategorySub tWishCategorySub);
	public TWishCategorySub getResource(int id);
	public void deleteResource(int id);
	public List<TWishCategorySub> getResources();
	public List<TWishCategorySub> loadByCateId(int cateId);

}
