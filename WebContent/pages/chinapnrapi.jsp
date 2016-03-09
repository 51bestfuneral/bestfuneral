<%@ page contentType="text/html; charset=gbk" language="java" import="java.util.*" errorPage="" %>
<%@ page import="com.funeral.kris.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>汇付天下</title>
</head>
<%@ page import="java.io.*,java.lang.*,java.sql.*,java.util.*,chinapnr.*,com.funeral.kris.util.ChinapnrUtil" %>
<body>
<%
	request.setCharacterEncoding("utf-8");
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss"); 
	java.util.Date currentTime = new java.util.Date();//碌玫陆碌卤前系统时录鉃�
	String OrdId = request.getParameter("OrdId").trim(); 
    String 	Version			= ChinapnrUtil.version;
    String 	CmdId			= "Buy";
    String 	MerId			= ChinapnrUtil.merId;
    
    System.out.println(" OrdAmt ="+request.getParameter("OrdAmt"));

    
    String 	OrdAmt			= request.getParameter("OrdAmt").trim();//两位数
    
    OrdAmt=new java.math.BigDecimal(OrdAmt).setScale(2, java.math.BigDecimal.ROUND_HALF_UP).toString();
    
    String 	CurCode			= "RMB";
    String 	Pid				= "";//request.getParameter("Pid").trim();
    String 	RetUrl			= "http://www.365niannian.com/funeral/pages/paymentFinal.html";
    String 	BgRetUrl		= "http://www.365niannian.com/funeral/pages/Buy_notify_url.jsp";
    String 	MerPriv			= ChinapnrUtil.merPriv;
    String 	GateId			= ChinapnrUtil.gateId;
    String 	UsrMp			= request.getParameter("UsrMp").trim();
    String 	DivDetails		= "";// request.getParameter("DivDetails").trim();
    String 	PayUsrId		= "";// request.getParameter("PayUsrId").trim();
	String path =application.getRealPath("");
	System.out.println(" -----path----"+path);
	//签幕
	String 	MerKeyFile	= path+"/pages/MerPrK873839.key";
	System.out.println(OrdAmt);
	String	MerData = Version + CmdId + MerId + OrdId + OrdAmt + CurCode + Pid + RetUrl + MerPriv + GateId + UsrMp + DivDetails + PayUsrId + BgRetUrl;	
	
	SecureLink sl=new SecureLink();
	int ret=sl.SignMsg(MerId,MerKeyFile,MerData);

	if (ret != 0) 
	{
		out.println("ret =" + ret );
		return ;
	}

	String	ChkValue = sl.getChkValue( );
	
	System.out.println(" ChkValue ="+ChkValue);
	
	Map<String, String> sParaTemp = new HashMap<String, String>();
	sParaTemp.put("Version","10");
    sParaTemp.put("CmdId", CmdId);
    sParaTemp.put("MerId", MerId);
    sParaTemp.put("OrdId", OrdId);
	sParaTemp.put("OrdAmt", OrdAmt);
	sParaTemp.put("CurCode", CurCode);
	sParaTemp.put("RetUrl", RetUrl);
	sParaTemp.put("BgRetUrl", BgRetUrl);
	sParaTemp.put("MerPriv", MerPriv);
	sParaTemp.put("UsrMp", UsrMp);
	sParaTemp.put("GateId", GateId);
	sParaTemp.put("ChkValue", ChkValue);

System.out.println(" sParaTemp ="+sParaTemp);
	
	//sParaTemp.put("show_url",  AlipayUtil.PAGE_URL + "/paymentFinal.html");
	//sParaTemp.put("anti_phishing_key", anti_phishing_key);
	//sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
	//陆篓b全瘸
	String sHtmlText = ChinapnrService.buildRequest(sParaTemp,"get","确认");
	out.println(sHtmlText);

%>

</body>
</html>