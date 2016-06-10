package com.funeral.kris.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class AlipayUtil {
	
	public static String partner = "2088221356680135";
	
	public static String seller_email = "xiaoxianglu@365niannian.com";
	
	public static String KEY = "j39zc02yhr2ro0xat888xuibfu90sgkf";

	public static String log_path = "D:\\";

	public static String input_charset = "utf-8";
	
	public static String sign_type = "MD5";
	
	public static String customer_url ="";
	
	public static  String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
	
	
	public static  String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
	
	public static  String web_app="http://365niannian.com";
	
	public static  String PAGE_URL =web_app +"/payment.html";
	
	public static  String RETURN_PAGE_URL =web_app +"/paymentFinal.html";

	public static  String Alipay_notify_url =web_app +"/notify_url.jsp";

	
	
	public static int order_open=1;

	public static int order_completed=100;
	
	
	public static Map<String,Map> feeMap=new HashMap<String,Map>();
	
	
	
	
	
	
	public static String generateTradeNo(int userId,int sequence){
		
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
		java.util.Date date=new java.util.Date();  
		String time=sdf.format(date);  
		
		String index="0"+sequence;
		
		String no="F"+time+userId;
		
		while(no.length()+index.length()<20){	
			index="0"+index;
		}
		
		String orderNo=no+index;
		return orderNo;
		
	}

}
