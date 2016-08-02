package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TCemeteryGraveZone;

public interface CemeteryGraveZoneService {
	public void addResource(TCemeteryGraveZone tCemeteryGraveZone);
	public void updateResource(TCemeteryGraveZone tCemeteryGraveZone);
	public TCemeteryGraveZone getResource(int id);
	public void deleteResource(int id);
	public List<TCemeteryGraveZone> getResources();
	public List<TCemeteryGraveZone> findByCemeteryId(int id);
	
}
