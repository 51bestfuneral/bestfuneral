package com.funeral.kris.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.CartDetail;
import com.funeral.kris.model.OrderDetail;
import com.funeral.kris.model.WishlistDetail;

public interface WishlistDetailService {
	
	public List<WishlistDetail> getResource();
	public List<WishlistDetail> getResourceByWishListId(int wishListId);
	public void addResource(WishlistDetail wishlistDetail);
	public void updateResource(WishlistDetail wishlistDetail);
	public WishlistDetail getResource(int id);
	public void deleteResource(int id);
	public List<WishlistDetail> getResources(HttpServletRequest request);
	public List<WishlistDetail> getSelectedWishlistDetailByWishListId(int wishListId);
    public void deleteAllResources(String condition);
    public List<WishlistDetail> getDirectWishlistDetailByWishListId(int wishListId);
    public List<WishlistDetail> getRecommendWishlistDetailByWishListId(int wishListId);
	public List<CartDetail> getResourceByCartId(int cartId);
	public List<OrderDetail> getWishOrderDetailByOrderId(int orderId);
}
