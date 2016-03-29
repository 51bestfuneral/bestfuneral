package com.funeral.kris.util;

import java.util.Random;

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
	
	public static final String PAGE_URL ="/funeral/pages";
	
	public static int completed=100;
	
	public static String generateTradeNo(int userId,int sequence){
		
		Long time=System.currentTimeMillis();
		
		String index="0"+sequence;
		
		String no="NN"+time+"T"+userId+"U";
		
		while(no.length()<21){	
			no=no+"0";
		}
		
		while(index.length()<11){	
			index="0"+index;
		}		
		
		System.out.println("  generateTradeNo   no= "+no);
		return no+index;
		
	}

}
