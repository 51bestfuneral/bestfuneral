package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.init.constants.LoginConstants;
import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.model.Cemetery;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.WishType;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.service.WishService;
import com.funeral.kris.service.WishTypeService;
import com.funeral.kris.service.WishlistDetailService;
import com.funeral.kris.service.WishlistService;

@Controller
@RequestMapping(value = "/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private WishTypeService wishTypeService;
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Autowired
	private EntityManager em;

	@Autowired
	private WishService wishService;

	@ResponseBody
	@RequestMapping(value = "/saveWish", method = RequestMethod.POST)
	public void saveWish(HttpServletRequest request) {

		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer wishlistId = Integer.parseInt(request.getParameter("wishlistId"));
		System.out.println(this.getClass() + " saveWish  id=  " + id + " wishlist id=" + wishlistId);

		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");

		Wish wish = wishService.getResource(id);

		Wishlist wishlist = wishlistService.getResource(wishlistId);

		if (wishlist != null) {
			wishlist.setStatus(LoginConstants.WISHLISTSTATUS_INIT);
			System.out.println("  getProcurementCost=" + wish.getXianenPrice() + "  getOriginalPirce="
					+ wishlist.getOriginalPrice());

			wishlist.setOriginalPirce(
					wish.getProcurementCost().add((wishlist.getOriginalPrice() == null
							? BigDecimal.ZERO : wishlist.getOriginalPrice())));
			wishlist.setComment(wish.getWishName());
			wishlist.setPrice(wish.getSellingPrice().add(wishlist.getPrice()));
			wishlist.setUserId(user.getUsrId());
		} else {
			wishlist = new Wishlist();
			wishlist.setStatus(LoginConstants.WISHLISTSTATUS_INIT);
			wishlist.setOriginalPirce(wish.getProcurementCost().add(wishlist.getOriginalPrice()));
			wishlist.setComment(wish.getWishName());
			wishlist.setPrice(wish.getSellingPrice().add(wishlist.getPrice()));
			wishlist.setUserId(user.getUsrId());
		}
		wishlistService.addResource(wishlist);

		WishlistDetail wishlistDetail = new WishlistDetail();
		
		boolean hasSame=false;

		List<WishlistDetail> wishlistDetailList = wishlistDetailService.getResourceByWishListId(wishlistId);

		if (wishlistDetailList != null && wishlistDetailList.size() > 0) {

			Iterator iterator = wishlistDetailList.iterator();

			while (iterator.hasNext()) {

				WishlistDetail wishlistDetailModel = (WishlistDetail) iterator.next();

				if (wishlistDetailModel.getWishId().intValue() == wish.getWishId().intValue()) {

					wishlistDetailModel.setCount(wishlistDetailModel.getCount() + 1);
					wishlistDetailService.updateResource(wishlistDetailModel);
					
					hasSame=true;
					break;
				}

			}

		}
		
		
		System.out.println("   -----------------  hasSame= "+hasSame);
		
		
		
      if(!hasSame){
		wishlistDetail.setCount(1);
		wishlistDetail.setWishlistId(wishlistId);
		wishlistDetail.setSourceId(WishConstants.wish_source_direct);
		wishlistDetail.setOriginalPrice(wish.getXianenPrice());
		wishlistDetail.setPrice(wish.getSellingPrice());
		wishlistDetail.setWishId(wish.getWishId());
		wishlistDetail.setSourceId(2);

		wishlistDetailService.addResource(wishlistDetail);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addWishlistPage() {
		ModelAndView modelAndView = new ModelAndView("add-wishlist-form");
		modelAndView.addObject("wishlist", new Wishlist());
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Wishlist addingWishlist(HttpServletRequest request) {
		Wishlist wishlist = null;
		Integer userId = ((User) request.getSession().getAttribute("user")).getUsrId();
		Integer wishlistId = Integer.valueOf(request.getParameter("wishlistId"));
		Integer level = Integer.valueOf(request.getParameter("level"));
		wishlist = generateWishList(userId, wishlistId, level);

		return wishlist;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Wishlist> listOfWishlists(HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		List<Wishlist> wishlists = new ArrayList<Wishlist>();
		if (user!= null && user.getUsrId() != null) {
		    Map<String, String> paramMap = new HashMap<String, String>();
		    paramMap.put("userId", user.getUsrId().toString());
		    wishlists = wishlistService.getResources(paramMap);
		}
		return wishlists;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editWishlistPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-wishlist-form");
		Wishlist wishlist = wishlistService.getResource(id);
		modelAndView.addObject("wishlist", wishlist);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView edditingWishlist(@ModelAttribute Wishlist wishlist, @PathVariable Integer id) {

		ModelAndView modelAndView = new ModelAndView("home");

		wishlistService.updateResource(wishlist);

		String message = "Wishlist was successfully edited.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteWishlist(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		wishlistService.deleteResource(id);
		String message = "Wishlist was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	private Wishlist generateWishList(Integer usrId, Integer wishlistId, Integer level) {
		Wishlist wishList = new Wishlist();
		BigDecimal totalPrice = BigDecimal.ZERO;
		wishList.setStatus(LoginConstants.WISHLISTSTATUS_INPROCESS);
		wishList.setUserId(usrId);
		wishList.setWishlistId(wishlistId);
		wishList.setLevel(level);
		wishList.setPrice(BigDecimal.ZERO);
		wishList.setOriginalPirce(BigDecimal.ZERO);
		totalPrice = generateWishDetail(wishList, level);
		wishlistService.updateResource(wishList);
		return wishList;
	}

	private BigDecimal generateWishDetail(Wishlist wishList, Integer level) {
		String condition = " source_id = 1 and   wishlist_id = " + wishList.getWishlistId();
		wishlistDetailService.deleteAllResources(condition);
		BigDecimal typePrice = BigDecimal.ZERO;
		BigDecimal totalPrice = BigDecimal.ZERO;
		List<WishType> wishTypeList = wishTypeService.getResources();
		for (WishType wishType : wishTypeList) {
			if (wishType.getLevel() <= level) {
				typePrice = generateWishForType(wishType.getWishType(), wishList);
				totalPrice = totalPrice.add(typePrice);
			}
		}
		return totalPrice;
	}

	@SuppressWarnings("unchecked")
	private BigDecimal generateWishForType(String wishType, Wishlist wishList) {
		String querySQL = null;
		BigDecimal totalPrice = BigDecimal.ZERO;
		BigDecimal originalPrice = new BigDecimal(0);
		querySQL = "select p from Wish p where 1=1 and generalCode = '%s'";
		querySQL = String.format(querySQL, wishType);
		Random random = new Random();
		int randomIndex = 0;

		List<Wish> wishs = em.createQuery(querySQL).getResultList();
        Wish randomWish = null;
		if (wishs != null && wishs.size() > 0) {
			WishlistDetail detail = new WishlistDetail();
			randomIndex = random.nextInt(wishs.size());
			randomWish = wishs.get(randomIndex);
			detail.setWishId(randomWish.getWishId());
			detail.setPrice(randomWish.getSellingPrice());
			detail.setOriginalPrice(randomWish.getXianenPrice());
			detail.setCount(1);
			detail.setSourceId(1);
			detail.setWishlistId(wishList.getWishlistId());
			detail.setWishType(wishType);
			detail.setCreateDate(new Date());
			detail.setUpdatedDate(new Date());
			detail.setSourceId(1);
			wishlistDetailService.addResource(detail);
			totalPrice = totalPrice.add(detail.getPrice() == null? BigDecimal.ZERO:detail.getPrice());
			originalPrice = originalPrice.add(randomWish.getXianenPrice() == null? BigDecimal.ZERO:randomWish.getXianenPrice());
		}
		wishList.setPrice(wishList.getPrice().add(totalPrice));
		wishList.setOriginalPirce(wishList.getOriginalPrice().add(originalPrice));
		return totalPrice;
	}

}
