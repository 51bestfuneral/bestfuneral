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

import com.funeral.kris.dao.OrderDetailDAO;
import com.funeral.kris.model.OrderDetail;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailDAO OrderDetailDAO;
	@Autowired
	private EntityManager em;

	public void addResource(OrderDetail orderDetail) {
		OrderDetailDAO.save(orderDetail);		
	}

	public void updateResource(OrderDetail orderDetail) {
		OrderDetailDAO.save(orderDetail);
	}

	public OrderDetail getResource(int id) {
		return OrderDetailDAO.findOne(id);
	}

	public void deleteResource(int id) {
		OrderDetailDAO.delete(id);
	}

	public List<OrderDetail> getResources(HttpServletRequest request) {
		String a = null;
		try {
		    a = SqlHelper.getSqlFromRequest("OrderDetail", request);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<OrderDetail> orderDetails = query.getResultList();
		return orderDetails;
	}
}
