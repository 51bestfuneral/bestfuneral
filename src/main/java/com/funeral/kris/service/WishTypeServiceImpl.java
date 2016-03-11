package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.WishTypeDAO;
import com.funeral.kris.model.WishType;

@Service
@Transactional
public class WishTypeServiceImpl implements WishTypeService {

	@Autowired
	private WishTypeDAO WishTypeDAO;

	public void addResource(WishType wishType) {
		WishTypeDAO.save(wishType);		
	}

	public void updateResource(WishType wishType) {
		WishTypeDAO.save(wishType);
	}

	public WishType getResource(int id) {
		return WishTypeDAO.findOne(id);
	}

	public void deleteResource(int id) {
		WishTypeDAO.delete(id);
	}

	public List<WishType> getResources() {
		List<WishType> wishTypeList = new ArrayList<WishType>();
		Iterable<WishType> wishTypeIter = WishTypeDAO.findAll();
		Iterator<WishType> iter = wishTypeIter.iterator();
		while(iter.hasNext()) {
			WishType wishType = iter.next();
			wishTypeList.add(wishType);
		}
		return wishTypeList;
	}
}
