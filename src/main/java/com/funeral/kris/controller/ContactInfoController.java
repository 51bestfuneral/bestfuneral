package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.model.ContactInfo;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.User;
import com.funeral.kris.service.ContactInfoService;
import com.funeral.kris.service.ExpressInfoService;

@Controller
@RequestMapping(value = "/contactInfo")
public class ContactInfoController {

	public static Integer IN_USE = 3;
	public static Integer IN_RELEASED = 1;

	@Autowired
	private ContactInfoService contactInfoService;
	@Autowired
	private ExpressInfoService expressInfoService;

	@ResponseBody

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody ContactInfo contactInfo) {

		System.out.println(" ----   add  contactInfo =" + contactInfo);

		int userId = contactInfo.getUserId();

		System.out.println("  ----   userId  = " + userId);

		List<ContactInfo> contactInfoList = contactInfoService.getByUserId(userId);

		if (contactInfoList != null) {

			Iterator iterator = contactInfoList.iterator();

			while (iterator.hasNext()) {
				ContactInfo contact = (ContactInfo) iterator.next();

				if (contact.getStatusId().intValue() == this.IN_USE.intValue()) {
					contact.setStatusId(1);
					contactInfoService.updateResource(contact);
				}

			}

		}

		contactInfo.setStatusId(IN_USE);
		contactInfo.setGender(3);
		contactInfoService.addResource(contactInfo);

		List<ExpressInfo> expressInfoList = expressInfoService.getByUserId(contactInfo.getUserId());

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

		expressInfo.setDeliveryMethod(contactInfo.getExpressMethod());
		if (contactInfo.getExpressMethod().intValue() == 3) {
			expressInfo.setExpressFee(new BigDecimal(20));

		} else {
			expressInfo.setExpressFee(BigDecimal.ZERO);

		}
		expressInfo.setDetailAddress(contactInfo.getDetailAddress());
		expressInfo.setPhone(contactInfo.getPhone());
		expressInfo.setReceiverName(contactInfo.getContactName());

		expressInfo.setUserId(contactInfo.getUserId());

		expressInfoService.addResource(expressInfo);
	}

	@ResponseBody
	@RequestMapping(value = "/listByUserId", method = RequestMethod.GET, produces = "application/json")
	public List<ContactInfo> listByUserId(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		int userId = user.getUsrId();

		System.out.println("  ----   userId  = " + userId);

		List<ContactInfo> contactInfoList = contactInfoService.getByUserId(user.getUsrId());
		return contactInfoList;
	}

	@ResponseBody
	@RequestMapping(value = "/listByContacterId", method = RequestMethod.GET, produces = "application/json")
	public ContactInfo listByContacterId(HttpServletRequest request) {
		String contactId = (String) request.getParameter("contactId");

		System.out.println("  ----   contactId  = " + contactId);

		ContactInfo contactInfo = contactInfoService.getResource(Integer.parseInt(contactId));
		return contactInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/getTempContacter", method = RequestMethod.GET, produces = "application/json")
	public ContactInfo getTempContacter(HttpServletRequest request) {
		String contactId = (String) request.getParameter("contactId");

		System.out.println("  ----   contactId  = " + contactId);

		ContactInfo contactInfo = contactInfoService.getResource(Integer.parseInt(contactId));
		return contactInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/getUsingContacter", method = RequestMethod.GET, produces = "application/json")
	public ContactInfo getUsingContacter(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		int userId = user.getUsrId();

		System.out.println("  ----   userId  = " + userId);

		ContactInfo ContactInfo = contactInfoService.getUsingContacter(userId);
		return ContactInfo;

	}

}
