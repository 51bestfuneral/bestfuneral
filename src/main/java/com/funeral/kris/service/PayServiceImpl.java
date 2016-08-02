package com.funeral.kris.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.util.HttpUtil;
import com.funeral.kris.util.PayCommonUtil;
import com.funeral.kris.util.PayConfigUtil;
import com.funeral.kris.util.XMLUtil;

@Service
@Transactional
public class PayServiceImpl implements PayService {

	public String weixinPay(String orderNo,BigDecimal price,String desc) throws Exception {
		// 账号信息
        String appid = PayConfigUtil.WEIXIN_APP_ID;  // appid
        //String appsecret = PayConfigUtil.APP_SECRET; // appsecret
        String mch_id = PayConfigUtil.WEIXIN_MCH_ID; // 商业号
        String key = PayConfigUtil.WEIXIN_API_KEY; // key

        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        
        
        // 获取发起电脑 ip
        String spbill_create_ip = PayConfigUtil.WEIXIN_CREATE_IP;
        // 回调接口 
        String notify_url = PayConfigUtil.WEIXIN_NOTIFY_URL;
        String trade_type = "NATIVE";
        
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", desc);
        packageParams.put("out_trade_no", orderNo);
        packageParams.put("total_fee", price.toString());
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);

        String sign = PayCommonUtil.createSign("UTF-8", packageParams,key);
        packageParams.put("sign", sign);
        
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        System.out.println(requestXML);
 
        String resXml = HttpUtil.postData(PayConfigUtil.WEIXIN_UFDODER_URL, requestXML);

        
        Map map = XMLUtil.doXMLParse(resXml);
        //String return_code = (String) map.get("return_code");
        //String prepay_id = (String) map.get("prepay_id");
        String urlCode = (String) map.get("code_url");
        String url = QRfromGoogle(urlCode);
        
		return url;
	}
	
	
	public String QRfromGoogle(String urlCode) throws Exception {
		int widhtHeight = 300;
		String EC_level = "L";
		int margin = 0;
		//urlCode = UrlEncode(urlCode);
		String QRfromGoogle = "http://qr.liantu.com/api.php?w=" + widhtHeight+"&text=" + urlCode ;

		return QRfromGoogle;
	}

	public String UrlEncode(String src)  throws UnsupportedEncodingException {
		return URLEncoder.encode(src, "UTF-8").replace("+", "%20");
	}
	


}
