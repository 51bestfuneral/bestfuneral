package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.WishlistDAO;
import com.funeral.kris.model.Wishlist;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistDAO WishlistDAO;

	public void addResource(Wishlist wishlist) {
		WishlistDAO.save(wishlist);		
	}

	public void updateResource(Wishlist wishlist) {
		WishlistDAO.save(wishlist);
	}

	public Wishlist getResource(int id) {
		return WishlistDAO.findOne(id);
	}

	public void deleteResource(int id) {
		WishlistDAO.delete(id);
	}

	public List<Wishlist> getResources() {
		List<Wishlist> wishlistList = new ArrayList<Wishlist>();
		Iterable<Wishlist> wishlistIter = WishlistDAO.findAll();
		Iterator<Wishlist> iter = wishlistIter.iterator();
		while(iter.hasNext()) {
			Wishlist wishlist = iter.next();
			wishlistList.add(wishlist);
		}
		return wishlistList;
	}
}
