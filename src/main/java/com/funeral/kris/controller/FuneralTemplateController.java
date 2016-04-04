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

import com.funeral.kris.model.FuneralTemplate;
import com.funeral.kris.service.FuneralTemplateService;

@Controller
@RequestMapping(value="/funeralTemplate")
public class FuneralTemplateController {
	
	@Autowired
	private FuneralTemplateService funeralTemplateService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addFuneralTemplatePage() {
		ModelAndView modelAndView = new ModelAndView("add-funeralTemplate-form");
		modelAndView.addObject("funeralTemplate", new FuneralTemplate());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingFuneralTemplate(@ModelAttribute FuneralTemplate funeralTemplate) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		funeralTemplateService.addResource(funeralTemplate);
		
		String message = "FuneralTemplate was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<FuneralTemplate> listOfFuneralTemplates() {
		ModelAndView modelAndView = new ModelAndView("list-of-funeralTemplates");

		List<FuneralTemplate> funeralTemplates = funeralTemplateService.getResources();
		modelAndView.addObject("funeralTemplates", funeralTemplates);

		return funeralTemplates;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editFuneralTemplatePage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-funeralTemplate-form");
		FuneralTemplate funeralTemplate = funeralTemplateService.getResource(id);
		modelAndView.addObject("funeralTemplate",funeralTemplate);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingFuneralTemplate(@ModelAttribute FuneralTemplate funeralTemplate, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		funeralTemplateService.updateResource(funeralTemplate);
		
		String message = "FuneralTemplate was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteFuneralTemplate(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		funeralTemplateService.deleteResource(id);
		String message = "FuneralTemplate was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
