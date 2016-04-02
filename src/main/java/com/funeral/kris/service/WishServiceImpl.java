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

import com.funeral.kris.dao.WishDAO;
import com.funeral.kris.model.Question;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class WishServiceImpl implements WishService {

	@Autowired
	private WishDAO WishDAO;
	@Autowired
	private EntityManager em;

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

	public List<Wish> getResources(HttpServletRequest request) {
		String a = null;
		try {
			a = SqlHelper.getSqlFromRequest("Wish", request);
		} catch (Exception e) {

		}
		Query query = em.createQuery(a);
		List<Wish> wishList = query.getResultList();
		return wishList;
	}

	@Override
	public List<Wish> getResources() {

		Iterable<Wish> Iterator = WishDAO.findAll();
		List<Wish> list = new ArrayList<Wish>();

		Iterator<Wish> iter = Iterator.iterator();
		while (iter.hasNext()) {
			Wish wish = iter.next();
			list.add(wish);
		}

		return list;

	}
}
