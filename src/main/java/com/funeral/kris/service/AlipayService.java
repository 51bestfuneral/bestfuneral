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
import com.funeral.kris.util.httpClient.HttpProtocolHandler;
import com.funeral.kris.util.httpClient.HttpRequest;
import com.funeral.kris.util.httpClient.HttpResponse;
import com.funeral.kris.util.httpClient.HttpResultType;

public class AlipayService {

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

	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(AlipayUtil.log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getAbstract(String strFilePath, String file_digest_type) throws IOException {
		PartSource file = new FilePartSource(new File(strFilePath));
		if (file_digest_type.equals("MD5")) {
			return DigestUtils.md5Hex(file.createInputStream());
		} else if (file_digest_type.equals("SHA")) {
			return DigestUtils.sha256Hex(file.createInputStream());
		} else {
			return "";
		}
	}

	public static boolean verify(Map<String, String> params) {
		String responseTxt = "false";
		if (params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id);
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		boolean isSign = getSignVeryfy(params, sign);

		if (isSign && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean getSignVeryfy(Map<String, String> Params, String sign) {

		Map<String, String> sParaNew = paraFilter(Params);

		String preSignStr = createLinkString(sParaNew);

		boolean isSign = false;
		if (AlipayUtil.sign_type.equals("MD5")) {
			isSign = AlipayMD5.verify(preSignStr, sign, AlipayUtil.KEY, AlipayUtil.input_charset);
		}
		return isSign;
	}

	private static String verifyResponse(String notify_id) {
		String partner = AlipayUtil.partner;
		String veryfy_url = AlipayUtil.HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;

		return checkUrl(veryfy_url);
	}

	private static String checkUrl(String urlvalue) {
		String inputLine = "";

		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}

		return inputLine;
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

		sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + AlipayUtil.ALIPAY_GATEWAY_NEW
				+ "_input_charset=" + AlipayUtil.input_charset + "\" method=\"" + strMethod + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);

			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}

		sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

		return sbHtml.toString();
	}

	public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName,
			String strParaFileName) {

		Map<String, String> sPara = buildRequestPara(sParaTemp);
		List<String> keys = new ArrayList<String>(sPara.keySet());

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\"  enctype=\"multipart/form-data\" action=\""
				+ AlipayUtil.ALIPAY_GATEWAY_NEW + "_input_charset=" + AlipayUtil.input_charset + "\" method=\""
				+ strMethod + "\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);

			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}

		sbHtml.append("<input type=\"file\" name=\"" + strParaFileName + "\" />");


		sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");

		return sbHtml.toString();
	}


	public static String buildRequest(String strParaFileName, String strFilePath, Map<String, String> sParaTemp)
			throws Exception {

		Map<String, String> sPara = buildRequestPara(sParaTemp);

		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

		HttpRequest request = new HttpRequest(HttpResultType.BYTES);

		request.setCharset(AlipayUtil.input_charset);

		request.setParameters(generatNameValuePair(sPara));
		request.setUrl(AlipayUtil.ALIPAY_GATEWAY_NEW + "_input_charset=" + AlipayUtil.input_charset);

		HttpResponse response = httpProtocolHandler.execute(request, strParaFileName, strFilePath);
		if (response == null) {
			return null;
		}

		String strResult = response.getStringResult();

		return strResult;
	}

	private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
		NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
		}

		return nameValuePair;
	}

	public static String query_timestamp() throws MalformedURLException, DocumentException, IOException {


		String strUrl = AlipayUtil.ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayUtil.partner
				+ "&_input_charset" + AlipayUtil.input_charset;
		StringBuffer result = new StringBuffer();

		SAXReader reader = new SAXReader();
		Document doc = reader.read(new URL(strUrl).openStream());

		List<Node> nodeList = doc.selectNodes("//alipay/*");

		for (Node node : nodeList) {

			if (node.getName().equals("is_success") && node.getText().equals("T")) {

				List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
				for (Node node1 : nodeList1) {
					result.append(node1.getText());
				}
			}
		}

		return result.toString();
	}
}
