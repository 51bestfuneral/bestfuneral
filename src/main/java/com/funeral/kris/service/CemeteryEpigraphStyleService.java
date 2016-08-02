package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TCemeteryEpigraphStyle;

public interface CemeteryEpigraphStyleService {
	
	public void addResource(TCemeteryEpigraphStyle tCemeteryEpigraphStyle);
	public void updateResource(TCemeteryEpigraphStyle tCemeteryEpigraphStyle);
	public TCemeteryEpigraphStyle getResource(int id);
	public void deleteResource(int id);
	public List<TCemeteryEpigraphStyle> getResources();
	public List<TCemeteryEpigraphStyle> findByCemeteryId(int id);

}
