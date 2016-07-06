package com.funeral.kris.pay.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.funeral.kris.constants.COLLECTION;
import com.funeral.kris.constants.EXPRESS;
import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.Order;
import com.funeral.kris.model.TFeeCollection;
import com.funeral.kris.model.User;
import com.funeral.kris.model.WishOrder;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.pay.dao.PayDAO;
import com.funeral.kris.pay.dao.PayDAOImpl;
import com.funeral.kris.service.ContactInfoService;
import com.funeral.kris.service.ExpressInfoService;
import com.funeral.kris.service.MailService;
import com.funeral.kris.service.OrderDetailService;
import com.funeral.kris.service.OrderService;
import com.funeral.kris.service.UserService;
import com.funeral.kris.service.WishOrderService;
import com.funeral.kris.service.WishService;
import com.funeral.kris.service.WishlistDetailService;
import com.funeral.kris.service.WishlistService;
import com.funeral.kris.util.AlipayUtil;

public class PayCollectionServiceImpl implements PayCollectionService {

	private static PayDAO dao = new PayDAOImpl();

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private WishService wishService;

	@Autowired
	private MailService mailService;

	private PayDAO payDao = new PayDAOImpl();
	@Autowired
	private OrderService orderService;
	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private ExpressInfoService expressInfoService;
	@Autowired
	private ContactInfoService contactInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Autowired
	private WishOrderService wishOrderService;

	@Deprecated
	@Override
	public void initFeeCollection(String orderNo) {

		Order order = dao.getOrderByOrderNo(orderNo);

		User user = userService.getResource(order.getUserId());

		TFeeCollection feeCollection = this
				.loadPayableFeeCollectionByOrderNo(orderNo);

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
			feeCollection
					.setTradeStatus(COLLECTION.collection_trade_status_init);

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
			feeCollection
					.setTradeStatus(COLLECTION.collection_trade_status_init);
			dao.saveFeeCollection(feeCollection);
		}

	}

	@Override
	public int completeCollection(Map<String, String> params) throws Exception {

		String orderNo = params.get("out_trade_no");

		// 修改Order状态
		// Order order = orderService.getResourceByOrderNo(orderNo);

		Order order = payDao.getOrderByOrderNo(orderNo);

		System.out
				.println(this.getClass()
						+ "  -------------------completeCollection  orderNo="
						+ orderNo);

		// order.setStatusId(AlipayUtil.order_completed);

		// orderService.updateResource(order);
		payDao.updateOrderStatus(orderNo, AlipayUtil.order_completed);

		System.out.println(this.getClass()
				+ "  -------------------completeCollection  params=" + params);

		TFeeCollection feeCollection = this
				.loadPayableFeeCollectionByOrderNo(orderNo);

		if (feeCollection == null) {

			System.out.println(this.getClass()
					+ "-------------- no payable order!  orderNo=" + orderNo);

			return -1;
		}

		feeCollection.setNotifyTime(params.get("notify_time"));
		feeCollection.setAmount(new BigDecimal(params.get("price")));
		feeCollection.setNotifyType(params.get("notify_type"));
		feeCollection.setOutTradeNo(params.get("trade_no"));
		feeCollection.setSignType("MD5");
		// feeCollection.setPayerId(Integer.parseInt(params.get("buyer_id")));
		feeCollection.setSellerId(params.get("seller_id"));
		feeCollection.setNotifyId(params.get("notify_id"));
		feeCollection.setTradeNo(params.get("out_trade_no"));
		feeCollection.setDescription(params.get("body"));
		feeCollection.setGmtCreate(params.get("gmt_create"));
		feeCollection.setGmtPayment(params.get("gmt_payment"));
		feeCollection.setSign(params.get("sign"));
		feeCollection
				.setTradeStatus(COLLECTION.collection_trade_status_completed);
		feeCollection.setStatusId(COLLECTION.collection_pay_success);
		feeCollection
				.setDescription(COLLECTION.collection_trade_status_completed);
		feeCollection.setQuantity(params.get("quantity"));
		feeCollection.setSubject(params.get("out_trade_no"));
		// feeCollection.setDiscount(new BigDecimal(params.get("buyer_email")));
		feeCollection.setUseCoupon(params.get("use_coupon"));
		feeCollection.setIsTotalFeeAdjust(params.get("is_total_fee_adjust"));
		feeCollection.setOrderNo(params.get("out_trade_no"));
		feeCollection.setOrderId(order.getOrderId());
		payDao.saveFeeCollection(feeCollection);
		// 修改wishlist

		int userId = order.getUserId();
		System.out.println(this.getClass()
				+ "  -------------------completeCollection  userId=" + userId);

		// Wishlist wishlist = wishlistService.getResourceByUserId(userId);

		Wishlist wishlist = payDao.getwishListByUserId(userId);

		wishlist.setPrice(wishlist.getPrice().subtract(
				feeCollection.getAmount()));

		// wishlist.setOriginalPirce(BigDecimal.ZERO);

		// wishlistService.updateResource(wishlist);

		payDao.updateWishlist(wishlist);

		// 修改wishlist detail
		//
		// List<WishlistDetail> wishlistDetailList = wishlistDetailService
		// .getSelectedWishlistDetailByWishListId(wishlist.getWishlistId());

		List<WishlistDetail> wishlistDetailList = payDao
				.getSelectedWishlistDetailByWishListId(wishlist.getWishlistId());

		Iterator wishlistDetailListIterator = wishlistDetailList.iterator();

		while (wishlistDetailListIterator.hasNext()) {

			WishlistDetail detail = (WishlistDetail) wishlistDetailListIterator
					.next();

			// wishlistDetailService.deleteResource(detail.getWishlistDetailId());
			payDao.deleteWishlistDetail(detail.getWishlistDetailId());
		}

		// 修改wishOrder 信息, 拿到 pay wish order id =当前wish order
		// id的记录，然后更新他们的字表，t_order_detail

		Integer wishOrderId = order.getWishOrderId();
		System.out.println(this.getClass()
				+ "  -------------------completeCollection  wishOrderId="
				+ wishOrderId);

		List<WishOrder> wishOrderList = payDao
				.getOpenWishOrderListByPayWishOrderId(wishOrderId);

		// List<WishOrder> wishOrderList = wishOrderService
		// .getOpenWishOrderListByPayWishOrderId(wishOrderId);

		if (wishOrderList != null) {

			Iterator iterator = wishOrderList.iterator();

			while (iterator.hasNext()) {

				WishOrder wishOrder = (WishOrder) iterator.next();

				if (wishOrder.getSourceId().intValue() == WishConstants.order_source_set
						&& wishOrderId != wishOrder.getWishOrderId().intValue()) {
					wishOrder
							.setStatusId(WishConstants.wishorder_status_closed);

				} else {
					wishOrder.setStatusId(WishConstants.wishorder_status_paied);
				}
				payDao.updateWishOrderStatus(wishOrder.getWishOrderId(),
						wishOrder.getStatusId());

			}

		}

		// 把已经支付的套餐对应的那个订单，状态修改为已关闭

		// 修改express 支付成功

		// List<ExpressInfo> expressInfoList = expressInfoService
		// .getUncompledExpressInfoByUserId(userId,
		// EXPRESS.express_status_init);

		List<ExpressInfo> expressInfoList = payDao
				.getUncompledExpressInfoByUserId(userId,
						EXPRESS.express_status_init);

		ExpressInfo currentExpress = expressInfoList.get(0);

		currentExpress.setStatusId(EXPRESS.express_status_paied);

		// expressInfoService.updateResource(currentExpress);

		payDao.updateExpressInfo(currentExpress);

		// 修改联系人

		// ContactInfo contactInfo =
		// contactInfoService.getUsingContacter(userId);

		payDao.releaseUsingContacter(userId);

		// contactInfo.setStatusId(ContactInfoController.IN_RELEASED);
		//
		// contactInfoService.updateResource(contactInfo);

		System.out
				.println(this.getClass()
						+ "  -------------------completeCollection  orderNo pay successfully! orderNo="
						+ orderNo);

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
