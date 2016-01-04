package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.bean.ServiceDisplayBean;
import com.funeral.kris.model.TServiceCategory;
import com.funeral.kris.model.TServiceCategoryClass;
import com.funeral.kris.service.ServiceCategoryClassService;
import com.funeral.kris.service.ServiceCategoryService;

@Controller
@RequestMapping(value = "/serviceDisplayController")
public class ServiceDisplayController {

	@Autowired
	private ServiceCategoryClassService serviceCategoryClassService;
	@Autowired
	private ServiceCategoryService serviceCategoryService;

	@ResponseBody
	@RequestMapping(value = "/serviceDisplay", method = RequestMethod.GET, produces = "application/json")
	public List<ServiceDisplayBean> serviceDisplay() {

		ModelAndView modelAndView = new ModelAndView("list-of-serviceDisplay");

		List<TServiceCategoryClass> serviceCategoryClassList = serviceCategoryClassService.getResources();
		List<TServiceCategory> serviceCategoryList = serviceCategoryService.getResources();
		List<ServiceDisplayBean> serviceDisplayBeanList = new ArrayList<ServiceDisplayBean>();


		Iterator serviceCategoryIterator = serviceCategoryList.iterator();

		while (serviceCategoryIterator.hasNext()) {

			ServiceDisplayBean serviceDisplayBean = new ServiceDisplayBean();

			TServiceCategory tServiceCategory = (TServiceCategory) serviceCategoryIterator.next();

			serviceDisplayBean.setTitle(tServiceCategory.getTitle());

			Iterator serviceCategoryClassIterator = serviceCategoryClassList.iterator();

			while (serviceCategoryClassIterator.hasNext()) {

				TServiceCategoryClass tServiceCategoryClass = (TServiceCategoryClass) serviceCategoryClassIterator
						.next();

				if (tServiceCategoryClass.getCateId().equals(tServiceCategory.getCateId())) {

					serviceDisplayBean.setCateId(tServiceCategoryClass.getCateId());
				

				if (tServiceCategoryClass.getClassId() == 1) {

					serviceDisplayBean.setDiscription1(tServiceCategoryClass.getClassDis());

					serviceDisplayBean.setDisplayId1(
							tServiceCategoryClass.getCateId() + "_" + tServiceCategoryClass.getClassId());

					serviceDisplayBean.setPrice1(tServiceCategoryClass.getPrice());

				} else if (tServiceCategoryClass.getClassId() == 2) {

					serviceDisplayBean.setDiscription2(tServiceCategoryClass.getClassDis());

					serviceDisplayBean.setDisplayId2(
							tServiceCategoryClass.getCateId() + "_" + tServiceCategoryClass.getClassId());

					serviceDisplayBean.setPrice2(tServiceCategoryClass.getPrice());

				} else if (tServiceCategoryClass.getClassId() == 3) {

					serviceDisplayBean.setDiscription3(tServiceCategoryClass.getClassDis());

					serviceDisplayBean.setDisplayId3(
							tServiceCategoryClass.getCateId() + "_" + tServiceCategoryClass.getClassId());

					serviceDisplayBean.setPrice3(tServiceCategoryClass.getPrice());

				}else if(tServiceCategoryClass.getClassId() == 0){
					


					serviceDisplayBean.setDiscription0(tServiceCategoryClass.getClassDis());

					serviceDisplayBean.setDisplayId0(
							tServiceCategoryClass.getCateId() + "_" + tServiceCategoryClass.getClassId());

					serviceDisplayBean.setPrice0(tServiceCategoryClass.getPrice());

				
					
				}

				}

			}
			serviceDisplayBeanList.add(serviceDisplayBean);
		}

		modelAndView.addObject("serviceDisplayBeanList", serviceDisplayBeanList);
		return serviceDisplayBeanList;
	}

}
