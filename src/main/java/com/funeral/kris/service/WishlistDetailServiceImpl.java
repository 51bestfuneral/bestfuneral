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

import com.funeral.kris.dao.WishlistDetailDAO;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class WishlistDetailServiceImpl implements WishlistDetailService {

	@Autowired
	private WishlistDetailDAO WishlistDetailDAO;
	@Autowired
	private EntityManager em;

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

	public List<WishlistDetail> getResources(HttpServletRequest request) {
		String a = null;

		try {
			 a = SqlHelper.getSqlFromRequest("WishlistDetail", request);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<WishlistDetail> WishlistDetails = query.getResultList();
		return WishlistDetails;
	}
}
