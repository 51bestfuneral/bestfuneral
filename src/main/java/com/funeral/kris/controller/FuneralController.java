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

import com.funeral.kris.model.Funeral;
import com.funeral.kris.service.FuneralService;

@Controller
@RequestMapping(value="/funeral")
public class FuneralController {
	
	@Autowired
	private FuneralService funeralService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addFuneralPage() {
		ModelAndView modelAndView = new ModelAndView("add-funeral-form");
		modelAndView.addObject("funeral", new Funeral());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingFuneral(@ModelAttribute Funeral funeral) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		funeralService.addResource(funeral);
		
		String message = "Funeral was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<Funeral> listOfFunerals() {
		ModelAndView modelAndView = new ModelAndView("list-of-funerals");

		List<Funeral> funerals = funeralService.getResources();
		modelAndView.addObject("funerals", funerals);

		return funerals;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editFuneralPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-funeral-form");
		Funeral funeral = funeralService.getResource(id);
		modelAndView.addObject("funeral",funeral);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingFuneral(@ModelAttribute Funeral funeral, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		funeralService.updateResource(funeral);
		
		String message = "Funeral was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteFuneral(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		funeralService.deleteResource(id);
		String message = "Funeral was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
