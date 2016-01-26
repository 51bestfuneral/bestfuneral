package com.funeral.kris.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.Wish;
import com.funeral.kris.service.WishService;

@Controller
@RequestMapping(value="/wish")
public class WishController {
	
	@Autowired
	private WishService wishService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addWishPage() {
		ModelAndView modelAndView = new ModelAndView("add-wish-form");
		modelAndView.addObject("wish", new Wish());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingWish(@ModelAttribute Wish wish) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		wishService.addResource(wish);
		
		String message = "Wish was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<Wish> listOfWishs(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("list-of-wishs");

		List<Wish> wishs = wishService.getResources(request);
		modelAndView.addObject("wishs", wishs);

		return wishs;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editWishPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-wish-form");
		Wish wish = wishService.getResource(id);
		modelAndView.addObject("wish",wish);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingWish(@ModelAttribute Wish wish, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		wishService.updateResource(wish);
		
		String message = "Wish was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteWish(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		wishService.deleteResource(id);
		String message = "Wish was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
