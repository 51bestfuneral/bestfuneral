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
	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public void buy(HttpServletRequest request) {
		
		
		
		
		
		
		
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/addSingle", method = RequestMethod.POST)
	public List<WishlistDetail> addingWishlistSingle(@RequestBody List<Wish> wishs, HttpServletRequest request) {

		List<WishlistDetail> successList = new ArrayList<WishlistDetail>();
		Integer wishlistId = Integer.valueOf(request.getParameter("wishlistId"));
		Integer wishId = Integer.valueOf(request.getParameter("wishId"));
		
		Wishlist wishlist = wishlistService.getResource(wishlistId);
		Date sysDate = new Date();
		if (wishsMap == null) {
			initialWishMap();
		}
		System.out.println(this.getClass()+"  addingWishlistSingle  wishlistId="+wishlistId+"  wishsMap="+wishsMap);

		for (Wish wish : wishs) {
			
			if(wish.getWishId().intValue()==wishId.intValue()){
			WishlistDetail wishlistDetail = new WishlistDetail();
			wish = wishsMap.get(wish.getWishId());
			wishlistDetail.setWishId(wish.getWishId());
			wishlistDetail.setCount(1);
			wishlistDetail.setPrice(wish.getSellingPrice());
			wishlistDetail.setOriginalPrice(wish.getXianenPrice());
			wishlistDetail.setWishType(wish.getWishType());
			wishlistDetail.setSourceId(WishConstants.wish_source_direct);
			wishlistDetail.setSelected(0);
			wishlistDetail.setWishlistId(wishlistId);
			wishlistDetail.setWishType(wish.getGeneralCode());
			wishlistDetail.setCreateDate(sysDate);
			wishlistDetail.setUpdatedDate(sysDate);
			
			int wishDetailId=this.checkExist(wish.getWishId(), wishlistId);
			System.out.println(this.getClass()+"  addingWishlistSingle checkExist   wishDetailId="+wishDetailId);

			if(wishDetailId>0){
				wishlistDetail.setWishlistDetailId(wishDetailId);
				wishlistDetail.setCount(wishlistDetail.getCount()+1);
			}
			
			wishlistDetailService.addResource(wishlistDetail);
			wishlist.setPrice(wishlist.getPrice());
			successList.add(wishlistDetail);
			break;
			}
		}

		wishlist = this.getCalculatedWishList(wishlistId);

		wishlistService.updateResource(wishlist);

		return successList;
	}

	public int checkExist(int wishId, int wishlistId) {

		List<WishlistDetail> list = wishlistDetailService.getResourceByWishListId(wishlistId);

		if (list != null && list.size() > 0) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				WishlistDetail detail = (WishlistDetail) iterator.next();

				if (detail.getWishId().intValue() == wishId) {

					return detail.getWishlistDetailId();
				}

			}

		}

		return 0;

	}

	@ResponseBody
	@RequestMapping(value = "/getDirectWishList", method = RequestMethod.GET, produces = "application/json")
	public List<WishListJson> listOfWishlistDetails(HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		String wishlistId = request.getParameter("wishlistId");

		List<WishListJson> wishlistJsons = new ArrayList<WishListJson>();
		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getResourceByWishListId(Integer.parseInt(wishlistId));
		for (WishlistDetail wishlistDetail : wishlistDetails) {
			if (wishlistDetail.getSourceId() != null
					&& wishlistDetail.getSourceId().intValue() == WishConstants.wish_source_direct
					&& wishlistDetail.getSelected().intValue() == 1) {
				WishListJson wishListJson = new WishListJson();
				Integer wishId = wishlistDetail.getWishId();
				Wish wish = wishsMap.get(wishId);
				wishListJson.setWishId(wishId);
				wishListJson.setAmount(wishlistDetail.getCount());
				wishListJson.setWishName(wish.getWishName());
				wishListJson.setWishDesc(wish.getWishDesc());
				wishListJson.setImageUrl(wish.getImgUrl());
				wishListJson.setPrice(wish.getSellingPrice() );
				wishListJson.setOriginalPrice(wish.getProcurementCost());
				wishListJson.setWishDetailId(wishlistDetail.getWishlistDetailId());
				wishListJson.setWishlistId(wishlistDetail.getWishlistId());
				wishListJson.setSelectedPrice(wishlistDetail.getSelectedPrice());
				wishlistJsons.add(wishListJson);
			}
		}
		return wishlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/getDirectWishListForPaymnetConfirm", method = RequestMethod.GET, produces = "application/json")
	public List<WishListJson> getDirectWishListForPaymnetConfirm(HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		String wishlistId = request.getParameter("wishlistId");

		List<WishListJson> wishlistJsons = new ArrayList<WishListJson>();
		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getSelectedWishlistDetailByWishListId(Integer.parseInt(wishlistId));

		for (WishlistDetail wishlistDetail : wishlistDetails) {
			if (wishlistDetail.getSourceId() != null
					&& wishlistDetail.getSourceId().intValue() == WishConstants.wish_source_direct
					&& wishlistDetail.getSelected().intValue() == 1) {
				WishListJson wishListJson = new WishListJson();
				Integer wishId = wishlistDetail.getWishId();
				Wish wish = wishsMap.get(wishId);
				wishListJson.setWishId(wishId);
				wishListJson.setAmount(wishlistDetail.getCount());
				wishListJson.setWishName(wish.getWishName());
				wishListJson.setWishDesc(wish.getWishDesc());
				wishListJson.setImageUrl(wish.getImgUrl());
				wishListJson.setPrice(new BigDecimal(wish.getSellingPrice().doubleValue() * wishlistDetail.getCount()));
				// wishListJson.setOriginalPrice(wish.getProcurementCost());
				wishListJson.setWishDetailId(wishlistDetail.getWishlistDetailId());
				wishListJson.setWishlistId(wishlistDetail.getWishlistId());
				wishListJson.setSelectedPrice(wishlistDetail.getSelectedPrice());
				wishlistJsons.add(wishListJson);
			}
		}
		return wishlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/getDirectWishListForShoppingCart", method = RequestMethod.GET, produces = "application/json")
	public List<WishListJson> getDirectWishListForShoppingCart(HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		String wishlistId = request.getParameter("wishlistId");

		List<WishListJson> wishlistJsons = new ArrayList<WishListJson>();
		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getResourceByWishListId(Integer.parseInt(wishlistId));
		for (WishlistDetail wishlistDetail : wishlistDetails) {
			if (wishlistDetail.getSourceId() != null
					&& wishlistDetail.getSourceId().intValue() == WishConstants.wish_source_direct) {
				WishListJson wishListJson = new WishListJson();
				Integer wishId = wishlistDetail.getWishId();
				Wish wish = wishsMap.get(wishId);
				wishListJson.setWishId(wishId);
				wishListJson.setAmount(wishlistDetail.getCount());
				wishListJson.setWishName(wish.getWishName());
				wishListJson.setWishDesc(wish.getWishDesc());
				wishListJson.setImageUrl(wish.getImgUrl());
				wishListJson.setPrice(wish.getSellingPrice());
				wishListJson.setOriginalPrice(wish.getProcurementCost());
				wishListJson.setWishDetailId(wishlistDetail.getWishlistDetailId());
				wishListJson.setWishlistId(wishlistDetail.getWishlistId());
				wishListJson.setSelectedPrice(wishlistDetail.getSelectedPrice());
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
	@RequestMapping(value = "/loadShoppingCart", method = RequestMethod.GET, produces = "application/json")
	public ShoppingCart loadShoppingCart(HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");

		String wishlistId = request.getParameter("wishlistId");
		Wishlist wishlist = wishlistService.getResource(Integer.parseInt(wishlistId));

		ShoppingCart shoppingCart = new ShoppingCart();

		List<WishlistDetail> wishlistDetails = wishlistDetailService.getResourceByWishListId(wishlist.getWishlistId());

		Integer allSelected = 0;

		boolean selected = false;

		Integer count = 0;

		BigDecimal orderTotalCost = BigDecimal.ZERO;

		List<Integer> selectedWishDetailIdList = new ArrayList<Integer>();

		Iterator iterator = wishlistDetails.iterator();

		while (iterator.hasNext()) {
			WishlistDetail detail = (WishlistDetail) iterator.next();

			if (detail.getSelected()!= null && detail.getSelected().intValue() == 1) {
				count = count + detail.getCount();
				selectedWishDetailIdList.add(detail.getWishlistDetailId());

				orderTotalCost = orderTotalCost.add(detail.getPrice().multiply(new BigDecimal(detail.getCount())));
			}
		}

		if (selectedWishDetailIdList.size() == wishlistDetails.size() && selectedWishDetailIdList.size() > 0) {
			allSelected = 1;
		}

		shoppingCart.setSelectedWishDetailIdList(selectedWishDetailIdList);

		shoppingCart.setCount(count);

		shoppingCart.setGrossFee(orderTotalCost);

		shoppingCart.setUserId(user.getUsrId());

		shoppingCart.setAllSelected(allSelected);

		return shoppingCart;

	}

	@ResponseBody
	@RequestMapping(value = "/selectOneItem", method = RequestMethod.GET, produces = "application/json")
	public ShoppingCart selectOneItem(HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");

		String wishlistDetailId = request.getParameter("wishDetaillistId");
		String wishlistId = request.getParameter("wishlistId");

		// String action = request.getParameter("action");

		BigDecimal price = BigDecimal.ZERO;
		BigDecimal originalPrice = BigDecimal.ZERO;

		ShoppingCart shoppingCart = new ShoppingCart();

		Wishlist wishlist = wishlistService.getResource(Integer.parseInt(wishlistId));
		WishlistDetail wishlistDetail = wishlistDetailService.getResource(Integer.parseInt(wishlistDetailId));

		if (wishlistDetail.getSelected().intValue() == 0) {
			wishlistDetail.setSelected(1);

			if (wishlistDetail.getPrice() != null) {
				price = price.add(wishlist.getPrice()
						.add(wishlistDetail.getPrice().multiply(new BigDecimal(wishlistDetail.getCount()))));
			}

			if (wishlist.getOriginalPrice() != null && wishlistDetail.getOriginalPrice() != null) {
				originalPrice = originalPrice.add(wishlist.getOriginalPrice()
						.add(wishlistDetail.getOriginalPrice().multiply(new BigDecimal(wishlistDetail.getCount()))));

			}
			wishlist.setPrice(price);
			wishlist.setOriginalPirce(originalPrice);
		} else {
			if (wishlistDetail.getPrice() != null) {
				wishlist.setPrice(wishlist.getPrice()
						.subtract(wishlistDetail.getPrice().multiply(new BigDecimal(wishlistDetail.getCount()))));
			}
			if (wishlistDetail.getOriginalPrice() != null) {
				wishlist.setOriginalPirce(wishlist.getOriginalPrice().subtract(
						wishlistDetail.getOriginalPrice().multiply(new BigDecimal(wishlistDetail.getCount()))));
			}
			wishlistDetail.setSelected(0);
		}

		wishlistService.updateResource(wishlist);

		wishlistDetailService.updateResource(wishlistDetail);

		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getResourceByWishListId(wishlistDetail.getWishlistId());

		Integer allSelected = 0;

		boolean selected = false;

		Integer count = 0;

		BigDecimal orderTotalCost = BigDecimal.ZERO;

		List<Integer> selectedWishDetailIdList = new ArrayList<Integer>();

		Iterator iterator = wishlistDetails.iterator();

		while (iterator.hasNext()) {
			WishlistDetail detail = (WishlistDetail) iterator.next();

			if (detail.getSelected().intValue() == 1) {
				count = count + detail.getCount();
				selectedWishDetailIdList.add(detail.getWishlistDetailId());
				if (detail.getPrice() != null) {
					orderTotalCost = orderTotalCost.add(detail.getPrice().multiply(new BigDecimal(detail.getCount())));
				}
			}
		}

		if (selectedWishDetailIdList.size() == wishlistDetails.size() && selectedWishDetailIdList.size() > 0) {
			allSelected = 1;
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

		System.out.println(" wishlistId======== " + wishlistId);

		// Wishlist wishlist =
		// wishlistService.getResource(Integer.parseInt(wishlistId));

		boolean allSelected = true;
		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getResourceByWishListId(Integer.parseInt(wishlistId));

		Iterator iterator = wishlistDetails.iterator();

		while (iterator.hasNext()) {

			WishlistDetail wishlistDetail = (WishlistDetail) iterator.next();

			if (wishlistDetail.getSelected().intValue() == 0) {
				allSelected = false;
				break;
			}

		}
		// String action = request.getParameter("action");

		ShoppingCart shoppingCart = new ShoppingCart();

		System.out.println(" allSelected======== " + allSelected);

		if (!allSelected) {
			shoppingCart.setAllSelected(1);
		} else {

			shoppingCart.setAllSelected(0);

		}

		Integer count = 0;

		List<Integer> selectedWishDetailIdList = new ArrayList<Integer>();

		List<WishlistDetail> wishlistDetailList = wishlistDetailService
				.getResourceByWishListId(Integer.parseInt(wishlistId));

		Iterator iter = wishlistDetailList.iterator();

		while (iter.hasNext()) {

			WishlistDetail wishDetail = (WishlistDetail) iter.next();

			if (shoppingCart.getAllSelected().intValue() == 0) {
				wishDetail.setSelected(0);
				wishlistDetailService.updateResource(wishDetail);
				// price = BigDecimal.ZERO;
				// originalPrice = BigDecimal.ZERO;
			}

			else {
				wishDetail.setSelected(1);
				wishlistDetailService.updateResource(wishDetail);
				// price=price.add(wishDetail.getPrice().multiply(new
				// BigDecimal(wishDetail.getCount())));
				// originalPrice=originalPrice.add(wishDetail.getOriginalPrice().multiply(new
				// BigDecimal(wishDetail.getCount())));
				selectedWishDetailIdList.add(wishDetail.getWishlistDetailId());
			}

		}

		Wishlist wishlist = this.getCalculatedWishList(Integer.parseInt(wishlistId));

		wishlistService.updateResource(wishlist);

		shoppingCart.setSelectedWishDetailIdList(selectedWishDetailIdList);

		shoppingCart.setCount(count);

		shoppingCart.setGrossFee(wishlist.getPrice());

		shoppingCart.setUserId(user.getUsrId());

		return shoppingCart;

	}

	@ResponseBody
	@RequestMapping(value = "/listCount", method = RequestMethod.GET, produces = "application/json")
	public Integer listCountOfWishlistDetails(HttpServletRequest request) {
		List<WishlistDetail> wishlistDetails = wishlistDetailService.getResources(request);
		return wishlistDetails.size();
	}

	// @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	// public ModelAndView editWishlistDetailPage(@PathVariable Integer id) {
	// ModelAndView modelAndView = new ModelAndView("edit-wishlistDetail-form");
	// WishlistDetail wishlistDetail = wishlistDetailService.getResource(id);
	// modelAndView.addObject("wishlistDetail", wishlistDetail);
	// return modelAndView;
	// }
	@ResponseBody
	@RequestMapping(value = "/oneMore", method = RequestMethod.GET)
	public ModelAndView oneMore(HttpServletRequest request) {

		String wishDetailId = request.getParameter("wishDetailId");

		WishlistDetail wishlistDetail = wishlistDetailService.getResource(Integer.parseInt(wishDetailId));

		int count = wishlistDetail.getCount() + 1;
		wishlistDetail.setCount(count);
		wishlistDetailService.updateResource(wishlistDetail);

		Wishlist wishlist = this.getCalculatedWishList(wishlistDetail.getWishlistId());

		wishlistService.updateResource(wishlist);

		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/subtraction", method = RequestMethod.GET)
	public ModelAndView subtraction(HttpServletRequest request) {

		String wishDetailId = request.getParameter("wishDetailId");

		WishlistDetail wishlistDetail = wishlistDetailService.getResource(Integer.parseInt(wishDetailId));

		int count = wishlistDetail.getCount() - 1;
		wishlistDetail.setCount(count);

		if (wishlistDetail.getCount().intValue() == 0) {

			wishlistDetailService.deleteResource(Integer.parseInt(wishDetailId));

		} else {

			wishlistDetailService.updateResource(wishlistDetail);

		}

		Wishlist wishlist = this.getCalculatedWishList(wishlistDetail.getWishlistId());

		wishlistService.updateResource(wishlist);
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request) {

		String wishDetailId = request.getParameter("wishDetailId");

		WishlistDetail wishlistDetail = wishlistDetailService.getResource(Integer.parseInt(wishDetailId));

		wishlistDetailService.deleteResource(Integer.parseInt(wishDetailId));

		Wishlist wishlist = this.getCalculatedWishList(wishlistDetail.getWishlistId());

		wishlistService.updateResource(wishlist);

	}

	private void initialWishMap() {
		HttpServletRequest fakeRequest = null;
		List<Wish> wishs = wishService.getResources(fakeRequest);
		wishsMap = new HashMap<Integer, Wish>();
		for (Wish wish : wishs) {
			wishsMap.put(wish.getWishId(), wish);
		}

	}

	public Wishlist getCalculatedWishList(int wishListId) {

		Wishlist wishlist = wishlistService.getResource(wishListId);

		List<WishlistDetail> wishlistDetails = wishlistDetailService.getSelectedWishlistDetailByWishListId(wishListId);

		BigDecimal price = BigDecimal.ZERO;
		BigDecimal originalPirce = BigDecimal.ZERO;

		if (wishlistDetails != null && wishlistDetails.size() > 0) {

			Iterator iterator = wishlistDetails.iterator();

			while (iterator.hasNext()) {

				WishlistDetail detail = (WishlistDetail) iterator.next();

				if (detail.getPrice() != null) {

					price = price.add(detail.getPrice().multiply(new BigDecimal(detail.getCount())));
				}

				if (detail.getOriginalPrice() != null) {

					originalPirce = originalPirce
							.add(detail.getOriginalPrice().multiply(new BigDecimal(detail.getCount())));

				}

			}

		}

		wishlist.setPrice(price);

		wishlist.setOriginalPirce(originalPirce);

		return wishlist;

	}

}
