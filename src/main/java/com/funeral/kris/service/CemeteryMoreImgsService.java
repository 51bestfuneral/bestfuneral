package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TCemeteryMoreImgs;

public interface CemeteryMoreImgsService {
	public void addResource(TCemeteryMoreImgs tCemeteryMoreImgs);
	public void updateResource(TCemeteryMoreImgs tCemeteryMoreImgs);
	public TCemeteryMoreImgs getResource(int id);
	public void deleteResource(int id);
	public List<TCemeteryMoreImgs> getResources();
	public List<TCemeteryMoreImgs> findByCemeteryId(int id);
}
