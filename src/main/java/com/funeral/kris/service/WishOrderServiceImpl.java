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

import com.funeral.kris.dao.WishOrderDAO;
import com.funeral.kris.model.WishOrder;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class WishOrderServiceImpl implements WishOrderService {

	@Autowired
	private WishOrderDAO WishOrderDAO;
	@Autowired
	private EntityManager em;

	public void addResource(WishOrder wishOrder) {
		WishOrderDAO.save(wishOrder);		
	}

	public void updateResource(WishOrder wishOrder) {
		WishOrderDAO.save(wishOrder);
	}

	public WishOrder getResource(int id) {
		return WishOrderDAO.findOne(id);
	}

	public void deleteResource(int id) {
		WishOrderDAO.delete(id);
	}

	public List<WishOrder> getResources(HttpServletRequest request) {
		String a = null;
		try {
		    a = SqlHelper.getSqlFromRequest("WishOrder", request);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<WishOrder> wishOrders = query.getResultList();
		return wishOrders;
	}
}
