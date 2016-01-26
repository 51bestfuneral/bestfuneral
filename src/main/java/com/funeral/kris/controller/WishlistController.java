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

import com.funeral.kris.model.Wishlist;
import com.funeral.kris.service.WishlistService;

@Controller
@RequestMapping(value="/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addWishlistPage() {
		ModelAndView modelAndView = new ModelAndView("add-wishlist-form");
		modelAndView.addObject("wishlist", new Wishlist());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingWishlist(@ModelAttribute Wishlist wishlist) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		wishlistService.addResource(wishlist);
		
		String message = "Wishlist was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<Wishlist> listOfWishlists() {
		ModelAndView modelAndView = new ModelAndView("list-of-wishlists");

		List<Wishlist> wishlists = wishlistService.getResources();
		modelAndView.addObject("wishlists", wishlists);

		return wishlists;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editWishlistPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-wishlist-form");
		Wishlist wishlist = wishlistService.getResource(id);
		modelAndView.addObject("wishlist",wishlist);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingWishlist(@ModelAttribute Wishlist wishlist, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		wishlistService.updateResource(wishlist);
		
		String message = "Wishlist was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteWishlist(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		wishlistService.deleteResource(id);
		String message = "Wishlist was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
