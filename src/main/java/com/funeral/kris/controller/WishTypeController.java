package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.dao.LevelWishTypeDAO;
import com.funeral.kris.dao.WishTypeDAO;
import com.funeral.kris.model.LevelWishType;
import com.funeral.kris.model.WishType;
import com.funeral.kris.service.WishTypeService;

@Controller
@RequestMapping(value="/wishType")
public class WishTypeController {
	
	@Autowired
	private WishTypeService wishTypeService;

	@Autowired
	private LevelWishTypeDAO levelWishTypeDAO;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addWishTypePage() {
		ModelAndView modelAndView = new ModelAndView("add-wishType-form");
		modelAndView.addObject("wishType", new WishType());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingWishType(@ModelAttribute WishType wishType) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		wishTypeService.addResource(wishType);
		
		String message = "WishType was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<WishType> listOfWishTypes() {
		List<WishType> wishTypes = wishTypeService.getResources();

		return wishTypes;
	}

	@ResponseBody
	@RequestMapping(value="/listLevel",method=RequestMethod.GET, produces = "application/json")
	public Map<String, String> listOfLevelWishTypes() {
		Map<String, String> levelWishTypes = new HashMap<String, String>();
		Map<String, Map<String, String>> wishTypesForLevel = new HashMap<String, Map<String, String>>();
		List<WishType> wishTypes = wishTypeService.getResources();
		List<LevelWishType> levelWishTypeList = new ArrayList<LevelWishType>();
		Iterable<LevelWishType> levelWishTypeIter = levelWishTypeDAO.findAll();
		Iterator<LevelWishType> iter = levelWishTypeIter.iterator();
		while(iter.hasNext()) {
			LevelWishType levelWishType = iter.next();
			levelWishTypeList.add(levelWishType);
		}

		wishTypesForLevel = convertToLevelMap(levelWishTypeList);
		Iterator<Map.Entry<String, Map<String, String>>> it = wishTypesForLevel.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Map<String, String>> entry = it.next();
			Map<String, String> wishTypeOwn = entry.getValue();
			String ownStr = "";
			
			for (WishType wishType: wishTypes) {
				if (!wishTypeOwn.containsKey(wishType.getWishType())) {
					ownStr = ownStr+",0";
				}
				else {
					ownStr = ownStr+",1";
				}
			}
			ownStr = ownStr.trim().replaceFirst(",", "");
			levelWishTypes.put(entry.getKey().toString(), ownStr);
		}
		return levelWishTypes;
	}

	@ResponseBody
	@RequestMapping(value="/listDistinct",method=RequestMethod.GET, produces = "application/json")
	public List<WishType> listDistinctOfWishTypes() {

		List<WishType> wishTypes = wishTypeService.getResources();

		return wishTypes;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editWishTypePage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-wishType-form");
		WishType wishType = wishTypeService.getResource(id);
		modelAndView.addObject("wishType",wishType);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingWishType(@ModelAttribute WishType wishType, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		wishTypeService.updateResource(wishType);
		
		String message = "WishType was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteWishType(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		wishTypeService.deleteResource(id);
		String message = "WishType was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	private Map<String, Map<String, String>> convertToLevelMap(List<LevelWishType> levelWishTypeList) {
		Map<String, Map<String, String>> wishTypesForLevel = new HashMap<String, Map<String, String>>();

		for (LevelWishType levelWishType : levelWishTypeList) {
			String keyName = "level" + levelWishType.getWishTypeLevel().toString();

			if (!wishTypesForLevel.containsKey(keyName)) {
				Map<String, String> wishTypeEachLevel = new HashMap<String, String>();
				wishTypeEachLevel.put(levelWishType.getWishType(), "");
				wishTypesForLevel.put(keyName, wishTypeEachLevel);
			}
			else {
				wishTypesForLevel.get(keyName).put(levelWishType.getWishType(), "");
			}
		}
	    return 	wishTypesForLevel;
	}

}
