package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.funeral.kris.model.User;
import com.funeral.kris.model.Wish;
import com.funeral.kris.service.WishService;

@Controller
@RequestMapping(value = "/wish")
public class WishController {

	@Autowired
	private WishService wishService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addWishPage() {
		ModelAndView modelAndView = new ModelAndView("add-wish-form");
		modelAndView.addObject("wish", new Wish());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addingWish(@ModelAttribute Wish wish) {

		ModelAndView modelAndView = new ModelAndView("home");
		wishService.addResource(wish);

		String message = "Wish was successfully added.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Wish> list(HttpServletRequest request) {

		List<Wish> whishlist = wishService.getResources(request);
		List<Wish> wishlistFinal = new ArrayList<Wish>();
		for (Wish wish : whishlist) {
			if (wish.getParentWish()!=null && !wish.getParentWish().equals("")) {
			    continue;
			}
			wishlistFinal.add(wish);
		}
		return wishlistFinal;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listForDesign", method = RequestMethod.GET, produces = "application/json")
	public List<Wish> listForDesign(HttpServletRequest request) {

		List<Wish> whishlist = wishService.getResources(request);
		List<Wish> wishlistFinal = new ArrayList<Wish>();
		User user = (User)request.getSession().getAttribute("user");
		Integer gender = user.getGender();
		for (Wish wish : whishlist) {
			if (wish.getParentWish()!=null && !wish.getParentWish().equals("")) {
			    continue;
			}
			if (wish.getGender()!=null && !wish.getGender().equals("0")) {
			    if (gender != null && gender.equals(0) && wish.getGender().equals("2")) {
			    	wishlistFinal.add(wish);
			    }
			    else if (gender != null && gender.equals(1) && wish.getGender().equals("1")) {
			    	wishlistFinal.add(wish);
			    }
			}
			else {
				wishlistFinal.add(wish);
			}
		}
		return wishlistFinal;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editWishPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-wish-form");
		Wish wish = wishService.getResource(id);
		modelAndView.addObject("wish", wish);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView edditingWish(@ModelAttribute Wish wish, @PathVariable Integer id) {

		ModelAndView modelAndView = new ModelAndView("home");

		wishService.updateResource(wish);

		String message = "Wish was successfully edited.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteWish(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		wishService.deleteResource(id);
		String message = "Wish was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
