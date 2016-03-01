package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.FuneralDetailDAO;
import com.funeral.kris.model.FuneralDetail;

@Service
@Transactional
public class FuneralDetailServiceImpl implements FuneralDetailService {

	@Autowired
	private FuneralDetailDAO FuneralDetailDAO;

	public void addResource(FuneralDetail funeralDetail) {
		FuneralDetailDAO.save(funeralDetail);		
	}

	public void updateResource(FuneralDetail funeralDetail) {
		FuneralDetailDAO.save(funeralDetail);
	}

	public FuneralDetail getResource(int id) {
		return FuneralDetailDAO.findOne(id);
	}

	public void deleteResource(int id) {
		FuneralDetailDAO.delete(id);
	}

	public List<FuneralDetail> getResources() {
		List<FuneralDetail> funeralDetailList = new ArrayList<FuneralDetail>();
		Iterable<FuneralDetail> funeralDetailIter = FuneralDetailDAO.findAll();
		Iterator<FuneralDetail> iter = funeralDetailIter.iterator();
		while(iter.hasNext()) {
			FuneralDetail funeralDetail = iter.next();
			funeralDetailList.add(funeralDetail);
		}
		return funeralDetailList;
	}
}
