package com.funeral.kris.service;


import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SmsSenderServiceImpl implements SmsSenderService{
	
	public static final String hostUrl = "http://gw.api.taobao.com/router/rest";
	public static final String appkey="23337041";
	public static final String secret ="e876ddc7996081df3d4501fa876e84f7";
	public static final String signName ="变更验证";
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int sendSms(Map<String,String> messageInfo) {
		return 1;
	}
	
	public int sendForPassword(Map<String,String> messageInfo) {
		messageInfo.put("tempCode", "SMS_7155135");
		return sendSms(messageInfo);
	}
	
	public int sendForPaid(Map<String,String> messageInfo) {
		messageInfo.put("tempCode", "SMS_7310281");
		return sendSms(messageInfo);
	}

	
}
