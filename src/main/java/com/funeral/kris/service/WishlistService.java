package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.Wishlist;

public interface WishlistService {
	
	public void addResource(Wishlist wishlist);
	public void updateResource(Wishlist wishlist);
	public Wishlist getResource(int id);
	public void deleteResource(int id);
	public List<Wishlist> getResources();

}
