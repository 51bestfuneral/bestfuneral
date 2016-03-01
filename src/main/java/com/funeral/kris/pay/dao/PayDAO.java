package com.funeral.kris.pay.dao;

import java.util.List;

import com.funeral.kris.model.ContactInfo;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.Order;
import com.funeral.kris.model.TFeeCollection;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;

public interface PayDAO {
	
public 	Order  getOrderByOrderNo(String orderNo)	;	
public TFeeCollection loadPayableFeeCollectionByOrderNo(String orderNo);
public  void saveFeeCollection(TFeeCollection feeCollection);	
public  void createFeeCollection(TFeeCollection feeCollection);	
public  Wishlist  getwishListByUserId(int userId);
public void  updateWishlist(Wishlist wishlist);
public List<WishlistDetail>  getSelectedWishlistDetailByWishListId(int wishlistId);
public void deleteWishlistDetail(int wishlistDetailId);
public  List<ExpressInfo>  getUncompledExpressInfoByUserId(int userId,int statusId);
public  ExpressInfo  getUncompledExpressInfoByWishOrderId(int wishOrderId,int statusId) throws Exception;
public void  updateExpressInfo(ExpressInfo expressInfo) throws Exception;
public void releaseUsingContacter(int userId);
public 	void  updateOrderStatus(String orderNo,int statusId)throws Exception	;	
public void updateWishOrderStatus(int wishOrderId,int statusId) throws Exception;

}
