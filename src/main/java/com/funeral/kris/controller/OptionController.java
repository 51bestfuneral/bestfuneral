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

import com.funeral.kris.model.Option;
import com.funeral.kris.service.OptionService;

@Controller
@RequestMapping(value="/option")
public class OptionController {
	
	@Autowired
	private OptionService optionService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addOptionPage() {
		ModelAndView modelAndView = new ModelAndView("add-option-form");
		modelAndView.addObject("option", new Option());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingOption(@ModelAttribute Option option) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		optionService.addResource(option);
		
		String message = "Option was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<Option> listOfOptions() {
		ModelAndView modelAndView = new ModelAndView("list-of-options");

		List<Option> options = optionService.getResources();
		modelAndView.addObject("options", options);

		return options;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editOptionPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-option-form");
		Option option = optionService.getResource(id);
		modelAndView.addObject("option",option);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingOption(@ModelAttribute Option option, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		optionService.updateResource(option);
		
		String message = "Option was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteOption(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		optionService.deleteResource(id);
		String message = "Option was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
