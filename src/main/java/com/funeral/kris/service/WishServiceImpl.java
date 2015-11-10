package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.WishDAO;
import com.funeral.kris.model.Wish;

@Service
@Transactional
public class WishServiceImpl implements WishService {

	@Autowired
	private WishDAO WishDAO;

	public void addResource(Wish wish) {
		WishDAO.save(wish);		
	}

	public void updateResource(Wish wish) {
		WishDAO.save(wish);
	}

	public Wish getResource(int id) {
		return WishDAO.findOne(id);
	}

	public void deleteResource(int id) {
		WishDAO.delete(id);
	}

	public List<Wish> getResources() {
		List<Wish> wishList = new ArrayList<Wish>();
		Iterable<Wish> wishIter = WishDAO.findAll();
		Iterator<Wish> iter = wishIter.iterator();
		while(iter.hasNext()) {
			Wish wish = iter.next();
			wishList.add(wish);
		}
		return wishList;
	}
}
