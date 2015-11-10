package com.funeral.kris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.WishType;
import com.funeral.kris.service.WishTypeService;

@Controller
@RequestMapping(value="/wishType")
public class WishTypeController {
	
	@Autowired
	private WishTypeService wishTypeService;
	
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
		ModelAndView modelAndView = new ModelAndView("list-of-wishTypes");

		List<WishType> wishTypes = wishTypeService.getResources();
		modelAndView.addObject("wishTypes", wishTypes);

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

}
