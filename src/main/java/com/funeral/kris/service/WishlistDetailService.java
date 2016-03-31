package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.WishlistDetail;

public interface WishlistDetailService {
	
	public List<WishlistDetail> getResource();
	public List<WishlistDetail> getResourceByWishListId(int wishListId);
	public void addResource(WishlistDetail wishlistDetail);
	public void updateResource(WishlistDetail wishlistDetail);
	public WishlistDetail getResource(int id);
	public void deleteResource(int id);
	public List<WishlistDetail> getResources(HttpServletRequest request);
    public void deleteAllResources(String condition);
}
