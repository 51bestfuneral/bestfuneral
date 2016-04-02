package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.ExpressInfo;

public interface ExpressInfoService {

	
	public void addResource(ExpressInfo expressInfo);
	public void updateResource(ExpressInfo expressInfo);
	public List<ExpressInfo> getByUserId(int userId);
	public List<ExpressInfo> getUncompledExpressInfoByUserId(int userId,int statusId);
	public ExpressInfo getResource(long id);
	public void deleteResource(long id);
	public List<ExpressInfo> getResources();
	
}
