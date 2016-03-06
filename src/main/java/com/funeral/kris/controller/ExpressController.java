package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.busModel.ExpressBean;
import com.funeral.kris.model.ContactInfo;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.User;
import com.funeral.kris.service.ContactInfoService;
import com.funeral.kris.service.ExpressInfoService;
import com.funeral.kris.service.ExpressService;
import com.funeral.kris.service.MailService;

@Controller
@RequestMapping(value = "/expressController")
public class ExpressController {

	public static String express_method_saved_in_niannian_title = "暂存在念念 ";
	public static String express_method_saved_in_niannian_description = "(我们会暂时替您保管，在需要的时候提供给怿)";
	
	
	
	public static String express_method_standard_title = "标准 ";
	public static String express_method_standard_description = "(免费, 2-3日送达)";
	
	
	
	public static String express_method_express_title = "快递 ";
	public static String express_method_express_description  = "(1日送达，20块钱快递费用)";
	
	
	public static String province_SH="上海";
	public static String province_JS="江苏省";
	public static String province_ZJ="浙江省";

	@Autowired
	private ExpressInfoService expressInfoService;

	@Autowired
	private ContactInfoService contactInfoService;
	
	@Autowired
	private MailService mainService;

	@Autowired
	private ExpressService expressService;

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void add(HttpServletRequest request) {

		String contactId = request.getParameter("contactId");
		String deliveryMethod = request.getParameter("deliveryMethod");
		Integer method = Integer.parseInt(deliveryMethod);
		ContactInfo contactInfo = contactInfoService.getResource(Integer.parseInt(contactId));

		
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		List<ExpressInfo> expressInfoList = expressInfoService.getByUserId(user.getUsrId());

		if (expressInfoList != null && expressInfoList.size() > 0) {

			Iterator iterator = expressInfoList.iterator();
			while (iterator.hasNext()) {

				ExpressInfo express = (ExpressInfo) iterator.next();
				expressInfoService.deleteResource(express.getExpressId());

			}

		}

		ExpressInfo expressInfo = new ExpressInfo();

		expressInfo.setCity(contactInfo.getCity());
		expressInfo.setProvince(contactInfo.getProvince());
		expressInfo.setStatusId(1);

		expressInfo.setDeliveryMethod(Integer.parseInt(deliveryMethod));
		if (method == 3) {
			expressInfo.setExpressFee(new BigDecimal(20));

		} else {
			expressInfo.setExpressFee(BigDecimal.ZERO);

		}
		expressInfo.setDetailAddress(contactInfo.getDetailAddress());
		expressInfo.setPhone(contactInfo.getPhone());
		expressInfo.setReceiverName(contactInfo.getContactName());

		expressInfo.setUserId(contactInfo.getUserId());

System.out.println("-----  contactId="+contactId+"  deliveryMethod="+deliveryMethod+"  getContactName="+contactInfo.getContactName());
		expressInfoService.addResource(expressInfo);
		
		// send email
		Map<String,String> messageInfo = new HashMap<String,String>();
		messageInfo.put("to", "li.yuan@ebaotech.com");
		messageInfo.put("subject", "你有一笔新的订单");
		messageInfo.put("content", "你有一笔新的订单(chelsea will provide the temp)");
		mainService.send(messageInfo);
		
	}

	@ResponseBody
	@RequestMapping(value = "/getByUserId", method = RequestMethod.GET, produces = "application/json")
	public List<ExpressInfo> getByUserId(HttpServletRequest request) {

		String userId = request.getParameter("userId");
		System.out.println(" ---------------- userId = " + userId);

		return expressInfoService.getByUserId(Integer.parseInt(userId));

	}

	@ResponseBody
	@RequestMapping(value = "/getUncompledExpressInfoByUserId", method = RequestMethod.GET, produces = "application/json")
	public List<ExpressInfo> getUncompledExpressInfoByUserId(HttpServletRequest request) {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		String statusId = request.getParameter("statusId");
		System.out.println(" ---------------- statusId=" + statusId);

		return expressInfoService.getUncompledExpressInfoByUserId(user.getUsrId().intValue(),
				Integer.parseInt(statusId));

	}

	@ResponseBody
	@RequestMapping(value = "/getUsingExpressInfoByUserId", method = RequestMethod.GET, produces = "application/json")
	public ExpressInfo getUsingExpressInfoByUserId(HttpServletRequest request) {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		return expressInfoService.getUsingExpressInfo(user.getUsrId());

	}

	@ResponseBody
	@RequestMapping(value = "/getUsingExpressBean", method = RequestMethod.GET, produces = "application/json")
	public ExpressBean getUsingExpressBean(HttpServletRequest request) {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		ExpressInfo info = expressInfoService.getUsingExpressInfo(user.getUsrId());

		ExpressBean bean = new ExpressBean();

		bean.setExpressMethod(info.getDeliveryMethod());

		if (info.getDeliveryMethod().intValue() == 1) {
			bean.setExpressTitle(this.express_method_saved_in_niannian_title);
			bean.setExpressDescription(this.express_method_saved_in_niannian_description);
		} else if (info.getDeliveryMethod().intValue() == 2) {

			bean.setExpressTitle(this.express_method_standard_title);
			bean.setExpressDescription(this.express_method_standard_description);
			
		
		} else {

			bean.setExpressTitle(this.express_method_express_title);
			bean.setExpressDescription(this.express_method_express_description);
		}

		
		if(info.getProvince().equals("1")){
			
			bean.setProvince(this.province_JS);
		}else if(info.getProvince().equals("2")){
			bean.setProvince(this.province_SH);
		}else{
			bean.setProvince(this.province_ZJ);
		}
		
		
		
		return bean;

	}
	
	@ResponseBody
	@RequestMapping(value = "/getProcess", method = RequestMethod.GET)
	public String getExpressProcess(HttpServletRequest request) {
		try{
		String orderNo = request.getParameter("orderNo");
		String expressNo = request.getParameter("expressNo");
		String expressCompany = request.getParameter("expressCompany");
		return expressService.searchExpress(orderNo,expressNo, expressCompany);
		} catch(Exception e){
			
		}
		return null;
		
		
		
	}

}
