package com.funeral.kris.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.init.constants.LoginConstants;
import com.funeral.kris.model.Order;
import com.funeral.kris.service.OrderService;
import com.funeral.kris.service.PayService;
import com.funeral.kris.util.AlipayUtil;
import com.funeral.kris.util.PayCommonUtil;
import com.funeral.kris.util.PayConfigUtil;
import com.funeral.kris.util.XMLUtil;

@Controller
@RequestMapping(value = "/pay")
public class PayController {

	@Autowired
	public PayService payService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="weixinNodify", method=RequestMethod.POST)
	public void weixinNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();

		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());

		// 过滤空 设置 TreeMap
		SortedMap<String,String> packageParams = new TreeMap<String, String>();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = m.get(parameter);

			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		// 账号信息
		String key = PayConfigUtil.WEIXIN_API_KEY; // key

		// 判断签名是否正确
		if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			String resXml = "";
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				////////// 执行自己的业务逻辑////////////////
				String mch_id = (String) packageParams.get("mch_id");
				String openid = (String) packageParams.get("openid");
				String is_subscribe = (String) packageParams.get("is_subscribe");
				String out_trade_no = (String) packageParams.get("out_trade_no");

				String total_fee = (String) packageParams.get("total_fee");

				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

			} else {
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			// ------------------------------
			// 处理业务完毕
			// ------------------------------
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} else {

		}

	}

	@ResponseBody
	@RequestMapping(value = "weixinPay", method = RequestMethod.POST)
	public Map<String, String> weixinPay(HttpServletRequest request) throws Exception{
		String orderNo= request.getParameter("orderNo");
		BigDecimal price =new BigDecimal(request.getParameter("price")).multiply(new BigDecimal("100"));
		String desc =request.getParameter("desc");
		String url= payService.weixinPay(orderNo, price, desc);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("codeUrl", url);
		return resultMap;
	}
	
	
	@ResponseBody
	@RequestMapping(value="checkOrderStatus", method=RequestMethod.POST)
	public Integer checkOrderStatus(HttpServletRequest request) throws Exception{
		String orderNo= request.getParameter("orderNo");
		if(orderNo==null||"".equals(orderNo)){
			return 0;
		}
		Order order = orderService.getResourceByOrderNo(orderNo);
		if(order.getStatusId().equals(AlipayUtil.order_completed)){
			return 1;
		}
		return 0;
	}
}
