package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.WishlistDAO;
import com.funeral.kris.model.Order;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistDAO WishlistDAO;
	@Autowired
	private EntityManager em;

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

	
	
	
	public List<Wishlist> getResources(HttpServletRequest request) {
		String a = null;
		try {
		    a = SqlHelper.getSqlFromRequest("Wishlist", request);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<Wishlist> wishList = query.getResultList();
		return wishList;
	}

	public List<Wishlist> getResources(Map<String, String> paramsMap) {
		String a = null;
		try {
		    a = SqlHelper.getSqlFromMap("Wishlist", paramsMap);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<Wishlist> wishList = query.getResultList();
		return wishList;
	}

	@Override
	public List<Wishlist> getResources() {

		Iterable<Wishlist> Iterator=	WishlistDAO.findAll();
		List<Wishlist> list = new ArrayList<Wishlist>();

		Iterator<Wishlist> iter = Iterator.iterator();
		while (iter.hasNext()) {
			Wishlist wishlist = iter.next();
			list.add(wishlist);
		}
		
		return list;
	
	}
}
