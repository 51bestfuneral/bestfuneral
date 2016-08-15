package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TCemeteryGraveStyle;

public interface CemeteryGraveStyleService {

	public void addResource(TCemeteryGraveStyle tCemeteryGraveStyle);
	public void updateResource(TCemeteryGraveStyle tCemeteryGraveStyle);
	public TCemeteryGraveStyle getResource(int id);
	public void deleteResource(int id);
	public List<TCemeteryGraveStyle> getResources();
	public List<TCemeteryGraveStyle> getByCemeteryId(int id);
}
