package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.bean.CemeteryBean;
import com.funeral.kris.model.Cemetery;
import com.funeral.kris.model.TCemeteryEpigraphStyle;
import com.funeral.kris.model.TCemeteryGraveStyle;
import com.funeral.kris.model.TCemeteryGraveZone;
import com.funeral.kris.model.TCemeteryKeywords;
import com.funeral.kris.service.CemeteryEpigraphStyleService;
import com.funeral.kris.service.CemeteryGraveStyleService;
import com.funeral.kris.service.CemeteryGraveZoneService;
import com.funeral.kris.service.CemeteryKeywordsService;
import com.funeral.kris.service.CemeteryService;

@Controller
@RequestMapping(value = "/cemetery")
public class CemeteryController {

	@Autowired
	private CemeteryService cemeteryService;
	@Autowired
	private CemeteryEpigraphStyleService cemeteryEpigraphStyleService;
	@Autowired
	private CemeteryGraveStyleService cemeteryGraveStyleService;
	@Autowired
	private CemeteryGraveZoneService cemeteryGraveZoneService;
	@Autowired
	private CemeteryKeywordsService cemeteryKeywordsService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addCemeteryPage() {
		ModelAndView modelAndView = new ModelAndView("add-cemetery-form");
		modelAndView.addObject("cemetery", new Cemetery());
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addingCemetery(@RequestBody Cemetery cemetery) {

		System.out.println("  cemetery=" + cemetery);
		System.out.println("  getCemeteryName=" + cemetery.getCemeteryName());

		cemeteryService.addResource(cemetery);

	}

	@ResponseBody
	@RequestMapping(value = "/getCemetery", method = RequestMethod.GET, produces = "application/json")
	public Cemetery getCemetery(HttpServletRequest request) {

		int cemeteryId = Integer.parseInt(request.getParameter("id"));
		Cemetery cemetery = cemeteryService.getResource(cemeteryId);
		return cemetery;
	}

	@ResponseBody
	@RequestMapping(value = "/findOneCemetery", method = RequestMethod.GET, produces = "application/json")
	public CemeteryBean findOneCemetery(HttpServletRequest request) {

		int cemeteryId = Integer.parseInt(request.getParameter("id"));
		Cemetery cemetery = cemeteryService.getResource(cemeteryId);

		List<TCemeteryEpigraphStyle> cemeteryEpigraphStyleList = cemeteryEpigraphStyleService
				.findByCemeteryId(cemeteryId);
		List<TCemeteryGraveStyle> cemeteryGraveStyleList = cemeteryGraveStyleService.getByCemeteryId(cemeteryId);
		List<TCemeteryGraveZone> zoneList = cemeteryGraveZoneService.findByCemeteryId(cemeteryId);

		List<TCemeteryKeywords> keywordsList = cemeteryKeywordsService.findByCemeteryId(cemeteryId);

		CemeteryBean bean = new CemeteryBean();

		bean.setCemeteryId(cemeteryId);

		bean.setCemeteryName(cemetery.getCemeteryName());

		bean.setAddress(cemetery.getAddress());

		bean.setCemeteryDesc(cemetery.getCemeteryDesc());

		bean.setDistrict(cemetery.getDistrict());

		bean.setFeature(cemetery.getFeature());

		bean.setMapUrl("/funeral/js/images/cemeteryMap.png");

		bean.setPrice(cemetery.getPrice());

		bean.setTrafficInfo(cemetery.getTrafficInfo());

		bean.setEpigraphStyleList(cemeteryEpigraphStyleList);

		bean.setGraveStyleList(cemeteryGraveStyleList);

		bean.setGraveZoneList(zoneList);

		bean.setDescImgUrl(cemetery.getDescImgUrl());
		bean.setFeatureImgUrl(cemetery.getFeatureImgUrl());
		bean.setLocationImgUrl(cemetery.getLocationImgUrl());

		bean.setKeywordsList(keywordsList);

		return bean;
	}

	@ResponseBody
	@RequestMapping(value = "/listCemeteryKeywords", method = RequestMethod.GET, produces = "application/json")
	public List<TCemeteryKeywords> listCemeteryKeywords(HttpServletRequest request) {

		int cemeteryId = Integer.parseInt(request.getParameter("id"));

		List<TCemeteryKeywords> keywords = cemeteryKeywordsService.findByCemeteryId(cemeteryId);

		return keywords;
	}

	@ResponseBody
	@RequestMapping(value = "/getOneCemeteryKeyword", method = RequestMethod.GET, produces = "application/json")
	public TCemeteryKeywords getOneCemeteryKeyword(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));

		TCemeteryKeywords keywords = cemeteryKeywordsService.getResource(id);

		return keywords;
	}

	@ResponseBody
	@RequestMapping(value = "/addCemeteryKeyword", method = RequestMethod.POST)
	public void addCemeteryKeyword(@RequestBody TCemeteryKeywords cemeteryKeywords) {

		cemeteryKeywordsService.addResource(cemeteryKeywords);
	}

	@ResponseBody
	@RequestMapping(value = "/removeCemetery", method = RequestMethod.GET, produces = "application/json")
	public void removeCemetery(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));
		cemeteryService.deleteResource(id);
	}

	@ResponseBody
	@RequestMapping(value = "/removeCemeteryKeyword", method = RequestMethod.GET, produces = "application/json")
	public void removeCemeteryKeyword(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));
		cemeteryKeywordsService.deleteResource(id);
	}

	@ResponseBody
	@RequestMapping(value = "/addEpigraph", method = RequestMethod.POST)
	public void addEpigraph(@RequestBody TCemeteryEpigraphStyle cemeteryEpigraphStyle) {

		cemeteryEpigraphStyleService.addResource(cemeteryEpigraphStyle);
	}

	@ResponseBody
	@RequestMapping(value = "/removeEpigraph", method = RequestMethod.GET, produces = "application/json")
	public void removeEpigraph(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));

		cemeteryEpigraphStyleService.deleteResource(id);
	}

	@ResponseBody
	@RequestMapping(value = "/addGrave", method = RequestMethod.POST)
	public void addGrave(@RequestBody TCemeteryGraveStyle cemeteryGraveStyle) {

		cemeteryGraveStyleService.addResource(cemeteryGraveStyle);
	}

	@ResponseBody
	@RequestMapping(value = "/addZone", method = RequestMethod.POST)
	public void addZone(@RequestBody TCemeteryGraveZone cemeteryGraveZone) {

		cemeteryGraveZoneService.addResource(cemeteryGraveZone);
	}

	@ResponseBody
	@RequestMapping(value = "/removeZone", method = RequestMethod.GET, produces = "application/json")
	public void removeZone(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		cemeteryGraveZoneService.deleteResource(id);
	}

	@ResponseBody
	@RequestMapping(value = "/removeGrave", method = RequestMethod.GET, produces = "application/json")
	public void removeGrave(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		cemeteryGraveStyleService.deleteResource(id);
	}

	@ResponseBody
	@RequestMapping(value = "/listZones", method = RequestMethod.GET, produces = "application/json")
	public List<TCemeteryGraveZone> listZones(HttpServletRequest request) {

		int cemeteryId = Integer.parseInt(request.getParameter("id"));

		List<TCemeteryGraveZone> zones = cemeteryGraveZoneService.findByCemeteryId(cemeteryId);

		return zones;
	}

	@ResponseBody
	@RequestMapping(value = "/listEpigraphs", method = RequestMethod.GET, produces = "application/json")
	public List<TCemeteryEpigraphStyle> listEpigraphs(HttpServletRequest request) {

		int cemeteryId = Integer.parseInt(request.getParameter("id"));

		List<TCemeteryEpigraphStyle> epigraphs = cemeteryEpigraphStyleService.findByCemeteryId(cemeteryId);

		return epigraphs;
	}

	@ResponseBody
	@RequestMapping(value = "/listGraveStyle", method = RequestMethod.GET, produces = "application/json")
	public List<TCemeteryGraveStyle> listGraveStyle(HttpServletRequest request) {

		int cemeteryId = Integer.parseInt(request.getParameter("id"));

		List<TCemeteryGraveStyle> Style = cemeteryGraveStyleService.getByCemeteryId(cemeteryId);

		return Style;
	}

	@ResponseBody
	@RequestMapping(value = "/listAllCemeteries", method = RequestMethod.GET, produces = "application/json")
	public List<CemeteryBean> listAllCemeteries() {

		List<CemeteryBean> list = new ArrayList<CemeteryBean>();

		List<Cemetery> cemeteryList = cemeteryService.getResources();

		List<String> cssList = cemeteryService.getAllCss();

		Iterator iterator = cemeteryList.iterator();

		int index = 0;

		while (iterator.hasNext()) {
			
			

			Cemetery cemetery = (Cemetery) iterator.next();

			if(cemetery.getType().intValue()==1){
			
			CemeteryBean bean = new CemeteryBean();

			bean.setCemeteryId(cemetery.getCemeteryId());

			bean.setCemeteryName(cemetery.getCemeteryName());

			bean.setAddress(cemetery.getAddress());

			bean.setCemeteryDesc(cemetery.getCemeteryDesc());

			bean.setDistrict(cemetery.getDistrict());

			bean.setFeature(cemetery.getFeature());

			bean.setMapUrl(cemetery.getMapUrl());

			bean.setPrice(cemetery.getPrice());

			bean.setTrafficInfo(cemetery.getTrafficInfo());

			String css = cssList.get(index++);
			bean.setDescImgUrl(cemetery.getDescImgUrl());
			bean.setCss(css);
			String style = "background-image:url(" + cemetery.getDescImgUrl() + "); background-repeat:no-repeat;";
			bean.setStyle(style);
			list.add(bean);
			}

		}

		return list;
	}
	@ResponseBody
	@RequestMapping(value = "/listAllTemple", method = RequestMethod.GET, produces = "application/json")
	public List<CemeteryBean> listAllTemple() {
		
		List<CemeteryBean> list = new ArrayList<CemeteryBean>();
		
		List<Cemetery> cemeteryList = cemeteryService.getResources();
		
		List<String> cssList = cemeteryService.getAllCss();
		
		Iterator iterator = cemeteryList.iterator();
		
		int index = 0;
		
		while (iterator.hasNext()) {
			
			Cemetery cemetery = (Cemetery) iterator.next();
			
			if(cemetery.getType().intValue()==2){
				
			CemeteryBean bean = new CemeteryBean();
			
			bean.setCemeteryId(cemetery.getCemeteryId());
			
			bean.setCemeteryName(cemetery.getCemeteryName());
			
			bean.setAddress(cemetery.getAddress());
			
			bean.setCemeteryDesc(cemetery.getCemeteryDesc());
			
			bean.setDistrict(cemetery.getDistrict());
			
			bean.setFeature(cemetery.getFeature());
			
			bean.setMapUrl(cemetery.getMapUrl());
			
			bean.setPrice(cemetery.getPrice());
			
			bean.setTrafficInfo(cemetery.getTrafficInfo());
			
			String css = cssList.get(index++);
			bean.setDescImgUrl(cemetery.getDescImgUrl());
			bean.setCss(css);
			String style = "background-image:url(" + cemetery.getDescImgUrl() + "); background-repeat:no-repeat;";
			bean.setStyle(style);
			list.add(bean);
			}
			
		}
		
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Cemetery> listOfCemeterys() {
		ModelAndView modelAndView = new ModelAndView("list-of-cemeterys");

		List<Cemetery> cemeterys = cemeteryService.getResources();
		modelAndView.addObject("cemeterys", cemeterys);

		return cemeterys;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editCemeteryPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-cemetery-form");
		Cemetery cemetery = cemeteryService.getResource(id);
		modelAndView.addObject("cemetery", cemetery);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView edditingCemetery(@ModelAttribute Cemetery cemetery, @PathVariable Integer id) {

		ModelAndView modelAndView = new ModelAndView("home");

		cemeteryService.updateResource(cemetery);

		String message = "Cemetery was successfully edited.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCemetery(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		cemeteryService.deleteResource(id);
		String message = "Cemetery was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
