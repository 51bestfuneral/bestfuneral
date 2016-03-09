package com.funeral.kris.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.model.Cart;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.Order;
import com.funeral.kris.model.User;
import com.funeral.kris.model.WishOrder;
import com.funeral.kris.service.AlipayService;
import com.funeral.kris.service.CartDetailService;
import com.funeral.kris.service.CartService;
import com.funeral.kris.service.ExpressInfoService;
import com.funeral.kris.service.FeeCollectionService;
import com.funeral.kris.service.MailService;
import com.funeral.kris.service.OrderService;
import com.funeral.kris.service.SmsSenderService;
import com.funeral.kris.service.WishOrderService;
import com.funeral.kris.service.WishlistDetailService;
import com.funeral.kris.service.WishlistService;
import com.funeral.kris.util.AlipayUtil;

@Controller
@RequestMapping(value = "/alipay")
public class AlipayController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Autowired
	private ExpressInfoService expressInfoService;
	@Autowired
	private FeeCollectionService feeCollectionService;
	@Autowired
	private WishOrderService wishOrderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private CartDetailService cartDetailService;
	@Autowired
	private MailService mailService;

	@Autowired
	private SmsSenderService smsSenderService;

	@ResponseBody
	@RequestMapping(value = "/confirmPay", method = RequestMethod.GET)
	public int confirmPay(HttpServletRequest request) {
		return 1;
	}

	@ResponseBody
	@RequestMapping(value = "/createOrder", method = RequestMethod.GET)
	public Order createOrder(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("user");
//		String wishOrderId = request.getParameter("wishOrderId");
		Integer currentWishOrderId= (Integer) request.getSession().getAttribute("currentWishOrderId");

		Integer setWishOrderId=(Integer) session.getAttribute("setWishOrderId");
		
//		String setWishOrderId = request.getParameter("setWishOrderId");

		
		
		
		
		
		

		WishOrder wishOrder = wishOrderService.getResource(currentWishOrderId);
		
		//要考虑是只支付购物车还是同时支付套餐
		
		
		if(wishOrder.getPayMethod().intValue()==WishConstants.wishorder_paymethod_wishListOnly){
			
			wishOrder.setPayWishOrderId(setWishOrderId);
			
			
		}
		
		

		Order order = orderService.getOrderByWishOrderId(currentWishOrderId);

		if (order != null
				&& order.getStatusId().intValue() == AlipayUtil.order_completed) {

throw new Exception(" ---");

		}

		BigDecimal cost = wishOrder.getPrice();

		ExpressInfo expressInfo = expressInfoService.getUsingExpressInfo(user
				.getUsrId());

		System.out.println("---------cost  " + cost);

		cost = cost.add(expressInfo.getExpressFee());

		List<Order> orderList = orderService.listOrderByUserId(user.getUsrId());

		int index = orderList.size() + 1;

		if (order == null) {
			String orderNo = AlipayUtil.generateTradeNo(user.getUsrId(), index);

			order = new Order();
			order.setUserId(user.getUsrId());
			order.setOrderNo(orderNo);
			order.setSubject(orderNo);
			order.setPayableAmount(cost);
			order.setWishOrderId(currentWishOrderId);
			order.setStatusId(AlipayUtil.order_open);
			orderService.addResource(order);
			// send mail
			Map<String, String> messageInfo = new HashMap<String, String>();
			messageInfo.put("to", "429105398@qq.com");
			messageInfo.put("subject", "你有一笔新的订单");
			messageInfo.put("content",
					"你有一笔新的订单(chelsea will provide the temp)");
			mailService.send(messageInfo);
			// send SMS
			Map<String, String> smsInfo = new HashMap<String, String>();
			smsInfo.put("phone", "18762605155");
			smsSenderService.sendRemindSms(smsInfo);

		} else {
			order.setWishOrderId(currentWishOrderId);
			order.setUserId(user.getUsrId());
			order.setPayableAmount(cost);
			order.setStatusId(AlipayUtil.order_open);
			orderService.addResource(order);
		}

		feeCollectionService.initFeeCollection(order.getOrderNo());

		return order;
	}

	@ResponseBody
	@RequestMapping(value = "/getTradeNo", method = RequestMethod.GET)
	public String createOrder() {

		return AlipayUtil.generateTradeNo(14, 1);

	}

	@ResponseBody
	@RequestMapping(value = "/getTotalPay", method = RequestMethod.GET)
	public BigDecimal getTotalPay(HttpServletRequest request) {
		String cartId = request.getParameter("cartId");

		Cart cart = cartService.getResource(Integer.parseInt(cartId));

		ExpressInfo expressInfo = expressInfoService.getUsingExpressInfo(cart
				.getUserId());
		System.out.println(" ------expressInfo=" + expressInfo);

		BigDecimal cartFee = cart.getPrice();
		BigDecimal expressFee;
		if (expressInfo.getExpressFee() == null) {
			expressFee = BigDecimal.ZERO;
		} else {

			expressFee = expressInfo.getExpressFee();
		}

		BigDecimal totalPay = cartFee.add(expressFee);

		return totalPay;

	}

	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public String pay(ServletRequest request) {

		try {

			String payment_type = "1";

			String notifyUrl = AlipayUtil.PAGE_URL + "/notify_url.jsp";

			String returnUrl = AlipayUtil.PAGE_URL + "return_url.jsp";
			String tradeNo = request.getParameter("WIDout_trade_no");
			String subject = request.getParameter("WIDsubject");
			String totalFee = request.getParameter("WIDtotal_fee");
			String body = request.getParameter("WIDbody");
			String showUrl = request.getParameter("WIDshow_url");
			String anti_phishing_key = "";

			String exter_invoke_ip = "";

			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "create_direct_pay_by_user");
			sParaTemp.put("partner", AlipayUtil.partner);
			sParaTemp.put("seller_email", AlipayUtil.seller_email);
			sParaTemp.put("_input_charset", AlipayUtil.input_charset);
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", notifyUrl);
			sParaTemp.put("return_url", returnUrl);
			sParaTemp.put("out_trade_no", tradeNo);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", totalFee);
			sParaTemp.put("body", body);
			sParaTemp.put("show_url", showUrl);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

			String url = AlipayService.buildRequest(sParaTemp, "get", "ȷ��");

			System.out.println("-------------- begin to pay url=" + url);

			return url;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public String notify(ServletRequest request) {

		// ��ȡ֧����POST����������Ϣ
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// ����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
			// valueStr = new String(valueStr.getBytes("UTF-8"), "UTF-8");
			params.put(name, valueStr);
		}

		try {
			// ��ȡ֧������֪ͨ���ز���ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���½����ο�)//
			// �̻�������

			String out_trade_no = new String(request.getParameter(
					"out_trade_no").getBytes("UTF-8"), "UTF-8");

			// ֧�������׺�

			String trade_no = new String(request.getParameter("trade_no")
					.getBytes("UTF-8"), "UTF-8");

			// ����״̬
			String trade_status = new String(request.getParameter(
					"trade_status").getBytes("UTF-8"), "UTF-8");

			if (AlipayService.verify(params)) {// ��֤�ɹ�
				// ////////////////////////////////////////////////////////////////////////////////////////
				// ������������̻���ҵ���߼��������

				// �����������ҵ���߼�����д�������´�������ο�������

				if (trade_status.equals("TRADE_FINISHED")) {
					// �жϸñʶ����Ƿ����̻���վ���Ѿ�������
					// ���û�������?��ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
					// ����������?��ִ���̻���ҵ�����

					// ע�⣺
					// �˿����ڳ�����˿����޺�������¿��˿��֧����ϵͳ���͸ý���״̬֪ͨ
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// �жϸñʶ����Ƿ����̻���վ���Ѿ�������
					// ���û�������?��ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
					// ����������?��ִ���̻���ҵ�����

					// ע�⣺
					// ������ɺ�֧����ϵͳ���͸ý���״̬֪ͨ
				}

				System.out.println("success");

			} else {// ��֤ʧ��
				System.out.println("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// ��ȡ֧������֪ͨ���ز���ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���Ͻ����ο�)//

		return "shop/shoping-cart";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(ServletRequest request) {

		// ��ȡ֧����GET����������Ϣ
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		try {
			for (Iterator iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// ����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
				valueStr = new String(valueStr.getBytes("UTF-8"), "UTF-8");
				params.put(name, valueStr);
			}

			// ��ȡ֧������֪ͨ���ز���ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���½����ο�)//
			// �̻�������

			String out_trade_no = new String(request.getParameter(
					"out_trade_no").getBytes("UTF-8"), "UTF-8");

			// ֧�������׺�

			String trade_no = new String(request.getParameter("trade_no")
					.getBytes("UTF-8"), "UTF-8");

			// ����״̬
			String trade_status = new String(request.getParameter(
					"trade_status").getBytes("UTF-8"), "UTF-8");

			// ��ȡ֧������֪ͨ���ز���ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���Ͻ����ο�)//

			// ����ó�֪ͨ��֤���
			boolean verify_result = AlipayService.verify(params);

			if (verify_result) {// ��֤�ɹ�
				// ////////////////////////////////////////////////////////////////////////////////////////
				// ������������̻���ҵ���߼��������

				// �����������ҵ���߼�����д�������´�������ο�������
				if (trade_status.equals("TRADE_FINISHED")
						|| trade_status.equals("TRADE_SUCCESS")) {
					// �жϸñʶ����Ƿ����̻���վ���Ѿ�������
					// ���û�������?��ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
					// ����������?��ִ���̻���ҵ�����
				}

				// ��ҳ�����ҳ�������༭
				System.out.println("��֤�ɹ�<br />");
				// �����������ҵ���߼�����д�������ϴ�������ο�������

				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {
				// ��ҳ�����ҳ�������༭
				System.out.println("��֤ʧ��");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return "";
	}

}
