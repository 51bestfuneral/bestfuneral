package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
	@RequestMapping(value = "/saveWish", method = RequestMethod.GET)
	public void saveWish(HttpServletRequest request) {

		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer wishlistId = Integer.parseInt(request.getParameter("wishlistId"));
		System.out.println(this.getClass() + " saveWish  id=  " + id + " wishlist id=" + wishlistId);

		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");

		Wish wish = wishService.getResource(id);

		Wishlist wishlist = wishlistService.getResource(wishlistId);

		if (wishlist != null) {
			wishlist.setStatus("1");

			System.out.println("  getProcurementCost=" + wish.getProcurementCost() + "  getOriginalPirce="
					+ wishlist.getOriginalPirce());

			wishlist.setOriginalPirce(
					wish.getProcurementCost().add((java.math.BigDecimal) (wishlist.getOriginalPirce() == null
							? BigDecimal.ZERO : wishlist.getOriginalPirce())));
			wishlist.setComment(wish.getWishName());
			wishlist.setPrice(wish.getSellingPrice().doubleValue() + wishlist.getPrice());
			wishlist.setUserId(user.getUsrId());
		} else {
			wishlist = new Wishlist();
			wishlist.setStatus("1");
			wishlist.setOriginalPirce(wish.getProcurementCost().add(wishlist.getOriginalPirce()));
			wishlist.setComment(wish.getWishName());
			wishlist.setPrice(wish.getSellingPrice().doubleValue() + wishlist.getPrice());
			wishlist.setUserId(user.getUsrId());
		}
		wishlistService.addResource(wishlist);

		WishlistDetail wishlistDetail = new WishlistDetail();

		// 如果是同一个user，并且已经选购了相同的产品或者服务，就合并成一条，只更新一下count
		
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
		wishlistDetail.setOriginalPrice(wish.getProcurementCost());
		wishlistDetail.setPrice(wish.getSellingPrice().doubleValue());
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
		Integer userId = Integer.valueOf(request.getParameter("userId"));
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

		System.out.println(this.getClass() + "  listOfWishlists getUserName =" + user.getUserName() + "  getUsrId="
				+ user.getUsrId());

		List<Wishlist> wishlists = wishlistService.getResources();

		List list = new ArrayList();

		if (user != null && wishlists != null) {

			int userId = user.getUsrId();

			Iterator iterator = wishlists.iterator();

			while (iterator.hasNext()) {

				Wishlist Wishlist = (Wishlist) iterator.next();

				if (Wishlist.getUserId().intValue() == userId) {
					list.add(Wishlist);
				}

			}

		} else {

			return list;
		}

		return list;
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
		String condition = " source_id!=1 and   wishlist_id = " + wishList.getWishlistId();
		wishlistDetailService.deleteAllResources(condition);
		Double typePrice = 0d;
		Double totalPrice = 0d;
		List<WishType> wishTypeList = wishTypeService.getResources();
		for (WishType wishType : wishTypeList) {
			if (wishType.getLevel() <= level) {
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
		} else {
			querySQL = "select p from Wish p where 1=1 and wishType = '%s'";
			querySQL = String.format(querySQL, wishType);
		}

		Random random = new Random();
		int randomIndex = 0;

		if (wishType.equals("Cemetery")) {
			List<Cemetery> cemeterys = em.createQuery(querySQL).getResultList();
			Cemetery randomWish = null;

			if (cemeterys != null && cemeterys.size() > 0) {
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
		} else {
			List<Wish> wishs = em.createQuery(querySQL).getResultList();
			Wish randomWish = null;

			if (wishs != null && wishs.size() > 0) {
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
