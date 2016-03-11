package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.FuneralDAO;
import com.funeral.kris.model.Funeral;

@Service
@Transactional
public class FuneralServiceImpl implements FuneralService {

	@Autowired
	private FuneralDAO FuneralDAO;

	public void addResource(Funeral funeral) {
		FuneralDAO.save(funeral);		
	}

	public void updateResource(Funeral funeral) {
		FuneralDAO.save(funeral);
	}

	public Funeral getResource(int id) {
		return FuneralDAO.findOne(id);
	}

	public void deleteResource(int id) {
		FuneralDAO.delete(id);
	}

	public List<Funeral> getResources() {
		List<Funeral> funeralList = new ArrayList<Funeral>();
		Iterable<Funeral> funeralIter = FuneralDAO.findAll();
		Iterator<Funeral> iter = funeralIter.iterator();
		while(iter.hasNext()) {
			Funeral funeral = iter.next();
			funeralList.add(funeral);
		}
		return funeralList;
	}
}
