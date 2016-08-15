package com.funeral.kris.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.util.PinyinUtil;
import com.google.gson.Gson;
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
import com.funeral.kris.model.TCemeteryPrice;
import com.funeral.kris.service.CemeteryEpigraphStyleService;
import com.funeral.kris.service.CemeteryGraveStyleService;
import com.funeral.kris.service.CemeteryGraveZoneService;
import com.funeral.kris.service.CemeteryKeywordsService;
import com.funeral.kris.service.CemeteryService;
import com.funeral.kris.service.CemeteryPriceService;
@Controller
@RequestMapping({"/cemetery"})
public class CemeteryController
{

  @Autowired
  private CemeteryService cemeteryService;
  @Autowired
	private CemeteryPriceService cemeteryPriceService;
  @Autowired
  private CemeteryEpigraphStyleService cemeteryEpigraphStyleService;

  @Autowired
  private CemeteryGraveStyleService cemeteryGraveStyleService;

  @Autowired
  private CemeteryGraveZoneService cemeteryGraveZoneService;

  @Autowired
  private CemeteryKeywordsService cemeteryKeywordsService;

  @RequestMapping(value={"/{cemeteryId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView getCemeteryPage(@PathVariable int cemeteryId)
  {
    ModelAndView modelAndView = new ModelAndView("cemeteryDetail.jsp");
    Cemetery cemetery = this.cemeteryService.getResource(cemeteryId);
    List cemeteryEpigraphStyleList = this.cemeteryEpigraphStyleService
            .findByCemeteryId(cemeteryId);
    List cemeteryGraveStyleList = this.cemeteryGraveStyleService.getByCemeteryId(cemeteryId);
    List zoneList = this.cemeteryGraveZoneService.findByCemeteryId(cemeteryId);
    List keywordsList = this.cemeteryKeywordsService.findByCemeteryId(cemeteryId);
    CemeteryBean bean = new CemeteryBean();
    bean.setCemeteryId(Integer.valueOf(cemeteryId));
    bean.setCemeteryName(cemetery.getCemeteryName());
    bean.setAddress(cemetery.getAddress());
    bean.setCemeteryDesc(cemetery.getCemeteryDesc());
    bean.setDistrict(cemetery.getDistrict());
    bean.setFeature(cemetery.getFeature());
    bean.setHeadImg(cemetery.getHeadImg());
    bean.setPrice(cemetery.getPrice());
    bean.setTrafficInfo(cemetery.getTrafficInfo().split("-"));
    bean.setEpigraphStyleList(cemeteryEpigraphStyleList);
    bean.setGraveStyleList(cemeteryGraveStyleList);
    bean.setGraveZoneList(zoneList);
    bean.setFirm(cemetery.getFirm());
    bean.setOpenTime(cemetery.getOpenTime());
    bean.setCloseTime(cemetery.getCloseTime());
    bean.setFund(cemetery.getFund());
    bean.setLevel(cemetery.getLevel());
    bean.setSize(cemetery.getSize());
    bean.setStop(cemetery.getStop() == 1?"有":"无");
    bean.setTextStyle(cemetery.getTextStyle());
    bean.setViews(cemetery.getViews());
    bean.setFamous(cemetery.getFamous());
    bean.setSpecialService(cemetery.getSpecialService());
    bean.setFreeService(cemetery.getFreeService().split("-"));
    bean.setNotices(cemetery.getNotices().split("-"));
    bean.setDescImgUrl(cemetery.getDescImgUrl());
    bean.setFeatureImgUrl(cemetery.getFeatureImgUrl());
    bean.setLocationImgUrl(cemetery.getLocationImgUrl());
    bean.setKeywordsList(keywordsList);
    Gson g = new Gson();
    String cjson = g.toJson(bean);
    modelAndView.addObject("cemetery", cjson);
    return modelAndView;
  }

  @RequestMapping(value={"/g/{filterId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView getCemeteryQueryPage(@PathVariable String filterId)
  {
    ModelAndView modelAndView = new ModelAndView("catagoryCemetery.jsp");
    modelAndView.addObject("filterId", filterId);
    return modelAndView;
  }

  @ResponseBody
  @RequestMapping(value={"/add"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public void addingCemetery(@RequestBody Cemetery cemetery) {
    System.out.println("  cemetery=" + cemetery);
    System.out.println("  getCemeteryName=" + cemetery.getCemeteryName());

    this.cemeteryService.addResource(cemetery);
  }
  @ResponseBody
  @RequestMapping(value={"/addTemple"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public void addTemple(@RequestBody Cemetery cemetery) {
    System.out.println("  cemetery=" + cemetery);
    System.out.println("  getCemeteryName=" + cemetery.getCemeteryName());
    cemetery.setType(Integer.valueOf(2));
    this.cemeteryService.addResource(cemetery);
  }
  @ResponseBody
  @RequestMapping(value={"/addCemetery"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public void addCemetery(@RequestBody Cemetery cemetery) {
    System.out.println("  cemetery=" + cemetery);
    System.out.println("  getCemeteryName=" + cemetery.getCemeteryName());
    cemetery.setType(Integer.valueOf(1));
    this.cemeteryService.addResource(cemetery);
  }

  @ResponseBody
  @RequestMapping(value={"/getCemetery"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Cemetery getCemetery(HttpServletRequest request) {
    int cemeteryId = Integer.parseInt(request.getParameter("id"));
    Cemetery cemetery = this.cemeteryService.getResource(cemeteryId);
    return cemetery;
  }
  
  
  @ResponseBody
	@RequestMapping(value = "/getCemeteryPriceBygraveStyleIdAndEpigraphStyleId", method = RequestMethod.GET, produces = "application/json")
	public TCemeteryPrice getCemeteryPriceBygraveStyleIdAndEpigraphStyleId(HttpServletRequest request) {

		int cemeteryId = Integer.parseInt(request.getParameter("cemeteryId"));
		int graveStyleId = Integer.parseInt(request.getParameter("graveStyleId"));
		int epigraphStyleId = Integer.parseInt(request.getParameter("epigraphStyleId"));

		List<TCemeteryPrice> cemeteryPriceList = cemeteryPriceService.getCemeteryPriceListByCemeteryId(cemeteryId);

		if (cemeteryPriceList == null || cemeteryPriceList.size() == 0) {
			return null;
		}

		Iterator iterator = cemeteryPriceList.iterator();

		while (iterator.hasNext()) {

			TCemeteryPrice cemeteryPrice = (TCemeteryPrice) iterator.next();
			if (cemeteryPrice.getEpigraphStyleId().intValue() == epigraphStyleId
					&& cemeteryPrice.getGraveStyleId().intValue() == graveStyleId) {

				return cemeteryPrice;
			}

		}

		return null;
	}

  @ResponseBody
	@RequestMapping(value = "/listCemeteryPrice", method = RequestMethod.GET, produces = "application/json")
	public List<TCemeteryPrice> listCemeteryPrice(HttpServletRequest request) {

		int cemeteryId = Integer.parseInt(request.getParameter("id"));

		List<TCemeteryPrice> prices = cemeteryPriceService.getCemeteryPriceListByCemeteryId(cemeteryId);

		return prices;
	}

  @ResponseBody
  @RequestMapping(value={"/listCemeteryKeywords"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public List<TCemeteryKeywords> listCemeteryKeywords(HttpServletRequest request) {
    int cemeteryId = Integer.parseInt(request.getParameter("id"));

    List keywords = this.cemeteryKeywordsService.findByCemeteryId(cemeteryId);

    return keywords;
  }
  @ResponseBody
  @RequestMapping(value={"/getOneCemeteryKeyword"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public TCemeteryKeywords getOneCemeteryKeyword(HttpServletRequest request) {
    int id = Integer.parseInt(request.getParameter("id"));

    TCemeteryKeywords keywords = this.cemeteryKeywordsService.getResource(id);

    return keywords;
  }
  @ResponseBody
  @RequestMapping(value={"/addCemeteryKeyword"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public void addCemeteryKeyword(@RequestBody TCemeteryKeywords cemeteryKeywords) {
    this.cemeteryKeywordsService.addResource(cemeteryKeywords);
  }
  @ResponseBody
  @RequestMapping(value={"/removeCemetery"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public void removeCemetery(HttpServletRequest request) {
    int id = Integer.parseInt(request.getParameter("id"));
    this.cemeteryService.deleteResource(id);
  }
  @ResponseBody
  @RequestMapping(value={"/removeCemeteryKeyword"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public void removeCemeteryKeyword(HttpServletRequest request) {
    int id = Integer.parseInt(request.getParameter("id"));
    this.cemeteryKeywordsService.deleteResource(id);
  }
  @ResponseBody
  @RequestMapping(value={"/addEpigraph"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public void addEpigraph(@RequestBody TCemeteryEpigraphStyle cemeteryEpigraphStyle) {
    this.cemeteryEpigraphStyleService.addResource(cemeteryEpigraphStyle);
  }
  @ResponseBody
  @RequestMapping(value={"/removeEpigraph"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public void removeEpigraph(HttpServletRequest request) {
    int id = Integer.parseInt(request.getParameter("id"));

    this.cemeteryEpigraphStyleService.deleteResource(id);
  }
  @ResponseBody
  @RequestMapping(value={"/addGrave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public void addGrave(@RequestBody TCemeteryGraveStyle cemeteryGraveStyle) {
    this.cemeteryGraveStyleService.addResource(cemeteryGraveStyle);
  }
  @ResponseBody
  @RequestMapping(value={"/addZone"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public void addZone(@RequestBody TCemeteryGraveZone cemeteryGraveZone) {
    this.cemeteryGraveZoneService.addResource(cemeteryGraveZone);
  }
  @ResponseBody
  @RequestMapping(value={"/removeZone"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public void removeZone(HttpServletRequest request) { int id = Integer.parseInt(request.getParameter("id"));
    this.cemeteryGraveZoneService.deleteResource(id); } 
  @ResponseBody
  @RequestMapping(value={"/removeGrave"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public void removeGrave(HttpServletRequest request) {
    int id = Integer.parseInt(request.getParameter("id"));
    this.cemeteryGraveStyleService.deleteResource(id);
  }
  @ResponseBody
  @RequestMapping(value={"/listZones"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public List<TCemeteryGraveZone> listZones(HttpServletRequest request) {
    int cemeteryId = Integer.parseInt(request.getParameter("id"));

    List zones = this.cemeteryGraveZoneService.findByCemeteryId(cemeteryId);

    return zones;
  }
  @ResponseBody
  @RequestMapping(value={"/listEpigraphs"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public List<TCemeteryEpigraphStyle> listEpigraphs(HttpServletRequest request) {
    int cemeteryId = Integer.parseInt(request.getParameter("id"));

    List epigraphs = this.cemeteryEpigraphStyleService.findByCemeteryId(cemeteryId);

    return epigraphs;
  }
  @ResponseBody
  @RequestMapping(value={"/listGraveStyle"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public List<TCemeteryGraveStyle> listGraveStyle(HttpServletRequest request) {
    int cemeteryId = Integer.parseInt(request.getParameter("id"));

    List Style = this.cemeteryGraveStyleService.getByCemeteryId(cemeteryId);

    return Style;
  }
  @ResponseBody
  @RequestMapping(value={"/listAllCemeteries"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public List<CemeteryBean> listAllCemeteries(HttpServletRequest request) {
    List list = new ArrayList();

    List cemeteryList = this.cemeteryService.getResources(request);

    Iterator iterator = cemeteryList.iterator();

    int index = 0;

    while (iterator.hasNext())
    {
      Cemetery cemetery = (Cemetery)iterator.next();

      if (cemetery.getType().intValue() != 1)
        continue;
      CemeteryBean bean = new CemeteryBean();

      bean.setCemeteryId(cemetery.getCemeteryId());

      bean.setCemeteryName(cemetery.getCemeteryName());

      bean.setAddress(cemetery.getAddress());

      bean.setCemeteryDesc(cemetery.getCemeteryDesc());

      bean.setDistrict(cemetery.getDistrict());

      bean.setFeature(cemetery.getFeature());

      bean.setMapUrl(cemetery.getMapUrl());

      bean.setPrice(cemetery.getPrice());

      bean.setTrafficInfo(cemetery.getTrafficInfo().split("-"));

      bean.setDescImgUrl(cemetery.getDescImgUrl());
      String style = "background-image:url(" + cemetery.getDescImgUrl() + "); background-repeat:no-repeat;";
      bean.setStyle(style);
      list.add(bean);
    }

    return list;
  }
  @ResponseBody
  @RequestMapping(value={"/listAllTemple"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public List<CemeteryBean> listAllTemple(HttpServletRequest request) { List list = new ArrayList();

    List cemeteryList = this.cemeteryService.getResources(request);

    Iterator iterator = cemeteryList.iterator();

    int index = 0;

    while (iterator.hasNext())
    {
      Cemetery cemetery = (Cemetery)iterator.next();

      if (cemetery.getType().intValue() != 2)
        continue;
      CemeteryBean bean = new CemeteryBean();

      bean.setCemeteryId(cemetery.getCemeteryId());

      bean.setCemeteryName(cemetery.getCemeteryName());

      bean.setAddress(cemetery.getAddress());

      bean.setCemeteryDesc(cemetery.getCemeteryDesc());

      bean.setDistrict(cemetery.getDistrict());

      bean.setFeature(cemetery.getFeature());

      bean.setMapUrl(cemetery.getMapUrl());

      bean.setPrice(cemetery.getPrice());

      bean.setTrafficInfo(cemetery.getTrafficInfo().split("-"));
      bean.setDescImgUrl(cemetery.getDescImgUrl());
      String style = "background-image:url(" + cemetery.getDescImgUrl() + "); background-repeat:no-repeat;";
      bean.setStyle(style);
      list.add(bean);
    }

    return list;
  }
  @ResponseBody
  @RequestMapping(value={"/list"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public List<Cemetery> listOfCemeterys(HttpServletRequest request) {
    List<Cemetery> cemeteryList = cemeteryService.getResources(request);
    return cemeteryList;
  }
  @ResponseBody
  @RequestMapping(value={"/listByDistrict"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public Map<String, List<CemeteryBean>> listByDistrict(HttpServletRequest request) {
    Map<String, List<CemeteryBean>> resultMap = new HashMap<String, List<CemeteryBean>>();
    List<Cemetery> cemeteryList = cemeteryService.getResources(request);

    for (Cemetery cemetery:cemeteryList) {
      CemeteryBean cemeteryBean = new CemeteryBean();
      cemeteryBean.setAddress(cemetery.getAddress());
      cemeteryBean.setCemeteryName(cemetery.getCemeteryName());
      cemeteryBean.setDistrict(cemetery.getDistrict());
      cemeteryBean.setPinyin(PinyinUtil.getHanyuPinyin(cemetery.getAddress()+cemetery.getCemeteryName()));
      if (!resultMap.containsKey(cemetery.getDistrict())) {
        List<CemeteryBean> list = new ArrayList<CemeteryBean>();
        list.add(cemeteryBean);
        resultMap.put(cemetery.getDistrict(), list);
      }
      else {
        resultMap.get(cemetery.getDistrict()).add(cemeteryBean);
      }
    }
    return resultMap;
  }
  @RequestMapping(value={"/edit/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView editCemeteryPage(@PathVariable Integer id) {
    ModelAndView modelAndView = new ModelAndView("edit-cemetery-form");
    Cemetery cemetery = this.cemeteryService.getResource(id.intValue());
    modelAndView.addObject("cemetery", cemetery);
    return modelAndView;
  }

  @RequestMapping(value={"/edit/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView edditingCemetery(@ModelAttribute Cemetery cemetery, @PathVariable Integer id) {
    ModelAndView modelAndView = new ModelAndView("home");

    this.cemeteryService.updateResource(cemetery);

    String message = "Cemetery was successfully edited.";
    modelAndView.addObject("message", message);

    return modelAndView;
  }
  @RequestMapping(value={"/delete/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView deleteCemetery(@PathVariable Integer id) {
    ModelAndView modelAndView = new ModelAndView("home");
    this.cemeteryService.deleteResource(id.intValue());
    String message = "Cemetery was successfully deleted.";
    modelAndView.addObject("message", message);
    return modelAndView;
  }
}