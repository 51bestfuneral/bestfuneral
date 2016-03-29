package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.busModel.WishListJson;
import com.funeral.kris.model.Answer;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.service.WishService;
import com.funeral.kris.service.WishlistDetailService;

@Controller
@RequestMapping(value="/wishlistDetail")
public class WishlistDetailController {
	
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Autowired
	private WishService wishService;
	private Map<Integer, Wish> wishsMap = null;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addWishlistDetailPage() {
		ModelAndView modelAndView = new ModelAndView("add-wishlistDetail-form");
		modelAndView.addObject("wishlistDetail", new WishlistDetail());
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public List<WishlistDetail> addingWishlistDetail(@RequestBody List<Wish> wishs, HttpServletRequest request) {

		List<WishlistDetail> successList = new ArrayList<WishlistDetail>();
		Integer wishlistId = Integer.valueOf(request.getParameter("wishlistId"));
		wishlistDetailService.deleteAllResources("wishlist_id="+wishlistId);
		Date sysDate = new Date();
		if (wishsMap == null) {
			initialWishMap();
		}

		for (Wish wish: wishs) {
			WishlistDetail wishlistDetail = new WishlistDetail();
		    wish = wishsMap.get(wish.getWishId());
		    wishlistDetail.setWishId(wish.getWishId());
		    wishlistDetail.setCount(1);
		    wishlistDetail.setPrice(wish.getSellingPrice().doubleValue());
		    wishlistDetail.setOriginalPrice(wish.getProcurementCost());
		    wishlistDetail.setWishType(wish.getWishType());
		    wishlistDetail.setWishlistId(wishlistId);
		    wishlistDetail.setCreateDate(sysDate);
		    wishlistDetail.setUpdatedDate(sysDate);
		    wishlistDetailService.addResource(wishlistDetail);
		    successList.add(wishlistDetail);
		}

		return successList;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<WishListJson> listOfWishlistDetails(HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		List<WishListJson> wishlistJsons = new ArrayList<WishListJson>();
		List<WishlistDetail> wishlistDetails = wishlistDetailService.getResources(request);
		for (WishlistDetail wishlistDetail : wishlistDetails) {
			WishListJson wishListJson = new WishListJson();
		    Integer wishId = wishlistDetail.getWishId();
		    Wish wish = wishsMap.get(wishId);
		    wishListJson.setWishId(wishId);
		    wishListJson.setAmount(wishlistDetail.getCount());
		    wishListJson.setWishName(wish.getWishName());
		    wishListJson.setWishDesc(wish.getWishDesc());
		    wishListJson.setImageUrl(wish.getUrl());
		    wishListJson.setPrice(wish.getSellingPrice().doubleValue());
		    wishListJson.setOriginalPrice(wish.getProcurementCost());
		    wishListJson.setWishDetailId(wishlistDetail.getWishlistDetailId());
		    wishListJson.setWishlistId(wishlistDetail.getWishlistId());
		    wishlistJsons.add(wishListJson);
		}

		return wishlistJsons;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editWishlistDetailPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-wishlistDetail-form");
		WishlistDetail wishlistDetail = wishlistDetailService.getResource(id);
		modelAndView.addObject("wishlistDetail",wishlistDetail);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingWishlistDetail(HttpServletRequest request, @PathVariable Integer id) {
		int count = Integer.valueOf(request.getParameter("count"));
		WishlistDetail wishlistDetail = wishlistDetailService.getResource(id);
		wishlistDetail.setCount(count);
		wishlistDetailService.updateResource(wishlistDetail);
		
		return null;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteWishlistDetail(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		wishlistDetailService.deleteResource(id);
		String message = "WishlistDetail was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	private void initialWishMap() {
		HttpServletRequest fakeRequest = null;
		List<Wish> wishs = wishService.getResources(fakeRequest);
		wishsMap = new HashMap<Integer, Wish>();
		for (Wish wish : wishs) {
			wishsMap.put(wish.getWishId(), wish);
		}
		
	}

}
