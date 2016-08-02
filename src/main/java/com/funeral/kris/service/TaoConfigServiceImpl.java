package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.funeral.kris.dao.TaoConfigDAO;
import com.funeral.kris.model.TaoConfig;

public class TaoConfigServiceImpl implements TaoConfigService {
	@Autowired
	private TaoConfigDAO taoConfigDAO;

	public void addTaoConfig(TaoConfig team) {
		taoConfigDAO.save(team);		
	}

	public void updateTaoConfig(TaoConfig team) {
		taoConfigDAO.save(team);
	}

	public TaoConfig getTaoConfig(int id) {
		return taoConfigDAO.findOne(id);
	}

	public void deleteTaoConfig(int id) {
		taoConfigDAO.delete(id);
	}

	public List<TaoConfig> getTaoConfigs() {
		List<TaoConfig> teamList = new ArrayList<TaoConfig>();
		Iterable<TaoConfig> teamIter = taoConfigDAO.findAll();
		Iterator<TaoConfig> iter = teamIter.iterator();
		while(iter.hasNext()) {
			TaoConfig taoConfig = iter.next();
			teamList.add(taoConfig);
		}
		return teamList;
	}

}
