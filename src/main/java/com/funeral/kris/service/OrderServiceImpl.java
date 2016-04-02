package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.OrderDAO;
import com.funeral.kris.model.Order;
import com.funeral.kris.util.AlipayUtil;
@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDAO orderDAO;
	@Override
	public void addResource(Order order) {
		orderDAO.save(order);		
	}

	@Override
	public void updateResource(Order order) {
		orderDAO.save(order);		
		
	}

	@Override
	public Order getResource(int id) {
		Order order=orderDAO.findOne(id);
		return order;
	}

	@Override
	public void deleteResource(int id) {
		orderDAO.delete(id);		
	}

	@Override
	public List<Order> getResources() {
		Iterable<Order> Iterator=	orderDAO.findAll();
		List<Order> list = new ArrayList<Order>();

		Iterator<Order> iter = Iterator.iterator();
		while (iter.hasNext()) {
			Order order = iter.next();
			list.add(order);
		}
		
		return list;
	}

	@Override
	public Order getByUserId(int userId) {

		List<Order> list=this.getResources();
		
		if(list==null){
			
			return null;
		}else{
			
			
			Iterator<Order> iter=	list.iterator();
			
			while(iter.hasNext()){
				
				Order order = iter.next();

				if(order.getUserId().intValue()==userId&&order.getStatusId().intValue()!=AlipayUtil.completed){
					
					return order;
				}
				
				
			}
			
		}
		return null;
	}
	

	
	

}
