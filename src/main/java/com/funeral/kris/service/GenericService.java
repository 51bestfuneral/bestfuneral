package com.funeral.kris.service;

import java.util.List;

public interface GenericService {
	
	public <T> void addResource(T resource);
	public <T> void updateResource(T resource);
	public Object getResource(int id);
	public void deleteResource(int id);
	public <T> List<T> getResources();

}
