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
	
	public static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
	
	
	public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
	
	public static final String PAGE_URL ="http://121.42.182.117/funeral/pages/paymentFinal.html";
	
	public static int order_open=1;

	public static int order_completed=100;
	
	
	public static Map<String,Map> feeMap=new HashMap<String,Map>();
	
	
	
	
	
	
	public static String generateTradeNo(int userId,int sequence){
		
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");  
		java.util.Date date=new java.util.Date();  
		String time=sdf.format(date);  
		
		String index="0"+sequence;
		
		String no="FN"+time+"F"+userId+"U";
		
		while(no.length()<10){	
			no=no+"0";
		}
		
		while(index.length()<5){	
			index="0"+index;
		}		
		
		System.out.println("  generateTradeNo   no= "+no);
		return no+index;
		
	}

}
