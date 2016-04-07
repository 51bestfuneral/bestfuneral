package com.funeral.kris.service;

import java.util.Map;

public interface SmsSenderService {

	public int sendSms(Map<String,String> messageInfo) ;
	
	public int sendForPassword(Map<String,String> messageInfo);
	
	public int sendForPaid(Map<String,String> messageInfo);
	
	public int sendRemindSms(Map<String,String> messageInfo);
	
}
