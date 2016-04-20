package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.FuneralDetail;

public interface FuneralDetailService {
	
	public void addResource(FuneralDetail funeralDetail);
	public void updateResource(FuneralDetail funeralDetail);
	public FuneralDetail getResource(int id);
	public void deleteResource(int id);
	public List<FuneralDetail> getResources();

}
