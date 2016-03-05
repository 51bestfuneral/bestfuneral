package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TWishLevelRelation;

public interface TWishLevelRelationService {

	public void addResource(TWishLevelRelation tWishLevelRelation);
	public void updateResource(TWishLevelRelation tWishLevelRelation);
	public TWishLevelRelation getResource(int id);
	public void deleteResource(int id);
	public List<TWishLevelRelation> getResources();
	
	
}
