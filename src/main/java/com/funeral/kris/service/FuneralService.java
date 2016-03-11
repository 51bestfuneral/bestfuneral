package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.Funeral;

public interface FuneralService {
	
	public void addResource(Funeral funeral);
	public void updateResource(Funeral funeral);
	public Funeral getResource(int id);
	public void deleteResource(int id);
	public List<Funeral> getResources();

}
