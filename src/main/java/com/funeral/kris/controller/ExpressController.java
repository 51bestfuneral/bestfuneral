package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.dao.ExpressInfoDAO;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.service.ExpressInfoService;

@Controller
@RequestMapping(value = "/expressController")
public class ExpressController {

	@Autowired
	private ExpressInfoService expressInfoService;
	@Autowired
	private ExpressInfoDAO expressInfoDAO;
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody ExpressInfo expressInfo) {

		List<ExpressInfo> expressList = expressInfoService.getUncompledExpressInfoByUserId(expressInfo.getUserId(), 1);
		System.out.println(
				" ---------------- getUserId = " + expressInfo.getUserId() + " expressInfo.getDeliveryMethod()="
						+ expressInfo.getDeliveryMethod() + " expressList.size()=" + expressList.size());

		if (expressList != null && expressList.size() > 0) {
			ExpressInfo express = expressList.get(0);
			System.out.println(
					" ---------------- express.getExpressId() = "+express.getExpressId() );
			expressInfoDAO.delete(express.getExpressId().longValue());
		}

		if (expressInfo.getDeliveryMethod().intValue() == 3) {
			expressInfo.setExpressFee(new BigDecimal("20"));
		} else {
			expressInfo.setExpressFee(BigDecimal.ZERO);

		}
		expressInfo.setStatusId(1);
		expressInfoService.addResource(expressInfo);
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

		String userId = request.getParameter("userId");
		String statusId = request.getParameter("statusId");
		System.out.println(" ---------------- userId = " + userId + " statusId=" + statusId);

		return expressInfoService.getUncompledExpressInfoByUserId(Integer.parseInt(userId), Integer.parseInt(statusId));

	}

}
