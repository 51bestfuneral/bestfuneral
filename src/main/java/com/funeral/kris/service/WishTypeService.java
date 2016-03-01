package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.WishType;

public interface WishTypeService {
	
	public void addResource(WishType wishType);
	public void updateResource(WishType wishType);
	public WishType getResource(int id);
	public void deleteResource(int id);
	public List<WishType> getResources();

}
