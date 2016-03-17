package com.funeral.kris.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.funeral.kris.service.AlipayService;
import com.funeral.kris.util.AlipayUtil;
import javax.servlet.ServletRequest;

@Controller
@RequestMapping(value = "/alipay")
public class AlipayController {

	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public String pay(ServletRequest request) {
		
		try{
		
		

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

		
		String url=AlipayService.buildRequest(sParaTemp, "get", "确认");
		
		System.out.println("-------------- begin to pay url="+url);
		
		return url;
		}catch(Exception e){
			
		e.printStackTrace();	
		}
		return null;
	}

	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public String notify(ServletRequest request) {

		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("UTF-8"), "UTF-8");
			params.put(name, valueStr);
		}

		try {
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("UTF-8"), "UTF-8");

			// 支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("UTF-8"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("UTF-8"), "UTF-8");

			if (AlipayService.verify(params)) {// 验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 付款完成后，支付宝系统发送该交易状态通知
				}

				

				System.out.println("success"); 

				
			} else {// 验证失败
				System.out.println("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		return "shop/shoping-cart";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(ServletRequest request) {

		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		try {
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("UTF-8"), "UTF-8");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("UTF-8"), "UTF-8");

			// 支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("UTF-8"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("UTF-8"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

			// 计算得出通知验证结果
			boolean verify_result = AlipayService.verify(params);

			if (verify_result) {// 验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
				}

				// 该页面可做页面美工编辑
				System.out.println("验证成功<br />");
				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

				//////////////////////////////////////////////////////////////////////////////////////////
			} else {
				// 该页面可做页面美工编辑
				System.out.println("验证失败");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return "";
	}

}
