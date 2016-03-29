package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.Cemetery;
import com.funeral.kris.model.OptionRule;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.WishType;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.service.WishTypeService;
import com.funeral.kris.service.WishlistDetailService;
import com.funeral.kris.service.WishlistService;

@Controller
@RequestMapping(value="/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private WishTypeService wishTypeService;
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Autowired
	private EntityManager em;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addWishlistPage() {
		ModelAndView modelAndView = new ModelAndView("add-wishlist-form");
		modelAndView.addObject("wishlist", new Wishlist());
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Wishlist addingWishlist(HttpServletRequest request) {
		Wishlist wishlist = null;
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		Integer wishlistId = Integer.valueOf(request.getParameter("wishlistId"));
		Integer level = Integer.valueOf(request.getParameter("level"));
		wishlist = generateWishList(userId, wishlistId, level);
		
		return wishlist;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<Wishlist> listOfWishlists(HttpServletRequest request) {
		List<Wishlist> wishlists = wishlistService.getResources(request);

		if (wishlists.isEmpty() && request.getParameter("userId") !=null && !request.getParameter("userId").equals("")) {
			Date sysDate = new Date();
			Wishlist wishlist = new Wishlist();
			wishlist.setUserId(Integer.valueOf(request.getParameter("userId")));
            wishlist.setCreateDate(sysDate);
            wishlist.setUpdatedDate(sysDate);
            wishlist.setPrice(0d);
            wishlistService.addResource(wishlist);
            wishlists = wishlistService.getResources(request);
		}
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

	private Wishlist generateWishList(Integer usrId, Integer wishlistId, Integer level) {
		Wishlist wishList = new Wishlist();
		Double totalPrice = 0d;
		wishList.setStatus("I");
		wishList.setUserId(usrId);
		wishList.setWishlistId(wishlistId);
		wishList.setLevel(level);
		totalPrice = generateWishDetail(wishList, level);
		wishList.setPrice(totalPrice);
		wishlistService.updateResource(wishList);
		return wishList;
	}
	
	private Double generateWishDetail(Wishlist wishList, Integer level) {
		String condition = "wishlist_id = "+wishList.getWishlistId();
		wishlistDetailService.deleteAllResources(condition);
		Double typePrice = 0d;
		Double totalPrice = 0d;
		List<WishType> wishTypeList = wishTypeService.getResources();
		for (WishType wishType: wishTypeList) {
			if (wishType.getLevel()<= level) {
			    typePrice = generateWishForType(wishType.getWishType(), wishList);
			    totalPrice = totalPrice + typePrice;
			}
		}
		return totalPrice;
	}
	
	@SuppressWarnings("unchecked")
	private Double generateWishForType(String wishType, Wishlist wishList) {
		String querySQL = null;
        Double totalPrice = 0d;
        BigDecimal originalPrice = new BigDecimal(0);
		if (wishType.equals("Cemetery")) {
		    querySQL = "select p from Cemetery p ";
		}
		else {
			querySQL = "select p from Wish p where 1=1 and wishType = '%s'";
			querySQL = String.format(querySQL, wishType);
		}

		Random random = new Random();
		int randomIndex = 0;

		if (wishType.equals("Cemetery")) {
			List<Cemetery> cemeterys = em.createQuery(querySQL).getResultList();
			Cemetery randomWish = null;

			if (cemeterys!=null && cemeterys.size() > 0) {
				WishlistDetail detail = new WishlistDetail();
				randomIndex = random.nextInt(cemeterys.size());
				randomWish = cemeterys.get(randomIndex);
				detail.setWishId(randomWish.getCemeteryId());
				detail.setPrice(Double.valueOf(randomWish.getPrice().toString()));
				detail.setOriginalPrice(randomWish.getOriginalPrice());
				detail.setCount(1);
				detail.setWishlistId(wishList.getWishlistId());
				detail.setWishType(wishType);
				detail.setCreateDate(new Date());
				detail.setUpdatedDate(new Date());
				wishlistDetailService.addResource(detail);
				totalPrice = totalPrice + detail.getPrice();
				originalPrice = originalPrice.add(randomWish.getOriginalPrice());
			}
		}
		else {
			List<Wish> wishs = em.createQuery(querySQL).getResultList();
			Wish randomWish = null;

			if (wishs!=null && wishs.size() > 0) {
				WishlistDetail detail = new WishlistDetail();
				randomIndex = random.nextInt(wishs.size());
				randomWish = wishs.get(randomIndex);
				detail.setWishId(randomWish.getWishId());
				detail.setPrice(randomWish.getSellingPrice().doubleValue());
				detail.setOriginalPrice(randomWish.getProcurementCost());
				detail.setCount(1);
				detail.setWishlistId(wishList.getWishlistId());
				detail.setWishType(wishType);
				detail.setCreateDate(new Date());
				detail.setUpdatedDate(new Date());
				wishlistDetailService.addResource(detail);
				totalPrice = totalPrice + detail.getPrice();
				originalPrice = originalPrice.add(randomWish.getProcurementCost());
			}
		}
		wishList.setPrice(totalPrice);
		wishList.setOriginalPirce(originalPrice);
		return totalPrice;
	}


}
