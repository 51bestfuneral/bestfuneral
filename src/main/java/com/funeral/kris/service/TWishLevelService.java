package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TWishLevel;

public interface TWishLevelService {
	
	public void addResource(TWishLevel tWishLevel);
	public void updateResource(TWishLevel tWishLevel);
	public TWishLevel getResource(int id);
	public void deleteResource(int id);
	public List<TWishLevel> getResources();

}
