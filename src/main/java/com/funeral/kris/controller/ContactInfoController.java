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

	private ContactInfo getContactorByWishOrderId(Integer wishOrderId) {

		ContactInfo contactInfo = contactInfoService
				.getContacterByWishOrderId(wishOrderId);

		return contactInfo;

	}

	private ContactInfo updateContactInfo(ContactInfo contactInfo,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		int userId = user.getUsrId();
		Integer currentWishOrderId = (Integer) request.getSession()
				.getAttribute("currentWishOrderId");
		ContactInfo oldContactInfo = this
				.getContactorByWishOrderId(currentWishOrderId);
		if (oldContactInfo == null) {
			contactInfo.setStatusId(IN_USE);
			contactInfo.setUserId(userId);
			contactInfo.setGender(3);
			contactInfo.setWishOrderId(currentWishOrderId);
			contactInfoService.addResource(contactInfo);

		} else if (oldContactInfo != null
				&& contactInfo.getContactId() != oldContactInfo.getContactId()) {
			oldContactInfo.setUserId(userId);

			oldContactInfo.setStatusId(IN_RELEASED);
			contactInfoService.updateResource(oldContactInfo);
			contactInfo.setWishOrderId(currentWishOrderId);
			contactInfo.setStatusId(IN_USE);
			contactInfo.setUserId(userId);
			contactInfoService.addResource(contactInfo);
		} else {

			contactInfo.setWishOrderId(currentWishOrderId);
			contactInfo.setStatusId(IN_USE);
			contactInfo.setUserId(userId);
			contactInfoService.updateResource(contactInfo);

		}

		return contactInfo;

	}

	private ExpressInfo updateExpressInfo(ContactInfo contactInfo) {

		ExpressInfo express = expressInfoService
				.getExpressInfoByWishOrderId(contactInfo.getWishOrderId());

		if (express != null) {
			expressInfoService.deleteResource(express.getExpressId());
		}

		ExpressInfo expressInfo = new ExpressInfo();

		expressInfo.setCity(contactInfo.getCity());
		expressInfo.setProvince(contactInfo.getProvince());
		expressInfo.setStatusId(1);
		expressInfo.setWishOrderId(contactInfo.getWishOrderId());
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
		return expressInfo;

	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody ContactInfo contactInfo,
			HttpServletRequest request) {

		contactInfo = this.updateContactInfo(contactInfo, request);

		System.out.println(this.getClass()
				+ "  --------------------getContactId---"
				+ contactInfo.getContactId() + "  getExpressMethod="
				+ contactInfo.getExpressMethod());

		this.updateExpressInfo(contactInfo);
	}

	@ResponseBody
	@RequestMapping(value = "/listByUserId", method = RequestMethod.GET, produces = "application/json")
	public List<ContactInfo> listByUserId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		int userId = user.getUsrId();

		System.out.println("  ----   userId  = " + userId);

		List<ContactInfo> contactInfoList = contactInfoService.getByUserId(user
				.getUsrId());
		return contactInfoList;
	}

	@ResponseBody
	@RequestMapping(value = "/listByContacterId", method = RequestMethod.GET, produces = "application/json")
	public ContactInfo listByContacterId(HttpServletRequest request) {
		String contactId = (String) request.getParameter("contactId");

		System.out.println("  ----   contactId  = " + contactId);

		ContactInfo contactInfo = contactInfoService.getResource(Integer
				.parseInt(contactId));
		return contactInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/getTempContacter", method = RequestMethod.GET, produces = "application/json")
	public ContactInfo getTempContacter(HttpServletRequest request) {
		String contactId = (String) request.getParameter("contactId");

		System.out.println("  ----   contactId  = " + contactId);

		ContactInfo contactInfo = contactInfoService.getResource(Integer
				.parseInt(contactId));
		return contactInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/getUsingContacter", method = RequestMethod.GET, produces = "application/json")
	public ContactInfo getUsingContacter(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		int userId = user.getUsrId();

		System.out.println("  ----   userId  = " + userId);

		ContactInfo ContactInfo = contactInfoService.getUsingContacter(userId);
		return ContactInfo;

	}

	@ResponseBody
	@RequestMapping(value = "/getUsingContacterByWishOrderId", method = RequestMethod.GET, produces = "application/json")
	public ContactInfo getUsingContacterByWishOrderId(HttpServletRequest request) {

		Integer currentWishOrderId = (Integer) request.getSession()
				.getAttribute("currentWishOrderId");

		System.out
				.println("  --getUsingContacterByWishOrderId--   currentWishOrderId  = "
						+ currentWishOrderId);

		ContactInfo ContactInfo = contactInfoService
				.getUsingContacterByWishOrderId(currentWishOrderId);

		return ContactInfo;

	}

}
