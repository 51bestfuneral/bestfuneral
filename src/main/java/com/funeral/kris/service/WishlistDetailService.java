package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.WishlistDetail;

public interface WishlistDetailService {
	
	public void addResource(WishlistDetail wishlistDetail);
	public void updateResource(WishlistDetail wishlistDetail);
	public WishlistDetail getResource(int id);
	public void deleteResource(int id);
	public List<WishlistDetail> getResources();

}
