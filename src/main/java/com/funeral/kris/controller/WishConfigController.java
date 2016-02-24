package com.funeral.kris.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.model.Wish;
import com.funeral.kris.service.WishService;

@Controller
@RequestMapping(value = "/wishConfig")
public class WishConfigController {
	@Autowired
	private WishService wishService;

	@ResponseBody
	@RequestMapping(value = "/saveWish", method = RequestMethod.POST)
	public void saveWish(@RequestBody Wish wish) {

		wishService.addResource(wish);
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/listById", method = RequestMethod.GET, produces = "application/json")
	public Wish listWishById(HttpServletRequest request) {

		String id = (String) request.getParameter("id");

		int wishId = Integer.parseInt(id);

		Wish wish = wishService.getResource(wishId);

		return wish;
	}
	@ResponseBody
	@RequestMapping(value = "/removeWishById", method = RequestMethod.GET, produces = "application/json")
	public void removeWishById(HttpServletRequest request) {
		
		String id = (String) request.getParameter("id");
		
		int wishId = Integer.parseInt(id);
		
		wishService.deleteResource(wishId);
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Wish> list(HttpServletRequest request) {

		List<Wish> wishList = wishService.getResources(request);

		return wishList;
	}
	

}
