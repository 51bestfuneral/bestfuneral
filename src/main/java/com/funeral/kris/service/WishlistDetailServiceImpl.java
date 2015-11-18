package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.WishlistDetailDAO;
import com.funeral.kris.model.WishlistDetail;

@Service
@Transactional
public class WishlistDetailServiceImpl implements WishlistDetailService {

	@Autowired
	private WishlistDetailDAO WishlistDetailDAO;

	public void addResource(WishlistDetail wishlistDetail) {
		WishlistDetailDAO.save(wishlistDetail);
	}

	public void updateResource(WishlistDetail wishlistDetail) {
		WishlistDetailDAO.save(wishlistDetail);
	}

	public WishlistDetail getResource(int id) {
		return WishlistDetailDAO.findOne(id);
	}

	public void deleteResource(int id) {
		WishlistDetailDAO.delete(id);
	}

	public List<WishlistDetail> getResources() {
		List<WishlistDetail> wishlistDetailList = new ArrayList<WishlistDetail>();
		Iterable<WishlistDetail> wishlistDetailIter = WishlistDetailDAO.findAll();
		Iterator<WishlistDetail> iter = wishlistDetailIter.iterator();
		while(iter.hasNext()) {
			WishlistDetail wishlistDetail = iter.next();
			wishlistDetailList.add(wishlistDetail);
		}
		return wishlistDetailList;
	}
}
