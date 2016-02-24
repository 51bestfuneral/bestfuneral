package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TCemeteryPrice;

public interface CemeteryPriceService {

	public void addResource(TCemeteryPrice cemeteryPrice);
	public void updateResource(TCemeteryPrice cemeteryPrice);
	public TCemeteryPrice getResource(int id);
	public void deleteResource(int id);
	public List<TCemeteryPrice> getResources();
	public List<TCemeteryPrice> getCemeteryPriceListByCemeteryId(int cemeteryId);
	
	public List<TCemeteryPrice> getCemeteryPriceListByGraveStyleId(int graveStyleId);
	
	public List<TCemeteryPrice> getCemeteryPriceListByEpigraphStyleId(int epigraphStyleId);
	
	
}
