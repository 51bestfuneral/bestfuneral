package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.bean.WishCateBean;
import com.funeral.kris.model.TWishCategory;

public interface TWishCategoryService {
	
	public void addResource(TWishCategory tWishCategory);
	public void updateResource(TWishCategory tWishCategory);
	public TWishCategory getResource(int id);
	public void deleteResource(int id);
	public List<TWishCategory> getResources();
	public List<WishCateBean> listWishCates();

}
