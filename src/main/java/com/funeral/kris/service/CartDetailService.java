package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.CartDetail;

public interface CartDetailService {
	
	public void addResource(CartDetail cartDetail);
	public void updateResource(CartDetail cartDetail);
	public CartDetail getResource(int id);
	public void deleteResource(int id);
	public List<CartDetail> getResources(HttpServletRequest request);
	public List<CartDetail> getResourceByCartId(int cartId);
	public List<CartDetail> getSelectedCartDetailsByCartId(int cartId);
	public boolean isAllSelected(int cartId);
}
