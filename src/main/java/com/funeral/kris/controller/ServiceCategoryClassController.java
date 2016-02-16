package com.funeral.kris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.TServiceCategoryClass;
import com.funeral.kris.service.ServiceCategoryClassService;

@Controller
@RequestMapping(value = "/serviceCategoryClass")
public class ServiceCategoryClassController {

	@Autowired
	private ServiceCategoryClassService serviceCategoryClassService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<TServiceCategoryClass> listOfServiceCategoryClass() {
		ModelAndView modelAndView = new ModelAndView("list-of-serviceCategoryClass");

		List<TServiceCategoryClass> serviceCategoryClassList = serviceCategoryClassService.getResources();
		modelAndView.addObject("serviceCategoryClassList", serviceCategoryClassList);

		return serviceCategoryClassList;
	}
	@ResponseBody
	@RequestMapping(value = "/getServiceCategoryClassByCateIdAndClassId", method = RequestMethod.GET, produces = "application/json")
	public TServiceCategoryClass getServiceCategoryClassByCateIdAndClassId(Integer cateId, Integer classId){
		
		
		System.out.println(" cateId ="+cateId+"classId= "+classId);
		
		ModelAndView modelAndView = new ModelAndView("list-of-serviceCategoryClass");
		
		TServiceCategoryClass serviceCategoryClass = serviceCategoryClassService.getServiceCategoryClassByCateIdAndClassId(cateId, classId);
		modelAndView.addObject("serviceCategoryClass", serviceCategoryClass);
		return serviceCategoryClass;
	}

}
