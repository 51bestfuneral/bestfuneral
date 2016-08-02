package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.TaoConfig;

public interface TaoConfigService {

	public void addTaoConfig(TaoConfig taoConfig);
	public void updateTaoConfig(TaoConfig taoConfig);
	public TaoConfig getTaoConfig(int id);
	public void deleteTaoConfig(int id);
	public List<TaoConfig> getTaoConfigs();
	
}
