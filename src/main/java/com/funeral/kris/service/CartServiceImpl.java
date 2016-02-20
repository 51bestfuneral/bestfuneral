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

import com.funeral.kris.dao.CartDAO;
import com.funeral.kris.model.Cart;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO CartDAO;
	@Autowired
	private EntityManager em;

	public void addResource(Cart cart) {
		CartDAO.save(cart);		
	}

	public void updateResource(Cart cart) {
		CartDAO.save(cart);
	}

	public Cart getResource(int id) {
		return CartDAO.findOne(id);
	}

	public void deleteResource(int id) {
		CartDAO.delete(id);
	}

	public List<Cart> getResources(HttpServletRequest request) {
		String a = null;
		try {
		    a = SqlHelper.getSqlFromRequest("Cart", request);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<Cart> carts = query.getResultList();
		return carts;
	}
}
