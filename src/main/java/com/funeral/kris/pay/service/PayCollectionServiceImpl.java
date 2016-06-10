package com.funeral.kris.pay.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.funeral.kris.constants.COLLECTION;
import com.funeral.kris.constants.EXPRESS;
import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.model.CartDetail;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.Order;
import com.funeral.kris.model.OrderDetail;
import com.funeral.kris.model.TFeeCollection;
import com.funeral.kris.model.Wish;
import com.funeral.kris.pay.dao.PayDAO;
import com.funeral.kris.pay.dao.PayDAOImpl;
import com.funeral.kris.service.OrderDetailService;
import com.funeral.kris.service.WishService;
import com.funeral.kris.util.AlipayUtil;

public class PayCollectionServiceImpl implements PayCollectionService {

	private static PayDAO dao=new PayDAOImpl();

	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private WishService wishService;

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
	public int completeCollection(Map<String, String> params) throws Exception {
		String orderNo = "";

		System.out.println(this.getClass() + "   completeCollection    params=" + params);

		orderNo = params.get("out_trade_no");
		
		Order order = dao.getOrderByOrderNo(orderNo);

		Integer wishOrderId=order.getWishOrderId();


		System.out.println(this.getClass() + "   completeCollection    orderNo=" + orderNo);

		TFeeCollection feeCollection = this.loadPayableFeeCollectionByOrderNo(orderNo);

		System.out.println(this.getClass() + "   completeCollection    feeCollection=" + feeCollection);

		if (feeCollection == null) {

			return -1;
		}

		System.out.println(this.getClass() + "   completeCollection    ------------------");
		feeCollection.setStatusId(COLLECTION.collection_pay_success);
		// feeCollection.setOrderId(orderId);
		feeCollection.setOrderNo(orderNo);
		feeCollection.setNotifyTime(params.get("notify_time"));
		feeCollection.setAmount(new BigDecimal(params.get("price")));
		feeCollection.setNotifyType(params.get("notify_type"));
		feeCollection.setOutTradeNo(params.get("trade_no"));
		feeCollection.setSignType("MD5");
		
		feeCollection.setOrderId(order.getOrderId());
		// feeCollection.setPayerId(Integer.parseInt(params.get("buyer_id")));
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
		// feeCollection.setDiscount(new BigDecimal(params.get("buyer_email")));
		feeCollection.setUseCoupon(params.get("use_coupon"));
		feeCollection.setIsTotalFeeAdjust(params.get("is_total_fee_adjust"));
		feeCollection.setCollectionType(Integer.parseInt(params.get("collection_type")));
		dao.saveFeeCollection(feeCollection);


        

		System.out.println(this.getClass() + "   completeCollection    ---------------order---" + orderNo);

		// 修改Order状态

		order.setStatusId(AlipayUtil.order_completed);
		dao.updateOrderStatus(orderNo, AlipayUtil.order_completed);
		

		// 修改express 支付成功

		
		System.out.println(this.getClass()+"  before getUncompledExpressInfoByWishOrderId  wishOrderId"+wishOrderId+"  ");
		
		ExpressInfo currentExpress = dao.getUncompledExpressInfoByWishOrderId(wishOrderId,
				EXPRESS.express_status_init);

		currentExpress.setStatusId(EXPRESS.express_status_paied);

		dao.updateExpressInfo(currentExpress);

		// 修改wish order 的状态，改为已支付
		dao.updateWishOrderStatus(order.getWishOrderId(), WishConstants.wishorder_status_paied);

		List<OrderDetail> orderDetailList = orderDetailService.getOrderListyWishOrderId(order.getWishOrderId());
		if(orderDetailList!=null){
		for(OrderDetail orderDetail :orderDetailList){
			Wish wish =wishService.getResource(orderDetail.getWishId());
			if(wish.getSalesVolume()==null){
				wish.setSalesVolume(1);
			}else{
				wish.setSalesVolume(wish.getSalesVolume()+1);
			}
			if(wish.getHoldingCount()!=null
					&&wish.getHoldingCount()>0){
				wish.setHoldingCount(wish.getHoldingCount()-1);
			}
			wishService.updateResource(wish);
		}
		}
		
		
		
		
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
