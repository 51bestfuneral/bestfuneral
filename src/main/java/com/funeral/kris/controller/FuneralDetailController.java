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

import com.funeral.kris.model.FuneralDetail;
import com.funeral.kris.service.FuneralDetailService;

@Controller
@RequestMapping(value="/funeralDetail")
public class FuneralDetailController {
	
	@Autowired
	private FuneralDetailService funeralDetailService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addFuneralDetailPage() {
		ModelAndView modelAndView = new ModelAndView("add-funeralDetail-form");
		modelAndView.addObject("funeralDetail", new FuneralDetail());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingFuneralDetail(@ModelAttribute FuneralDetail funeralDetail) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		funeralDetailService.addResource(funeralDetail);
		
		String message = "FuneralDetail was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<FuneralDetail> listOfFuneralDetails() {
		ModelAndView modelAndView = new ModelAndView("list-of-funeralDetails");

		List<FuneralDetail> funeralDetails = funeralDetailService.getResources();
		modelAndView.addObject("funeralDetails", funeralDetails);

		return funeralDetails;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editFuneralDetailPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-funeralDetail-form");
		FuneralDetail funeralDetail = funeralDetailService.getResource(id);
		modelAndView.addObject("funeralDetail",funeralDetail);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingFuneralDetail(@ModelAttribute FuneralDetail funeralDetail, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		funeralDetailService.updateResource(funeralDetail);
		
		String message = "FuneralDetail was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteFuneralDetail(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		funeralDetailService.deleteResource(id);
		String message = "FuneralDetail was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
