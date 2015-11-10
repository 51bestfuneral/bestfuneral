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

import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.service.WishlistDetailService;

@Controller
@RequestMapping(value="/wishlistDetail")
public class WishlistDetailController {
	
	@Autowired
	private WishlistDetailService wishlistDetailService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addWishlistDetailPage() {
		ModelAndView modelAndView = new ModelAndView("add-wishlistDetail-form");
		modelAndView.addObject("wishlistDetail", new WishlistDetail());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingWishlistDetail(@ModelAttribute WishlistDetail wishlistDetail) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		wishlistDetailService.addResource(wishlistDetail);
		
		String message = "WishlistDetail was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<WishlistDetail> listOfWishlistDetails() {
		ModelAndView modelAndView = new ModelAndView("list-of-wishlistDetails");

		List<WishlistDetail> wishlistDetails = wishlistDetailService.getResources();
		modelAndView.addObject("wishlistDetails", wishlistDetails);

		return wishlistDetails;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editWishlistDetailPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-wishlistDetail-form");
		WishlistDetail wishlistDetail = wishlistDetailService.getResource(id);
		modelAndView.addObject("wishlistDetail",wishlistDetail);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingWishlistDetail(@ModelAttribute WishlistDetail wishlistDetail, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		wishlistDetailService.updateResource(wishlistDetail);
		
		String message = "WishlistDetail was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteWishlistDetail(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		wishlistDetailService.deleteResource(id);
		String message = "WishlistDetail was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
