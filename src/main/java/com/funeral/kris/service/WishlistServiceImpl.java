package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.WishlistDAO;
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
}
