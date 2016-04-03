package com.funeral.kris.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.funeral.kris.util.AlipayMD5;
import com.funeral.kris.util.AlipayUtil;
import com.funeral.kris.util.ChinapnrUtil;
import com.funeral.kris.util.httpClient.HttpProtocolHandler;
import com.funeral.kris.util.httpClient.HttpRequest;
import com.funeral.kris.util.httpClient.HttpResponse;
import com.funeral.kris.util.httpClient.HttpResultType;

public class ChinapnrService {

	public static Map<String, String> paraFilter(Map<String, String> sArray) {

		Map<String, String> result = new HashMap<String, String>();

		if (sArray == null || sArray.size() <= 0) {
			return result;
		}

		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}

		return result;
	}

	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}



	public static String buildRequestMysign(Map<String, String> sPara) {
		String prestr = createLinkString(sPara); 
		String mysign = "";
		if (AlipayUtil.sign_type.equals("MD5")) {
			mysign = AlipayMD5.sign(prestr, AlipayUtil.KEY, AlipayUtil.input_charset);
		}
		return mysign;
	}

	private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
		
		Map<String, String> sPara = paraFilter(sParaTemp);
		
		String mysign = buildRequestMysign(sPara);

		
		sPara.put("sign", mysign);
		sPara.put("sign_type", AlipayUtil.sign_type);

		return sPara;
	}


	public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {

		Map<String, String> sPara = buildRequestPara(sParaTemp);
		List<String> keys = new ArrayList<String>(sPara.keySet());

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("<form id=\"chinapnrSubmit\" name=\"chinapnrSubmit\" action=\"" + ChinapnrUtil.CHINAPNR_GATEWAY+"\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);

			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}

		sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['chinapnrSubmit'].submit();</script>");

		return sbHtml.toString();
	}
}
