<%@ page contentType="text/html; charset=gbk" language="java" import="java.util.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>后台通知面页</title>
</head>
<%@ page import="java.io.*,java.lang.*,java.sql.*,java.util.*,chinapnr.*" %>
<body bgcolor="#FFFFFF" text="#000000" STYLE="margin-top=0; margin-left=0; margin-right=0;margin-bottom=0">
<%
	request.setCharacterEncoding("gbk");
    String 	CmdId = request.getParameter("CmdId");				//消息类型
    String 	MerId = request.getParameter("MerId");	 			//商户号
    String 	RespCode = request.getParameter("RespCode"); 		//应答返回码
    String 	TrxId = request.getParameter("TrxId"); 				//钱管家交易唯一标识
    String 	OrdAmt = request.getParameter("OrdAmt");  			//金额
    String 	CurCode = request.getParameter("CurCode"); 			//币种
    String 	Pid = request.getParameter("Pid");					//商品编号
    String 	OrdId = request.getParameter("OrdId"); 				//订单号
    String 	MerPriv = request.getParameter("MerPriv");			//商户私有域
    String 	RetType = request.getParameter("RetType");			//返回类型
    String 	DivDetails = request.getParameter("DivDetails"); 	//分账明细
    String 	GateId = request.getParameter("GateId");  			//银行ID
    String 	ChkValue = request.getParameter("ChkValue"); 		//签名信息 
    
    
    
    
    System.out.println("----------------------------------hui---------------------------");
	
	try
	{
		//验签
		String 	MerKeyFile	= "http://121.42.182.117/funeral/pages/PgPubk.key";
		String	MerData = CmdId + MerId + RespCode + TrxId + OrdAmt + CurCode + Pid + OrdId + MerPriv + RetType + DivDetails + GateId;  	//参数顺序不能错
		SecureLink sl = new SecureLink( ) ;
		int ret = sl.VeriSignMsg(MerKeyFile , MerData, ChkValue) ;
			
		if (ret != 0) 
		{
			out.println("签名验证失败["+MerData+"]");
		}
		else
		{
			if(RespCode.equals("000000"))
			{
				//交易成功
				//根据订单号 进行相应业务操作
				//在些插入代码
				out.println("交易成功");
			}
			else
			{
				//交易失败
				//根据订单号 进行相应业务操作
				//在些插入代码
				out.println("交易失败");
			}
			out.println("RECV_ORD_ID_"+OrdId);
		}
	}
	catch(Exception e)
	{
		out.println("签名验证异常");
	}

%>
</body>
</html>
