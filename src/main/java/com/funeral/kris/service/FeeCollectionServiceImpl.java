package com.funeral.kris.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.constants.COLLECTION;
import com.funeral.kris.constants.EXPRESS;
import com.funeral.kris.controller.ContactInfoController;
import com.funeral.kris.dao.FeeCollectionDAO;
import com.funeral.kris.model.ContactInfo;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.Order;
import com.funeral.kris.model.TFeeCollection;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.util.AlipayUtil;

@Service
@Transactional
public class FeeCollectionServiceImpl implements FeeCollectionService {
	@Autowired
	private FeeCollectionDAO feeCollectionDAO;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private ExpressInfoService expressInfoService;
	@Autowired
	private ContactInfoService contactInfoService;
	
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Override
	public void initFeeCollection(String orderNo) {
		
		System.out.println("----------------  orderNo="+orderNo+"   orderService="+orderService);
		
		
		Order  order=	orderService.getResourceByOrderNo(orderNo);

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

			feeCollectionDAO.save(feeCollection);

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
			feeCollectionDAO.save(feeCollection);

		}

	}

	@Override
	public int completeCollection(Map<String, String> params) {

		String orderNo = params.get("out_trade_no");

		TFeeCollection feeCollection = this.loadPayableFeeCollectionByOrderNo(orderNo);
		
		if(feeCollection==null){
			
			feeCollection=new TFeeCollection();
		}

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
		feeCollectionDAO.save(feeCollection);
		
		
		
		
		//修改wishlist
		
		int userId=Integer.parseInt(params.get("user_id"));
		
		Wishlist  wishlist =wishlistService.getResourceByUserId(userId);
		
		wishlist.setPrice(BigDecimal.ZERO);
		
		wishlist.setOriginalPirce(BigDecimal.ZERO);
		
		wishlistService.updateResource(wishlist);
		
		//修改wishlist detail
		
		
		List<WishlistDetail>  wishlistDetailList= wishlistDetailService.getSelectedWishlistDetailByWishListId(wishlist.getWishlistId());
		
		
		Iterator  wishlistDetailListIterator=     wishlistDetailList.iterator();
		
		while(wishlistDetailListIterator.hasNext()){
			
			WishlistDetail  detail=	(WishlistDetail) wishlistDetailListIterator.next();
			
			
			wishlistDetailService.deleteResource(detail.getWishlistDetailId());
			
			
		}
		
		
		
		//修改Order状态
		
		Order  order=	orderService.getResourceByOrderNo(orderNo);

		order.setStatusId(AlipayUtil.order_completed);
		
		//修改express 支付成功
		
		List<ExpressInfo>  expressInfoList=expressInfoService.getUncompledExpressInfoByUserId(userId, EXPRESS.express_status_init);
		
		ExpressInfo  currentExpress=expressInfoList.get(0);
		
		currentExpress.setStatusId(EXPRESS.express_status_paied);	
		
		expressInfoService.updateResource(currentExpress);
		
		//修改联系人
		
		ContactInfo  contactInfo=contactInfoService.getUsingContacter(userId);
		
		
		
		contactInfo.setStatusId(ContactInfoController.IN_RELEASED);
		
		contactInfoService.updateResource(contactInfo);
		
		return feeCollection.getCollectionId();

	}

	@Override
	public void deleteResource(int collectionId) {
		feeCollectionDAO.delete(collectionId);
	}

	@Override
	public List<TFeeCollection> loadFeeCollection() {

		List<TFeeCollection> tFeeCollectionList = new ArrayList<TFeeCollection>();
		Iterable<TFeeCollection> iterator = feeCollectionDAO.findAll();
		if (iterator == null) {
			return new ArrayList<TFeeCollection>();
		}
		Iterator<TFeeCollection> iter = iterator.iterator();
		while (iter.hasNext()) {

			TFeeCollection collection = iter.next();
			tFeeCollectionList.add(collection);
		}
		return tFeeCollectionList;

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

		List<TFeeCollection> collectionList = this.loadFeeCollection();

		List<TFeeCollection> feeCollectionList = new ArrayList<TFeeCollection>();

		if (collectionList == null) {

			return new ArrayList<TFeeCollection>();

		} else {

			Iterator iterator = collectionList.iterator();

			while (iterator.hasNext()) {
				TFeeCollection collection = (TFeeCollection) iterator.next();

				feeCollectionList.add(collection);
			}

		}

		return feeCollectionList;
	}

	@Override
	public TFeeCollection loadPayableFeeCollectionByOrderNo(String orderNo) {

		List<TFeeCollection> tFeeCollectionList = this.loadFeeCollectionByOrderNo(orderNo);

		if (tFeeCollectionList == null || tFeeCollectionList.size() > 0) {

			return null;
		} else {

			Iterator iterator = tFeeCollectionList.iterator();

			while (iterator.hasNext()) {

				TFeeCollection collection = (TFeeCollection) iterator.next();

				if (collection.getOrderNo().equals(orderNo)
						&& collection.getStatusId().intValue() != COLLECTION.collection_pay_success
						&& COLLECTION.collection_pay_expired != collection.getStatusId().intValue()) {
					
					return collection;

				}

			}

		}
		return null;
	}
}
