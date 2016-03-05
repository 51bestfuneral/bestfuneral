package com.funeral.kris.controller;

import java.math.BigDecimal;
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
import com.funeral.kris.constants.QUESTION;
import com.funeral.kris.model.TCostCalculationTrace;
import com.funeral.kris.model.TServiceCategory;
import com.funeral.kris.model.TServiceCategoryClass;
import com.funeral.kris.service.ServiceCategoryClassService;
import com.funeral.kris.service.ServiceCategoryService;
import com.funeral.kris.service.TCostCalculationTraceService;

@Controller
@RequestMapping(value = "/serviceDisplayController")
public class ServiceDisplayController {

	@Autowired
	private ServiceCategoryClassService serviceCategoryClassService;
	@Autowired
	private TCostCalculationTraceService costCalculationTraceService;
	@Autowired
	private ServiceCategoryService serviceCategoryService;

	@ResponseBody
	@RequestMapping(value = "/serviceDisplay", method = RequestMethod.GET, produces = "application/json")
	public List<ServiceDisplayBean> serviceDisplay() {

		List<TCostCalculationTrace> costCalculationTraceList = costCalculationTraceService
				.loadByUserId(QUESTION.ADMIN_ID.intValue());
		String unSelectFont = "color:#3d4351;";
		String selectFont = "color:#ff5274;";
		
		String cursorPoint="cursor:pointer;";

		System.out.println(this.getClass() + "############## serviceDisplay...");

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

					if (tServiceCategoryClass.getClassId() == 0) {

						serviceDisplayBean.setDiscription0(tServiceCategoryClass.getClassDis());

						serviceDisplayBean.setDisplayId0(
								tServiceCategoryClass.getCateId() + "_" + tServiceCategoryClass.getClassId());

						serviceDisplayBean.setPrice0(tServiceCategoryClass.getPrice());

						serviceDisplayBean.setImgUrldefault0(tServiceCategoryClass.getimgUrldefault());
						serviceDisplayBean.setImgUrlSelected0(tServiceCategoryClass.getImgUrlSelected());

						String unSelectStyle = "height:179px;width:179px;border-radius:179px;float:left;position:relative;top:10px;left:4%;padding-left:10px;border:0px solid blue";

						unSelectStyle = unSelectStyle + ";background:url" + "(" + QUESTION.rootPath
								+ tServiceCategoryClass.getimgUrldefault() + ");background-repeat:no-repeat;";
						String selectStyle = "height:179px;width:179px;border-radius:179px;float:left;position:relative;top:10px;left:4%;padding-left:10px;border:3px solid #ff5274";

						selectStyle = selectStyle + ";background:url" + "(" + QUESTION.rootPath
								+ tServiceCategoryClass.getImgUrlSelected() + ");background-repeat:no-repeat;";

						if (costCalculationTraceService.selected(Integer.parseInt(tServiceCategoryClass.getCateId()), 0,
								costCalculationTraceList)) {
							serviceDisplayBean.setStyle0(selectStyle+cursorPoint);
							serviceDisplayBean.setFont0(selectFont);

						} else {

							serviceDisplayBean.setStyle0(unSelectStyle+cursorPoint);
							serviceDisplayBean.setFont0(unSelectFont);

						}

					} else if (tServiceCategoryClass.getClassId() == 1) {

						serviceDisplayBean.setDiscription1(tServiceCategoryClass.getClassDis());

						serviceDisplayBean.setDisplayId1(
								tServiceCategoryClass.getCateId() + "_" + tServiceCategoryClass.getClassId());

						serviceDisplayBean.setPrice1(tServiceCategoryClass.getPrice());
						serviceDisplayBean.setImgUrldefault1(tServiceCategoryClass.getimgUrldefault());
						serviceDisplayBean.setImgUrlSelected1(tServiceCategoryClass.getImgUrlSelected());

						String unSelectStyle = "height:179px;width:179px;border-radius:179px;float:left;position:relative;top:10px;left:4%;padding-left:10px;border:0px solid blue";

						unSelectStyle = unSelectStyle + ";background:url" + "(" + QUESTION.rootPath
								+ tServiceCategoryClass.getimgUrldefault() + ");background-repeat:no-repeat;";
						String selectStyle = "height:179px;width:179px;border-radius:179px;float:left;position:relative;top:10px;left:4%;padding-left:10px;border:3px solid #ff5274";

						selectStyle = selectStyle + ";background:url" + "(" + QUESTION.rootPath
								+ tServiceCategoryClass.getImgUrlSelected() + ");background-repeat:no-repeat;";

						if (costCalculationTraceService.selected(Integer.parseInt(tServiceCategoryClass.getCateId()), 1,
								costCalculationTraceList)) {
							serviceDisplayBean.setStyle1(selectStyle+cursorPoint);
							serviceDisplayBean.setFont1(selectFont);

						} else {

							serviceDisplayBean.setStyle1(unSelectStyle+cursorPoint);
							serviceDisplayBean.setFont1(unSelectFont);

						}

					} else if (tServiceCategoryClass.getClassId() == 2) {

						serviceDisplayBean.setDiscription2(tServiceCategoryClass.getClassDis());

						serviceDisplayBean.setDisplayId2(
								tServiceCategoryClass.getCateId() + "_" + tServiceCategoryClass.getClassId());

						serviceDisplayBean.setPrice2(tServiceCategoryClass.getPrice());
						serviceDisplayBean.setImgUrldefault2(tServiceCategoryClass.getimgUrldefault());
						serviceDisplayBean.setImgUrlSelected2(tServiceCategoryClass.getImgUrlSelected());

						String unSelectStyle = "height:179px;width:179px;border-radius:179px;float:left;position:relative;top:10px;left:4%;padding-left:10px;border:0px solid blue";

						unSelectStyle = unSelectStyle + ";background:url" + "(" + QUESTION.rootPath
								+ tServiceCategoryClass.getimgUrldefault() + ");background-repeat:no-repeat;";
						String selectStyle = "height:179px;width:179px;border-radius:179px;float:left;position:relative;top:10px;left:4%;padding-left:10px;border:3px solid #ff5274";

						selectStyle = selectStyle + ";background:url" + "(" + QUESTION.rootPath
								+ tServiceCategoryClass.getImgUrlSelected() + ");background-repeat:no-repeat;";

						if (costCalculationTraceService.selected(Integer.parseInt(tServiceCategoryClass.getCateId()), 2,
								costCalculationTraceList)) {
							serviceDisplayBean.setStyle2(selectStyle+cursorPoint);
							serviceDisplayBean.setFont2(selectFont);

						} else {

							serviceDisplayBean.setStyle2(unSelectStyle+cursorPoint);
							serviceDisplayBean.setFont2(unSelectFont);

						}

					} else if (tServiceCategoryClass.getClassId() == 3) {

						serviceDisplayBean.setDiscription3(tServiceCategoryClass.getClassDis());

						serviceDisplayBean.setDisplayId3(
								tServiceCategoryClass.getCateId() + "_" + tServiceCategoryClass.getClassId());

						serviceDisplayBean.setPrice3(tServiceCategoryClass.getPrice());

						serviceDisplayBean.setImgUrldefault3(tServiceCategoryClass.getimgUrldefault());
						serviceDisplayBean.setImgUrlSelected3(tServiceCategoryClass.getImgUrlSelected());
						String unSelectStyle = "height:179px;width:179px;border-radius:179px;float:left;position:relative;top:10px;left:4%;padding-left:10px;border:0px solid blue";

						unSelectStyle = unSelectStyle + ";background:url" + "(" + QUESTION.rootPath
								+ tServiceCategoryClass.getimgUrldefault() + ");background-repeat:no-repeat;";
						String selectStyle = "height:179px;width:179px;border-radius:179px;float:left;position:relative;top:10px;left:4%;padding-left:10px;border:3px solid #ff5274";

						selectStyle = selectStyle + ";background:url" + "(" + QUESTION.rootPath
								+ tServiceCategoryClass.getImgUrlSelected() + ");background-repeat:no-repeat;";

						if (costCalculationTraceService.selected(Integer.parseInt(tServiceCategoryClass.getCateId()), 3,
								costCalculationTraceList)) {
							serviceDisplayBean.setStyle3(selectStyle+cursorPoint);
							serviceDisplayBean.setFont3(selectFont);

						} else {

							serviceDisplayBean.setStyle3(unSelectStyle+cursorPoint);
							serviceDisplayBean.setFont3(unSelectFont);

						}
					}

				}

			}
			serviceDisplayBeanList.add(serviceDisplayBean);
		}

		modelAndView.addObject("serviceDisplayBeanList", serviceDisplayBeanList);
		return serviceDisplayBeanList;
	}

	@ResponseBody
	@RequestMapping(value = "/calcCost", method = RequestMethod.GET, produces = "application/json")
	public BigDecimal calcCost() {

		List<TCostCalculationTrace> costCalculationTraceList = costCalculationTraceService
				.loadByUserId(QUESTION.ADMIN_ID.intValue());

		BigDecimal totalCost = BigDecimal.ZERO;

		Iterator iterator = costCalculationTraceList.iterator();

		while (iterator.hasNext()) {

			TCostCalculationTrace trace = (TCostCalculationTrace) iterator.next();

			TServiceCategoryClass serviceCategoryClass = serviceCategoryClassService
					.getServiceCategoryClassByCateIdAndClassId(trace.getCateId(), trace.getClassId());

			totalCost = totalCost.add(serviceCategoryClass.getPrice());

		}

		return totalCost;

	}

}
