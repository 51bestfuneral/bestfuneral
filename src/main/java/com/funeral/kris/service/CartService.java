package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.Cart;

public interface CartService {
	
	public void addResource(Cart cart);
	public void updateResource(Cart cart);
	public Cart getResource(int id);
	public void deleteResource(int id);
	public List<Cart> getResources(HttpServletRequest request);
}
