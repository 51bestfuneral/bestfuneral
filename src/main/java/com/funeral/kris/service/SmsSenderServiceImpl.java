package com.funeral.kris.service;


import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Service
@Transactional
public class SmsSenderServiceImpl implements SmsSenderService{
	
	public static final String hostUrl = "http://gw.api.taobao.com/router/rest";
	public static final String appkey="23337041";
	public static final String secret ="e876ddc7996081df3d4501fa876e84f7";
	public static final String signName ="变更验证";
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int sendSms(Map<String,String> messageInfo) {
		
		String json="{";
		//短信模板的内容  
		Iterator it = messageInfo.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			json+="\""+key+"\":\""+value+"\",";
		}
		if(json.length()>1){
			json = json.substring(0, json.length()-1);
		}
		json+="}";
		TaobaoClient client = new DefaultTaobaoClient(hostUrl, appkey, secret);  
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();  
		//req.setExtend("123456");  
		req.setSmsType("normal");  
		req.setSmsFreeSignName(signName);  
		req.setSmsParamString(json);  
		req.setRecNum(messageInfo.get("phone"));  
		req.setSmsTemplateCode(messageInfo.get("tempCode"));  
		try {  
		    AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);  
		    System.out.println(json);  
		    System.out.println(rsp.getBody());  
		    return 1;  
		} catch (Exception e) {  
		    // TODO: handle exception  
		    return -1;  
		} 
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
