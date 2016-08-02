package com.funeral.kris.util;

import com.funeral.kris.init.ENV;


public class ChinapnrUtil {
	
	
	
	public static String version ="10";
	
	public static String merId="873839";
	
	public static String merPriv="";
	
	public static String gateId="";
	
	public static  String web_app=ENV.getValue("web_app");

	
	public static  String PAGE_URL =web_app+"/funeral/pages";
	
	public static  String CHINAPNR_GATEWAY = "http://mas.chinapnr.com/gar/RecvMerchant.do";
	
	public static  String  Chinapnr_Buy_notify_url=web_app+"/Buy_notify_url.jsp";

	public static  String RETURN_PAGE_URL =web_app +"/payment.html";

}
