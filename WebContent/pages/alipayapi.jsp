
<%
/* *
 *功能：即时到账交易接口接入页
 *版本：3.3
 *日期：2012-08-14
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、商户服务中心（https://b.alipay.com/support/helperApply.htm?action=consultationApply），提交申请集成协助，我们会有专业的技术工程师主动联系您协助解决
 *2、商户帮助中心（http://help.alipay.com/support/232511-16307/0-16307.htm?sh=Y&info_type=9）
 *3、支付宝论坛（http://club.alipay.com/read-htm-tid-8681712.html）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
 **********************************************
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.funeral.kris.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.funeral.kris.service.*"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付宝即时到账交易接口</title>
</head>
<%
		////////////////////////////////////请求参数//////////////////////////////////////

		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = AlipayUtil.PAGE_URL+"/notify_url.jsp";
		//需http://格式的完整路径，不能加?id=123这类自定义参数
		//页面跳转同步通知页面路径
		String return_url = "http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		//商户订单号
		//商户网站订单系统中唯一订单号，必填
		//订单名称
		String subject = request.getParameter("WIDsubject");
		
	String tradeNo = request.getParameter("WIDout_trade_no");

		
		//必填
		//付款金额
		String total_fee = request.getParameter("WIDtotal_fee");
		//必填
		String body = request.getParameter("WIDbody");
		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数
		String exter_invoke_ip ="";
		if (request.getHeader("x-forwarded-for") == null)
        { exter_invoke_ip= request.getRemoteAddr();
        }else{
        	exter_invoke_ip= request.getHeader("x-forwarded-for");
        			}
		
		//客户端的IP地址
		
		//非局域网的外网IP地址，如：221.0.0.1
		
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service","create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayUtil.partner);
        sParaTemp.put("seller_email", AlipayUtil.seller_email);
        sParaTemp.put("_input_charset", AlipayUtil.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", "http://www.365niannian.com/funeral/pages/notify_url.jsp");
		sParaTemp.put("return_url", "http://www.365niannian.com/funeral/pages/paymentFinal.html");
		sParaTemp.put("out_trade_no", tradeNo);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("key", "ceshi");
		
		System.out.println(" sParaTemp=  "+sParaTemp);

		
		//sParaTemp.put("show_url",  AlipayUtil.PAGE_URL + "/paymentFinal.html");
		//sParaTemp.put("anti_phishing_key", anti_phishing_key);
		//sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		//建立请求
		String sHtmlText = AlipayService.buildRequest(sParaTemp,"get","确认");
		
		
		System.out.println("  tradeNo  ="+tradeNo+"  subject="+subject);
		
	//	com.funeral.kris.service.FeeCollectionServiceImpl  collectionService=new com.funeral.kris.service.FeeCollectionServiceImpl();
		
		//collectionService.initFeeCollection(tradeNo);
		
		out.println(sHtmlText);
		
		
		System.out.println("sHtmlText=  "+sHtmlText);
	%>
<body>
</body>
</html>