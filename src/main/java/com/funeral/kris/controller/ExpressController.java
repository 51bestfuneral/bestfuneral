package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.busModel.ExpressBean;
import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.model.CartDetail;
import com.funeral.kris.model.ContactInfo;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.OrderDetail;
import com.funeral.kris.model.User;
import com.funeral.kris.model.WishOrder;
import com.funeral.kris.service.CartDetailService;
import com.funeral.kris.service.ContactInfoService;
import com.funeral.kris.service.ExpressInfoService;
import com.funeral.kris.service.ExpressService;
import com.funeral.kris.service.MailService;
import com.funeral.kris.service.OrderDetailService;
import com.funeral.kris.service.WishOrderService;

@Controller
@RequestMapping(value = "/expressController")
public class ExpressController {

	public static String express_method_saved_in_niannian_title = "暂存在念念 ";
	public static String express_method_saved_in_niannian_description = "(我们会暂时替您保管，在需要的时候提供给您!)";

	public static String express_method_standard_title = "标准 ";
	public static String express_method_standard_description = "(免邮费, 2-3日送达)";

	public static String express_method_express_title = "快递 ";
	public static String express_method_express_description = "(1日送达，20块钱快递费用)";

	public static String province_SH = "上海";
	public static String province_JS = "江苏省";
	public static String province_ZJ = "浙江省";
	@Autowired
	private WishOrderService wishOrderService;
	@Autowired
	private ExpressInfoService expressInfoService;

	@Autowired
	private ContactInfoService contactInfoService;

	@Autowired
	private MailService mainService;

	@Autowired
	private ExpressService expressService;
	@Autowired
	private CartDetailService cartDetailService;
	@Autowired
	private OrderDetailService orderDetailService;

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void add(HttpServletRequest request) {

		String contactId = request.getParameter("contactId");
		String deliveryMethod = request.getParameter("deliveryMethod");
		Integer method = Integer.parseInt(deliveryMethod);
		ContactInfo contactInfo = contactInfoService.getResource(Integer
				.parseInt(contactId));

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		List<ExpressInfo> expressInfoList = expressInfoService.getByUserId(user
				.getUsrId());

		if (expressInfoList != null && expressInfoList.size() > 0) {

			Iterator iterator = expressInfoList.iterator();
			while (iterator.hasNext()) {

				ExpressInfo express = (ExpressInfo) iterator.next();
				if (expressInfoService.getResource(express.getExpressId()) != null) {
					expressInfoService.deleteResource(express.getExpressId());
				}
			}

		}

		ExpressInfo expressInfo = new ExpressInfo();

		expressInfo.setCity(contactInfo.getCity());
		expressInfo.setProvince(contactInfo.getProvince());
		expressInfo.setStatusId(1);
		expressInfo.setWishOrderId(contactInfo.getWishOrderId());

		expressInfo.setDeliveryMethod(Integer.parseInt(deliveryMethod));
		if (method == 3) {
			expressInfo.setExpressFee(new BigDecimal(20));

		} else {
			expressInfo.setExpressFee(BigDecimal.ZERO);

		}
		expressInfo.setDetailAddress(contactInfo.getDetailAddress());
		expressInfo.setPhone(contactInfo.getPhone());
		expressInfo.setReceiverName(contactInfo.getContactName());

		expressInfo.setUserId(contactInfo.getUserId());

		System.out.println("-----  contactId=" + contactId
				+ "  deliveryMethod=" + deliveryMethod + "  getContactName="
				+ contactInfo.getContactName());
		expressInfoService.addResource(expressInfo);

		Integer currentCartId = (Integer) request.getSession().getAttribute(
				"currentCartId");

		System.out
				.println(this.getClass() + "  currentCartId=" + currentCartId);

		List<CartDetail> cartList = cartDetailService
				.getSelectedCartDetailsByCartId(currentCartId);

		WishOrder wishOrder = wishOrderService.getResource(contactInfo
				.getWishOrderId());

		WishOrder latestOpenWishOrder = wishOrderService
				.getLatestOpenWishOrderForSet(user.getUsrId());

		Integer currentWishOrderId = wishOrder.getWishOrderId();
		
		
	System.out.println(this.getClass()+"  --------add currentWishOrderId="+currentWishOrderId);
	
	if(latestOpenWishOrder!=null){
		
		System.out.println(this.getClass()+"  --------add latestOpenWishOrder.getWishOrderId="+latestOpenWishOrder.getWishOrderId());
		
	}

		if (wishOrder.getPayMethod().intValue() == WishConstants.wishorder_paymethod_shoppingCartOnly
				|| wishOrder.getPayMethod().intValue() == WishConstants.wishorder_paymethod_together) { // 如果支付组合里面包含了支付购物车，则在此处清空购物车
			for (CartDetail cartDetail : cartList) {

				cartDetailService.deleteResource(cartDetail.getCartDetailId());
			}
		}

		if (wishOrder.getPayMethod().intValue() == WishConstants.wishorder_paymethod_together
				|| wishOrder.getPayMethod().intValue() == WishConstants.wishorder_paymethod_wishListOnly) {

			// 先把原来order下面的套餐order detail 删除
			List<OrderDetail> oldsetWishOrderDetaillist = orderDetailService
					.getOrderDetailForWishList(wishOrder.getWishOrderId());

			if (oldsetWishOrderDetaillist != null
					&& currentWishOrderId.intValue() != latestOpenWishOrder
							.getWishOrderId().intValue()) {
				Iterator iterator = oldsetWishOrderDetaillist.iterator();

				while (iterator.hasNext()) {
					OrderDetail detail = (OrderDetail) iterator.next();

					orderDetailService
							.deleteResource(detail.getOrderDetailId());
				}

			}

			// 需要把套餐下的 t_order_detail copy 一份，挂在直选的wish下面

			// 拿到当前最新的套餐，copy一份 order detail，然后挂在当前的wish order下

			if (latestOpenWishOrder != null&&currentWishOrderId.intValue() != latestOpenWishOrder
					.getWishOrderId().intValue()) {

				List<OrderDetail> list = orderDetailService
						.getResourcesByWishOrderId(latestOpenWishOrder
								.getWishOrderId());

				if (list != null) {

					Iterator iter = list.iterator();

					while (iter.hasNext()) {
						OrderDetail orderDetail = (com.funeral.kris.model.OrderDetail) iter
								.next();

						orderDetail.setSourceId(WishConstants.order_source_set);
						orderDetail.setWishOrderId(wishOrder.getWishOrderId());
						orderDetail.setOrderDetailId(null);
						orderDetailService.addResource(orderDetail);
					}

				}
				// 把套餐指向当前的订单
				latestOpenWishOrder.setPayWishOrderId(currentWishOrderId);
				wishOrderService.updateResource(latestOpenWishOrder);
			}

		} else if (wishOrder.getPayMethod().intValue() == WishConstants.wishorder_paymethod_shoppingCartOnly) {
			// 什么也不用做
		}

		wishOrder.setPayWishOrderId(wishOrder.getWishOrderId());

		wishOrder.setStatusId(WishConstants.wishorder_status_pendingPay);
		wishOrderService.updateResource(wishOrder);

	}

	@ResponseBody
	@RequestMapping(value = "/getByUserId", method = RequestMethod.GET, produces = "application/json")
	public List<ExpressInfo> getByUserId(HttpServletRequest request) {

		String userId = request.getParameter("userId");
		System.out.println(" ---------------- userId = " + userId);

		return expressInfoService.getByUserId(Integer.parseInt(userId));

	}

	@ResponseBody
	@RequestMapping(value = "/getUncompledExpressInfoByUserId", method = RequestMethod.GET, produces = "application/json")
	public List<ExpressInfo> getUncompledExpressInfoByUserId(
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		String statusId = request.getParameter("statusId");
		System.out.println(" ---------------- statusId=" + statusId);

		return expressInfoService.getUncompledExpressInfoByUserId(user
				.getUsrId().intValue(), Integer.parseInt(statusId));

	}

	@ResponseBody
	@RequestMapping(value = "/getUsingExpressInfoByUserId", method = RequestMethod.GET, produces = "application/json")
	public ExpressInfo getUsingExpressInfoByUserId(HttpServletRequest request) {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		return expressInfoService.getUsingExpressInfo(user.getUsrId());

	}

	@ResponseBody
	@RequestMapping(value = "/getExpressInfoByWishOrderId", method = RequestMethod.GET, produces = "application/json")
	public ExpressInfo getExpressInfoByWishOrderId(HttpServletRequest request) {
		Integer currentWishOrderId = (Integer) request.getSession()
				.getAttribute("currentWishOrderId");

		// String wishOrderId = request.getParameter("wishOrderId");

		return expressInfoService
				.getExpressInfoByWishOrderId(currentWishOrderId);

	}

	@ResponseBody
	@RequestMapping(value = "/getUsingExpressBean", method = RequestMethod.GET, produces = "application/json")
	public ExpressBean getUsingExpressBean(HttpServletRequest request) {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		ExpressInfo info = expressInfoService.getUsingExpressInfo(user
				.getUsrId());

		ExpressBean bean = new ExpressBean();

		bean.setExpressMethod(info.getDeliveryMethod());

		if (info.getDeliveryMethod().intValue() == 1) {
			bean.setExpressTitle(this.express_method_saved_in_niannian_title);
			bean.setExpressDescription(this.express_method_saved_in_niannian_description);
		} else if (info.getDeliveryMethod().intValue() == 2) {

			bean.setExpressTitle(this.express_method_standard_title);
			bean.setExpressDescription(this.express_method_standard_description);

		} else {

			bean.setExpressTitle(this.express_method_express_title);
			bean.setExpressDescription(this.express_method_express_description);
		}

		if (info.getProvince().equals("1")) {

			bean.setProvince(this.province_JS);
		} else if (info.getProvince().equals("2")) {
			bean.setProvince(this.province_SH);
		} else {
			bean.setProvince(this.province_ZJ);
		}

		return bean;

	}

	@ResponseBody
	@RequestMapping(value = "/getProcess", method = RequestMethod.GET)
	public String getExpressProcess(HttpServletRequest request) {
		try {
			String orderNo = request.getParameter("orderNo");
			String expressNo = request.getParameter("expressNo");
			String expressCompany = request.getParameter("expressCompany");
			return expressService.searchExpress(orderNo, expressNo,
					expressCompany);
		} catch (Exception e) {

		}
		return null;

	}

}
