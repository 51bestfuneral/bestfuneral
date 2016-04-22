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

import com.funeral.kris.dao.CartDetailDAO;
import com.funeral.kris.model.CartDetail;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class CartDetailServiceImpl implements CartDetailService {

	@Autowired
	private CartDetailDAO CartDetailDAO;
	@Autowired
	private EntityManager em;

	public void addResource(CartDetail cartDetail) {
		CartDetailDAO.save(cartDetail);		
	}

	public void updateResource(CartDetail cartDetail) {
		CartDetailDAO.save(cartDetail);
	}

	public CartDetail getResource(int id) {
		return CartDetailDAO.findOne(id);
	}

	public void deleteResource(int id) {
		CartDetailDAO.delete(id);
	}

	public List<CartDetail> getResources(HttpServletRequest request) {
		String a = null;
		try {
		    a = SqlHelper.getSqlFromRequest("CartDetail", request);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<CartDetail> cartDetails = query.getResultList();
		return cartDetails;
	}

	@Override
	public List<CartDetail> getResourceByCartId(int cartId) {

		String a = "select q from CartDetail q where wishlistId ="+cartId;
		Query query = em.createQuery(a);
		List<CartDetail> CartDetails = query.getResultList();
		return CartDetails;

	}
}
