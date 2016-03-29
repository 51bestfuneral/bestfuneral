package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.Order;

public interface OrderService {

	public void addResource(Order order);
	public void updateResource(Order order);
	public Order getResource(int id);
	public void deleteResource(int id);
	public List<Order> getResources();
	public Order getByUserId(int userId);
	
}
