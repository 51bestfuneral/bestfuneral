package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.WishOrder;

public interface WishOrderService {
	
	public void addResource(WishOrder wishOrder);
	public void updateResource(WishOrder wishOrder);
	public WishOrder getResource(int id);
	public void deleteResource(int id);
	public List<WishOrder> getResources(HttpServletRequest request);
}
