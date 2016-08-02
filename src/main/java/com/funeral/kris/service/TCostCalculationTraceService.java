package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TCostCalculationTrace;

public interface TCostCalculationTraceService {


	public void addResource(TCostCalculationTrace  trace);
	public void updateResource(TCostCalculationTrace trace);
	public TCostCalculationTrace getResource(int id);
	public void deleteResource(int id);
	public List<TCostCalculationTrace> getResources();
	public List<TCostCalculationTrace> loadByUserId(int userId);
	public boolean selected(int cateId,int classId,List<TCostCalculationTrace> traceList);
    public void deleteByCateIdClassIdUserId(int cateId,int classId,int userId);
    public void deleteByCateIdUserId(int cateId,int userId);
    public TCostCalculationTrace loadByCateIdClassIdUserId(int cateId,int classId,int userId);
	
}
