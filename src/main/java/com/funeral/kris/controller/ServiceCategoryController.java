package com.funeral.kris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.TServiceCategory;
import com.funeral.kris.service.ServiceCategoryService;

@Controller
@RequestMapping(value = "/serviceCategory")
public class ServiceCategoryController {

	@Autowired
	private ServiceCategoryService serviceCategoryService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<TServiceCategory> listOfServiceCategory() {
		ModelAndView modelAndView = new ModelAndView("list-of-serviceCategories");

		List<TServiceCategory> serviceCategories = serviceCategoryService.getResources();
		modelAndView.addObject("serviceCategories", serviceCategories);

		return serviceCategories;
	}

}
