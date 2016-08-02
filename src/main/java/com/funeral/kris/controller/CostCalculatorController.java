package com.funeral.kris.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.constants.QUESTION;
import com.funeral.kris.model.TCostCalculationTrace;
import com.funeral.kris.service.TCostCalculationTraceService;

@Controller
@RequestMapping(value = "/costCalculatorController")
public class CostCalculatorController {
	@Autowired
	private TCostCalculationTraceService costCalculationTraceService;

	@ResponseBody
	@RequestMapping(value = "/saveCalculationTrace", method = RequestMethod.GET, produces = "application/json")
	public void saveCalculationTrace(HttpServletRequest request) {

		String id = request.getParameter("id");

		String ids[] = id.split("_");

		String id0 = ids[0];
		String id1 = ids[1];

		int cateId = Integer.parseInt(id0);
		int classId = Integer.parseInt(id1);

		System.out.println(this.getClass() + "----------------id0=" + id0 + "   id1=" + id1);

		boolean exist = costCalculationTraceService.loadByCateIdClassIdUserId(cateId, classId,
				QUESTION.ADMIN_ID.intValue()) != null;
		TCostCalculationTrace trace = new TCostCalculationTrace();

		trace.setCateId(cateId);
		trace.setClassId(classId);
		trace.setUserId(QUESTION.ADMIN_ID.intValue());

		if (exist) {

			costCalculationTraceService.deleteByCateIdUserId(cateId, QUESTION.ADMIN_ID.intValue());

		} else {

			costCalculationTraceService.deleteByCateIdUserId(cateId, QUESTION.ADMIN_ID.intValue());

			costCalculationTraceService.addResource(trace);

		}

	}

}
