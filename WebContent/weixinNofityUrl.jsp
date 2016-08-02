<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.funeral.kris.util.*"%>
<%@ page import="com.funeral.kris.service.*"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpSession"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信接口</title>
</head>
<%
	InputStream inputStream;
	StringBuffer sb = new StringBuffer();
	inputStream = request.getInputStream();
	String s;
	BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	while ((s = in.readLine()) != null) {
		sb.append(s);
	}
	in.close();
	inputStream.close();

	// 解析xml成map
	Map<String, String> m = new HashMap<String, String>();
	m = XMLUtil.doXMLParse(sb.toString());

	// 过滤空 设置 TreeMap
	SortedMap<String, String> packageParams = new TreeMap<String, String>();
	Iterator it = m.keySet().iterator();
	while (it.hasNext()) {
		String parameter = (String) it.next();
		String parameterValue = m.get(parameter);

		String v = "";
		if (null != parameterValue) {
			v = parameterValue.trim();
		}
		packageParams.put(parameter, v);
	}

	// 账号信息
	String key = PayConfigUtil.WEIXIN_API_KEY; // key

	// 判断签名是否正确
	if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
		// ------------------------------
		// 处理业务开始
		// ------------------------------
		String resXml = "";
		if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
			// 这里是支付成功
			////////// 执行自己的业务逻辑////////////////
			String mch_id = (String) packageParams.get("mch_id");
			String openid = (String) packageParams.get("openid");
			String is_subscribe = (String) packageParams.get("is_subscribe");
			String out_trade_no = (String) packageParams.get("out_trade_no");

			String total_fee = (String) packageParams.get("total_fee");
			com.funeral.kris.pay.service.PayCollectionService service = new com.funeral.kris.pay.service.PayCollectionServiceImpl();
			service.completeCollection(packageParams);

			// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
			resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
					+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

		} else {
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
		// ------------------------------
		// 处理业务完毕
		// ------------------------------
		BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
		output.write(resXml.getBytes());
		output.flush();
		output.close();
	} else {

	}

%>




<body>
</body>
</html>