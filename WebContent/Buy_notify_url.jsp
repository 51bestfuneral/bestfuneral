<%@ page contentType="text/html; charset=gbk" language="java" import="java.util.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>��̨֪ͨ��ҳ</title>
</head>
<%@ page import="java.io.*,java.lang.*,java.sql.*,java.util.*,chinapnr.*" %>
<body bgcolor="#FFFFFF" text="#000000" STYLE="margin-top=0; margin-left=0; margin-right=0;margin-bottom=0">
<%
	request.setCharacterEncoding("gbk");
    String 	CmdId = request.getParameter("CmdId");				//��Ϣ����
    String 	MerId = request.getParameter("MerId");	 			//�̻���
    String 	RespCode = request.getParameter("RespCode"); 		//Ӧ�𷵻���
    String 	TrxId = request.getParameter("TrxId"); 				//Ǯ�ܼҽ���Ψһ��ʶ
    String 	OrdAmt = request.getParameter("OrdAmt");  			//���
    String 	CurCode = request.getParameter("CurCode"); 			//����
    String 	Pid = request.getParameter("Pid");					//��Ʒ���
    String 	OrdId = request.getParameter("OrdId"); 				//������
    String 	MerPriv = request.getParameter("MerPriv");			//�̻�˽����
    String 	RetType = request.getParameter("RetType");			//��������
    String 	DivDetails = request.getParameter("DivDetails"); 	//������ϸ
    String 	GateId = request.getParameter("GateId");  			//����ID
    String 	ChkValue = request.getParameter("ChkValue"); 		//ǩ����Ϣ 
    
    Map<String,String> params = new HashMap<String,String>();
    
    
    
    
    
    if("000000".equals(RespCode)){
    	
    	
    	params.put("trade_no",TrxId);
        params.put("out_trade_no",OrdId);
        params.put("body",OrdId);
        params.put("collection_type","1");
        params.put("price",OrdAmt);
        
        
        
        com.funeral.kris.pay.service.PayCollectionService service=new com.funeral.kris.pay.service.PayCollectionServiceImpl();
		service.completeCollection(params);
    	
    }
    
    
    
    
    System.out.println("----------------------------------hui---------------------------RespCode="+RespCode);
	
	try
	{
		//��ǩ
		String 	MerKeyFile	= "http://www.365niannian.com/funeral/pages/PgPubk.key";
		String	MerData = CmdId + MerId + RespCode + TrxId + OrdAmt + CurCode + Pid + OrdId + MerPriv + RetType + DivDetails + GateId;  	//����˳���ܴ�
		SecureLink sl = new SecureLink( ) ;
		int ret = sl.VeriSignMsg(MerKeyFile , MerData, ChkValue) ;
			
		if (ret != 0) 
		{
			out.println("ǩ����֤ʧ��["+MerData+"]");
		}
		else
		{
			if(RespCode.equals("000000"))
			{
				
				
			    System.out.println("----------------------------------hui--------------------------OrdId="+OrdId+"  OrdAmt="+OrdAmt);
			
				//���׳ɹ�
				//���ݶ����� ������Ӧҵ�����
				//��Щ�������
				out.println("���׳ɹ�");
			}
			else
			{
				//����ʧ��
				//���ݶ����� ������Ӧҵ�����
				//��Щ�������
				out.println("����ʧ��");
			}
			out.println("RECV_ORD_ID_"+OrdId);
		}
	}
	catch(Exception e)
	{
		out.println("ǩ����֤�쳣");
	}

%>
</body>
</html>
