package com.funeral.kris.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.WishOrder;
import com.funeral.kris.model.Wishlist;

public interface WishlistService {
	
	public void addResource(Wishlist wishlist);
	public void updateResource(Wishlist wishlist);
	public Wishlist getResource(int id);
	public Wishlist getResourceByUserId(int id);
	public void deleteResource(int id);
	public List<Wishlist> getResources(HttpServletRequest request);
	public List<Wishlist> getResources(Map<String, String> paramMap);
	public List<Wishlist> getResources();
	public List<WishOrder> getWishOrderByUserId(int userId);

}
