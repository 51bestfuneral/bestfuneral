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

import com.funeral.kris.model.OptionRule;
import com.funeral.kris.service.OptionRuleService;

@Controller
@RequestMapping(value="/optionRule")
public class OptionRuleController {
	
	@Autowired
	private OptionRuleService optionRuleService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addOptionRulePage() {
		ModelAndView modelAndView = new ModelAndView("add-optionRule-form");
		modelAndView.addObject("optionRule", new OptionRule());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingOptionRule(@ModelAttribute OptionRule optionRule) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		optionRuleService.addResource(optionRule);
		
		String message = "OptionRule was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<OptionRule> listOfOptionRules() {
		ModelAndView modelAndView = new ModelAndView("list-of-optionRules");

		List<OptionRule> optionRules = optionRuleService.getResources();
		modelAndView.addObject("optionRules", optionRules);

		return optionRules;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editOptionRulePage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-optionRule-form");
		OptionRule optionRule = optionRuleService.getResource(id);
		modelAndView.addObject("optionRule",optionRule);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingOptionRule(@ModelAttribute OptionRule optionRule, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		optionRuleService.updateResource(optionRule);
		
		String message = "OptionRule was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteOptionRule(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		optionRuleService.deleteResource(id);
		String message = "OptionRule was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
