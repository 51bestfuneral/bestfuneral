package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.WishOrder;

public interface WishOrderService {
	
	public void addResource(WishOrder wishOrder);
	public void updateResource(WishOrder wishOrder);
	public WishOrder getResource(int id);
	public void deleteResource(int id);
	public WishOrder getLatestOpenWishOrderForSet(int userId);
	public List<WishOrder> getResource();
	public List<WishOrder> getResourceByUserId(int userId);
	public List<WishOrder> getOpenWishOrderListByPayWishOrderId(int payWishOrderId);
	public WishOrder getWishOrderByOrderNo(String orderNo);


}
