package com.funeral.kris.controller;

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
import com.funeral.kris.model.User;
import com.funeral.kris.service.ContactInfoService;

@Controller
@RequestMapping(value = "/contactInfo")
public class ContactInfoController {

	@Autowired
	private ContactInfoService contactInfoService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody ContactInfo contactInfo) {

		System.out.println(" ----   add  contactInfo =" + contactInfo);
		contactInfo.setStatusId(1);
		contactInfoService.addResource(contactInfo);
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

	
	
	
	
}
