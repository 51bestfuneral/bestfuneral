package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.bean.ShoppingCart;
import com.funeral.kris.busModel.WishListJson;
import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.service.UserService;
import com.funeral.kris.service.WishService;
import com.funeral.kris.service.WishlistDetailService;
import com.funeral.kris.service.WishlistService;

@Controller
@RequestMapping(value = "/wishlistDetail")
public class WishlistDetailController {

	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private UserService userService;
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Autowired
	private WishService wishService;
	private Map<Integer, Wish> wishsMap = null;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addWishlistDetailPage() {
		ModelAndView modelAndView = new ModelAndView("add-wishlistDetail-form");
		modelAndView.addObject("wishlistDetail", new WishlistDetail());
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public List<WishlistDetail> addingWishlistDetail(@RequestBody List<Wish> wishs, HttpServletRequest request) {

		List<WishlistDetail> successList = new ArrayList<WishlistDetail>();
		Integer wishlistId = Integer.valueOf(request.getParameter("wishlistId"));
		wishlistDetailService.deleteAllResources("wishlist_id=" + wishlistId);
		Date sysDate = new Date();
		if (wishsMap == null) {
			initialWishMap();
		}

		for (Wish wish : wishs) {
			WishlistDetail wishlistDetail = new WishlistDetail();
			wish = wishsMap.get(wish.getWishId());
			wishlistDetail.setWishId(wish.getWishId());
			wishlistDetail.setCount(1);
			wishlistDetail.setPrice(wish.getSellingPrice());
			wishlistDetail.setOriginalPrice(wish.getXianenPrice());
			wishlistDetail.setSourceId(1);
			wishlistDetail.setWishType(wish.getGeneralCode());
			wishlistDetail.setWishlistId(wishlistId);
			wishlistDetail.setCreateDate(sysDate);
			wishlistDetail.setUpdatedDate(sysDate);
			wishlistDetailService.addResource(wishlistDetail);
			successList.add(wishlistDetail);
		}

		return successList;
	}

	@ResponseBody
	@RequestMapping(value = "/addSingle", method = RequestMethod.POST)
	public List<WishlistDetail> addingWishlistSingle(@RequestBody List<Wish> wishs, HttpServletRequest request) {

		List<WishlistDetail> successList = new ArrayList<WishlistDetail>();
		Integer wishlistId = Integer.valueOf(request.getParameter("wishlistId"));
		Wishlist wishlist = wishlistService.getResource(wishlistId);
		Date sysDate = new Date();
		if (wishsMap == null) {
			initialWishMap();
		}

		for (Wish wish : wishs) {
			WishlistDetail wishlistDetail = new WishlistDetail();
			wish = wishsMap.get(wish.getWishId());
			wishlistDetail.setWishId(wish.getWishId());
			wishlistDetail.setCount(1);
			wishlistDetail.setPrice(wish.getSellingPrice());
			wishlistDetail.setOriginalPrice(wish.getXianenPrice());
			wishlistDetail.setWishType(wish.getWishType());
			wishlistDetail.setSourceId( WishConstants.wish_source_direct);
			wishlistDetail.setSelected(0);
			wishlistDetail.setWishlistId(wishlistId);
			wishlistDetail.setWishType(wish.getGeneralCode());
			wishlistDetail.setCreateDate(sysDate);
			wishlistDetail.setUpdatedDate(sysDate);
			wishlistDetailService.addResource(wishlistDetail);
			wishlist.setPrice(wishlist.getPrice());
			successList.add(wishlistDetail);
		}

		return successList;
	}

	@ResponseBody
	@RequestMapping(value = "/getDirectWishList", method = RequestMethod.GET, produces = "application/json")
	public List<WishListJson> listOfWishlistDetails(HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		String wishlistId=request.getParameter("wishlistId");
		
		List<WishListJson> wishlistJsons = new ArrayList<WishListJson>();
		List<WishlistDetail> wishlistDetails = wishlistDetailService.getResourceByWishListId(Integer.parseInt(wishlistId));
		for (WishlistDetail wishlistDetail : wishlistDetails) {
			if(wishlistDetail.getSourceId() != null &&
					wishlistDetail.getSourceId().intValue()==WishConstants.wish_source_direct){
			    WishListJson wishListJson = new WishListJson();
			    Integer wishId = wishlistDetail.getWishId();
			    Wish wish = wishsMap.get(wishId);
			    wishListJson.setWishId(wishId);
			    wishListJson.setAmount(wishlistDetail.getCount());
			    wishListJson.setWishName(wish.getWishName());
			    wishListJson.setWishDesc(wish.getWishDesc());
			    wishListJson.setImageUrl(wish.getImgUrl());
			    wishListJson.setPrice(wish.getSellingPrice());
			    wishListJson.setOriginalPrice(wish.getXianenPrice());
			    wishListJson.setWishDetailId(wishlistDetail.getWishlistDetailId());
			    wishListJson.setWishlistId(wishlistDetail.getWishlistId());
			    wishlistJsons.add(wishListJson);
			}
		}
		return wishlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/getSetWishList", method = RequestMethod.GET, produces = "application/json")
	public List<WishListJson> getSetWishList(HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		List<WishListJson> wishlistJsons = new ArrayList<WishListJson>();
		List<WishlistDetail> wishlistDetails = wishlistDetailService.getResources(request);
		for (WishlistDetail wishlistDetail : wishlistDetails) {
			if (wishlistDetail.getSourceId() != null && wishlistDetail.getSourceId().intValue() == WishConstants.wish_source_set) {
				WishListJson wishListJson = new WishListJson();
				Integer wishId = wishlistDetail.getWishId();
				Wish wish = wishsMap.get(wishId);
				wishListJson.setWishId(wishId);
				wishListJson.setAmount(wishlistDetail.getCount());
				wishListJson.setWishName(wish.getWishName());
				wishListJson.setWishDesc(wish.getWishDesc());
				wishListJson.setImageUrl(wish.getImgUrl());
				wishListJson.setPrice(wish.getSellingPrice());
				wishListJson.setOriginalPrice(wish.getXianenPrice());
				wishListJson.setWishDetailId(wishlistDetail.getWishlistDetailId());
				wishListJson.setWishlistId(wishlistDetail.getWishlistId());
				wishlistJsons.add(wishListJson);
			}
		}

		return wishlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/getAllWishList", method = RequestMethod.GET, produces = "application/json")
	public List<WishListJson> getAllWishList(HttpServletRequest request) {
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
			wishListJson.setImageUrl(wish.getImgUrl());
			wishListJson.setPrice(wish.getSellingPrice());
			wishListJson.setOriginalPrice(wish.getXianenPrice());
			wishListJson.setWishDetailId(wishlistDetail.getWishlistDetailId());
			wishListJson.setWishlistId(wishlistDetail.getWishlistId());
			wishlistJsons.add(wishListJson);

		}

		return wishlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/selectOneItem", method = RequestMethod.GET, produces = "application/json")
	public ShoppingCart selectOneItem(HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");

		String wishlistDetailId = request.getParameter("wishlistDetailId");
		String wishlistId = request.getParameter("wishlistId");

		String action = request.getParameter("action");

		ShoppingCart shoppingCart = new ShoppingCart();
		
		Wishlist wishlist = wishlistService.getResource(Integer.parseInt(wishlistId));
		WishlistDetail wishlistDetail = wishlistDetailService.getResource(Integer.parseInt(wishlistDetailId));
		if (Integer.parseInt(action) == 1) {
			wishlistDetail.setSelected(1);

			wishlist.setPrice(wishlist.getPrice()
					.add(wishlistDetail.getPrice().multiply(new BigDecimal(wishlistDetail.getCount()))));
			wishlist.setOriginalPirce(wishlist.getOriginalPrice()
					.add(wishlistDetail.getOriginalPrice().multiply(new BigDecimal(wishlistDetail.getCount()))));
			

		} else {
			
			wishlist.setPrice(wishlist.getPrice()
					.subtract(wishlistDetail.getPrice().multiply(new BigDecimal(wishlistDetail.getCount()))));
			
			wishlist.setOriginalPirce(wishlist.getOriginalPrice()
					.subtract(wishlistDetail.getOriginalPrice().multiply(new BigDecimal(wishlistDetail.getCount()))));

			wishlistDetail.setSelected(0);
		}
		
		wishlistService.updateResource(wishlist);
		
		
		wishlistDetailService.updateResource(wishlistDetail);

		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getResourceByWishListId(wishlistDetail.getWishlistId());

		Integer allSelected = 0;

		Integer count = 0;

		BigDecimal orderTotalCost = BigDecimal.ZERO;

		List<Integer> selectedWishDetailIdList = new ArrayList<Integer>();

		Iterator iterator = wishlistDetails.iterator();

		while (iterator.hasNext()) {
			WishlistDetail detail = (WishlistDetail) iterator.next();

			selectedWishDetailIdList.add(detail.getWishlistDetailId());

			if (detail.getSelected().intValue() == 1) {
				count = count + detail.getCount();

				orderTotalCost = orderTotalCost.add(detail.getPrice().multiply(new BigDecimal(detail.getCount())));
			}

		}

		shoppingCart.setSelectedWishDetailIdList(selectedWishDetailIdList);

		shoppingCart.setCount(count);

		shoppingCart.setGrossFee(orderTotalCost);

		shoppingCart.setUserId(user.getUsrId());

		shoppingCart.setAllSelected(allSelected);

		return shoppingCart;

	}

	@ResponseBody
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET, produces = "application/json")
	public ShoppingCart selectAll(HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");

		String wishlistId = request.getParameter("wishlistId");

		String action = request.getParameter("action");

		ShoppingCart shoppingCart = new ShoppingCart();

		List<WishlistDetail> wishlistDetailList = wishlistDetailService.getResource();

		if (Integer.parseInt(action) == 1) {
			shoppingCart.setAllSelected(1);
		} else {

			shoppingCart.setAllSelected(0);

		}

		Integer allSelected = 0;

		Integer count = 0;

		BigDecimal orderTotalCost = BigDecimal.ZERO;

		List<Integer> selectedWishDetailIdList = new ArrayList<Integer>();

		Iterator iterator = wishlistDetailList.iterator();

		while (iterator.hasNext()) {
			WishlistDetail detail = (WishlistDetail) iterator.next();

			selectedWishDetailIdList.add(detail.getWishlistDetailId());

			if (Integer.parseInt(action) == 1) {
				count = count + detail.getCount();

				orderTotalCost = orderTotalCost.add(detail.getPrice().multiply(new BigDecimal(detail.getCount())));

				detail.setSelected(1);
				allSelected = 1;
				wishlistDetailService.updateResource(detail);
			} else {

				detail.setSelected(0);
				allSelected = 0;

				wishlistDetailService.updateResource(detail);

			}

		}

		shoppingCart.setSelectedWishDetailIdList(selectedWishDetailIdList);

		shoppingCart.setCount(count);

		shoppingCart.setGrossFee(orderTotalCost);

		shoppingCart.setUserId(user.getUsrId());

		shoppingCart.setAllSelected(allSelected);

		return shoppingCart;

	}

	@ResponseBody
	@RequestMapping(value = "/listCount", method = RequestMethod.GET, produces = "application/json")
	public Integer listCountOfWishlistDetails(HttpServletRequest request) {
		List<WishlistDetail> wishlistDetails = wishlistDetailService.getResources(request);
		return wishlistDetails.size();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editWishlistDetailPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-wishlistDetail-form");
		WishlistDetail wishlistDetail = wishlistDetailService.getResource(id);
		modelAndView.addObject("wishlistDetail", wishlistDetail);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView edditingWishlistDetail(HttpServletRequest request, @PathVariable Integer id) {
		int count = Integer.valueOf(request.getParameter("count"));
		WishlistDetail wishlistDetail = wishlistDetailService.getResource(id);

		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");
		Wishlist wishlist = new Wishlist();

		List<Wishlist> wishList = wishlistService.getResources();

		Iterator iterator = wishList.iterator();

		while (iterator.hasNext()) {

			wishlist = (Wishlist) iterator.next();

			if (wishlist.getUserId().intValue() == user.getUsrId().intValue()) {
				break;

			}

		}

		System.out.println("  edditingWishlistDetail    count =" + count + "  wishlistDetail.getCount()="
				+ wishlistDetail.getCount());

		int difference = count - wishlistDetail.getCount();

		wishlist.setPrice(wishlist.getPrice().add(wishlistDetail.getPrice().multiply(new BigDecimal(difference))));

		wishlist.setOriginalPirce(wishlist.getOriginalPrice()
				.add(wishlistDetail.getOriginalPrice().multiply(new BigDecimal(difference))));

		wishlistDetail.setCount(count);

		wishlistService.updateResource(wishlist);

		wishlistDetailService.updateResource(wishlistDetail);

		return null;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteWishlistDetail(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");

		WishlistDetail wishlistDetail = wishlistDetailService.getResource(id);

		// update wishList
		Wishlist wishlist = wishlistService.getResource(wishlistDetail.getWishlistId());

		wishlist.setPrice(wishlist.getPrice().subtract(wishlistDetail.getPrice()));

		wishlist.setOriginalPirce(wishlist.getOriginalPrice().subtract(wishlistDetail.getOriginalPrice()));
		wishlistDetailService.deleteResource(id);

		wishlistService.updateResource(wishlist);

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
