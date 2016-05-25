package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.OrderDetail;

public interface OrderDetailService {

	public void addResource(OrderDetail orderDetail);
	public void updateResource(OrderDetail orderDetail);
	public OrderDetail getResource(int id);
	public void deleteResource(int id);
	public List<OrderDetail> getResources(HttpServletRequest request);
	public List<OrderDetail> getResourcesByWishOrderId(int wishOrderId);
	public void deleteResourceByOrderId(int id);
}
