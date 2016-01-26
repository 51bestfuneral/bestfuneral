package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.CemeteryDAO;
import com.funeral.kris.model.Cemetery;

@Service
@Transactional
public class CemeteryServiceImpl implements CemeteryService {

	@Autowired
	private CemeteryDAO CemeteryDAO;

	public void addResource(Cemetery cemetery) {
		CemeteryDAO.save(cemetery);		
	}

	public void updateResource(Cemetery cemetery) {
		CemeteryDAO.save(cemetery);
	}

	public Cemetery getResource(int id) {
		return CemeteryDAO.findOne(id);
	}

	public void deleteResource(int id) {
		CemeteryDAO.delete(id);
	}

	public List<Cemetery> getResources() {
		List<Cemetery> cemeteryList = new ArrayList<Cemetery>();
		Iterable<Cemetery> cemeteryIter = CemeteryDAO.findAll();
		Iterator<Cemetery> iter = cemeteryIter.iterator();
		while(iter.hasNext()) {
			Cemetery cemetery = iter.next();
			cemeteryList.add(cemetery);
		}
		return cemeteryList;
	}
}
