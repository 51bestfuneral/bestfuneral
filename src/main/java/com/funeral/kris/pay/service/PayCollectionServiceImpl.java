package com.funeral.kris.pay.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.funeral.kris.constants.COLLECTION;
import com.funeral.kris.constants.EXPRESS;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.Order;
import com.funeral.kris.model.TFeeCollection;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.pay.dao.PayDAO;
import com.funeral.kris.pay.dao.PayDAOImpl;
import com.funeral.kris.util.AlipayUtil;

public class PayCollectionServiceImpl implements PayCollectionService {

	private PayDAO dao;

	public PayCollectionServiceImpl() {

		this.dao = new PayDAOImpl();

	}

	@Override
	public void initFeeCollection(String orderNo) {

		Order order = dao.getOrderByOrderNo(orderNo);

		TFeeCollection feeCollection = this.loadPayableFeeCollectionByOrderNo(orderNo);

		if (feeCollection == null) {

			feeCollection = new TFeeCollection();

			feeCollection.setAmount(order.getPayableAmount());
			feeCollection.setCollectionType(COLLECTION.collection_type_alipay);
			feeCollection.setCurrencyId(1);
			feeCollection.setCurrencyRate(BigDecimal.ONE);
			feeCollection.setOrderId(order.getOrderId());
			feeCollection.setOrderNo(orderNo);
			feeCollection.setDescription(order.getSubject());
			feeCollection.setStatusId(COLLECTION.collection_init);
			feeCollection.setSubject(orderNo);
			feeCollection.setTradeStatus(COLLECTION.collection_trade_status_init);

			dao.createFeeCollection(feeCollection);

		} else {
			feeCollection.setAmount(order.getPayableAmount());
			feeCollection.setCollectionType(COLLECTION.collection_type_alipay);
			feeCollection.setCurrencyId(1);
			feeCollection.setCurrencyRate(BigDecimal.ONE);
			feeCollection.setOrderId(order.getOrderId());
			feeCollection.setOrderNo(orderNo);
			feeCollection.setDescription(order.getSubject());
			feeCollection.setStatusId(COLLECTION.collection_init);
			feeCollection.setSubject(orderNo);
			feeCollection.setTradeStatus(COLLECTION.collection_trade_status_init);
			dao.saveFeeCollection(feeCollection);
		}

	}

	@Override
	public int completeCollection(Map<String, String> params) {
		
		
		System.out.println(this.getClass()+"   completeCollection    params="+params);


		String orderNo = params.get("out_trade_no");
		
		
		System.out.println(this.getClass()+"   completeCollection    orderNo="+orderNo);


		TFeeCollection feeCollection = this.loadPayableFeeCollectionByOrderNo(orderNo);
		
		
		System.out.println(this.getClass()+"   completeCollection    feeCollection="+feeCollection);
		
		
		if(feeCollection==null){
			
			return -1;
		}

		
		
		
		
		System.out.println(this.getClass()+"   completeCollection    ------------------");
		
//		feeCollection.setOrderId(orderId);
		feeCollection.setOrderNo(orderNo);
		feeCollection.setNotifyTime(params.get("notify_time"));
		feeCollection.setAmount(new BigDecimal(params.get("price")));
		feeCollection.setNotifyType(params.get("notify_type"));
		feeCollection.setOutTradeNo(params.get("trade_no"));
		feeCollection.setSignType("MD5");
//		feeCollection.setPayerId(Integer.parseInt(params.get("buyer_id")));
		feeCollection.setSellerId(params.get("seller_id"));
		feeCollection.setNotifyId(params.get("notify_id"));
		feeCollection.setTradeNo(params.get("out_trade_no"));
		feeCollection.setDescription(params.get("body"));
		feeCollection.setGmtCreate(params.get("gmt_create"));
		feeCollection.setGmtPayment(params.get("gmt_payment"));
		feeCollection.setSign(params.get("sign"));
		feeCollection.setTradeStatus(COLLECTION.collection_trade_status_completed);
		feeCollection.setBuyerEmail(params.get("buyer_email"));
		feeCollection.setSellerEmail(params.get("seller_email"));
		feeCollection.setQuantity(params.get("quantity"));
		feeCollection.setSubject(params.get("out_trade_no"));
//		feeCollection.setDiscount(new BigDecimal(params.get("buyer_email")));
		feeCollection.setUseCoupon(params.get("use_coupon"));
		feeCollection.setIsTotalFeeAdjust(params.get("is_total_fee_adjust"));
		dao.saveFeeCollection(feeCollection);
		
		
		

		
		Order order=dao.getOrderByOrderNo(orderNo);

		
		
		//修改wishlist
		
		int userId=order.getUserId();
		
		System.out.println(this.getClass()+"   completeCollection    ---------------userId---"+userId);

		
		
		Wishlist  wishlist =dao.getwishListByUserId(userId);
		
		wishlist.setPrice(BigDecimal.ZERO);
		
		wishlist.setOriginalPirce(BigDecimal.ZERO);
		
		dao.updateWishlist(wishlist);
		
		//修改wishlist detail
		
		
		System.out.println(this.getClass()+"   completeCollection    ---------------getWishlistId---"+wishlist.getWishlistId());

		
		List<WishlistDetail>  wishlistDetailList= dao.getSelectedWishlistDetailByWishListId(wishlist.getWishlistId());
		
		
		Iterator  wishlistDetailListIterator=     wishlistDetailList.iterator();
		
		while(wishlistDetailListIterator.hasNext()){
			
			WishlistDetail  detail=	(WishlistDetail) wishlistDetailListIterator.next();
			
			
			dao.deleteWishlistDetail(detail.getWishlistDetailId());
			
			
		}
		
		
		System.out.println(this.getClass()+"   completeCollection    ---------------order---"+orderNo);

		//修改Order状态
		
//		Order  order=	dao.getOrderByOrderNo(orderNo);

		order.setStatusId(AlipayUtil.order_completed);
		
		//修改express 支付成功
		
		List<ExpressInfo>  expressInfoList=dao.getUncompledExpressInfoByUserId(userId, EXPRESS.express_status_init);
		
		System.out.println(this.getClass()+"   completeCollection    ---------------expressInfoList---"+expressInfoList);

		
		ExpressInfo  currentExpress=expressInfoList.get(0);
		
		currentExpress.setStatusId(EXPRESS.express_status_paied);	
		
		dao.updateExpressInfo(currentExpress);
		
		//修改联系人
		
//		ContactInfo  contactInfo=contactInfoService.getUsingContacter(userId);
//		
//		
//		
//		contactInfo.setStatusId(ContactInfoController.IN_RELEASED);
//		
//		contactInfoService.updateResource(contactInfo);
		
		return feeCollection.getCollectionId();

	
	}

	@Override
	public void deleteResource(int collectionId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TFeeCollection> loadFeeCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TFeeCollection loadPayableFeeCollectionByOrderNo(String orderNo) {
		
		
		
		return dao.loadPayableFeeCollectionByOrderNo(orderNo);
	}

	@Override
	public TFeeCollection loadFeeCollectionById(int collectionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upCollection(TFeeCollection tFeeCollection) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TFeeCollection> loadFeeCollectionByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
