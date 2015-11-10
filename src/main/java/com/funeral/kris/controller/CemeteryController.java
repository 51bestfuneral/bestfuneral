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

import com.funeral.kris.model.Cemetery;
import com.funeral.kris.service.CemeteryService;

@Controller
@RequestMapping(value="/cemetery")
public class CemeteryController {
	
	@Autowired
	private CemeteryService cemeteryService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addCemeteryPage() {
		ModelAndView modelAndView = new ModelAndView("add-cemetery-form");
		modelAndView.addObject("cemetery", new Cemetery());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingCemetery(@ModelAttribute Cemetery cemetery) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		cemeteryService.addResource(cemetery);
		
		String message = "Cemetery was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<Cemetery> listOfCemeterys() {
		ModelAndView modelAndView = new ModelAndView("list-of-cemeterys");

		List<Cemetery> cemeterys = cemeteryService.getResources();
		modelAndView.addObject("cemeterys", cemeterys);

		return cemeterys;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editCemeteryPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-cemetery-form");
		Cemetery cemetery = cemeteryService.getResource(id);
		modelAndView.addObject("cemetery",cemetery);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingCemetery(@ModelAttribute Cemetery cemetery, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		cemeteryService.updateResource(cemetery);
		
		String message = "Cemetery was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteCemetery(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		cemeteryService.deleteResource(id);
		String message = "Cemetery was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
