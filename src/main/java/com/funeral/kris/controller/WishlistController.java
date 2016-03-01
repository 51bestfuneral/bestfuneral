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
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.busModel.CartlistJson;
import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.init.constants.LoginConstants;
import com.funeral.kris.model.Cart;
import com.funeral.kris.model.CartDetail;
import com.funeral.kris.model.OrderDetail;
import com.funeral.kris.model.TaoConfig;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.WishOrder;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.service.CartDetailService;
import com.funeral.kris.service.CartService;
import com.funeral.kris.service.OrderDetailService;
import com.funeral.kris.service.WishOrderService;
import com.funeral.kris.service.WishService;
import com.funeral.kris.service.WishlistDetailService;
import com.funeral.kris.service.WishlistService;

@Controller
@RequestMapping(value = "/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private CartService cartService;
	@Autowired
	private CartDetailService cartDetailService;
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Autowired
	private WishOrderService wishOrderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private EntityManager em;
	
	private List<TaoConfig> taoConfigs = new ArrayList<TaoConfig>();

	@Autowired
	private WishService wishService;
	private Integer gender;

	@ResponseBody
	@RequestMapping(value = "/saveWish", method = RequestMethod.POST)
	public void saveWish(HttpServletRequest request) {

		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer wishlistId = Integer.parseInt(request.getParameter("wishlistId"));
		System.out.println(this.getClass() + " saveWish  id=  " + id + " wishlist id=" + wishlistId);

		HttpSession session = request.getSession(false);

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
		wishlistDetail.setWishType(wish.getGeneralCode());
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
		User user = (User) request.getSession().getAttribute("user");
		gender = user.getGender();
		Integer userId = user.getUsrId();
		Integer wishlistId = Integer.valueOf(request.getParameter("wishlistId"));
		Integer level = Integer.valueOf(request.getParameter("level"));
		generateTaoCanRule(level, user.getBirthday());
		wishlist = generateWishList(userId, wishlistId, level);

		return wishlist;
	}

	@ResponseBody
	@RequestMapping(value = "/generateOrderByWish", method = RequestMethod.POST)
	public WishOrder generateOrderByWishlist(HttpServletRequest request) {
		Wishlist wishlist = null;
		//定制完成，数据转移到WishOrder和OrderDetail
		WishOrder order = new WishOrder();
		User user = (User) request.getSession().getAttribute("user");
		wishlist = wishlistService.getResource(user.getWishlistId());
		order.setUserId(user.getUsrId());
		order.setPrice(wishlist.getPrice());
		order.setOriginalPrice(wishlist.getOriginalPrice());
		order.setSourceId(WishConstants.order_source_set);
		order.setPayMethod(WishConstants.wishorder_paymethod_together);
		order.setStatusId(WishConstants.wishlist_status_init);
		wishOrderService.addResource(order);
		List<WishlistDetail> wishdetailList = new ArrayList<WishlistDetail>();
		wishdetailList = wishlistDetailService.getResourceByWishListId(user.getWishlistId());

		for (WishlistDetail wishlistDetail: wishdetailList) {
			OrderDetail orderdetail = new OrderDetail();
			orderdetail.setCount(wishlistDetail.getCount());
			orderdetail.setOrderId(order.getWishOrderId());
			orderdetail.setOriginalPrice(wishlistDetail.getOriginalPrice());
			orderdetail.setPrice(wishlistDetail.getPrice());
			orderdetail.setWishId(wishlistDetail.getWishId());
			orderdetail.setSourceId(WishConstants.order_source_set);
			orderdetail.setWishListId(user.getWishlistId());
			orderdetail.setCreatedDate(new Date());
			orderdetail.setUpdatedDate(new Date());
			orderDetailService.addResource(orderdetail);
		}
		return order;
	}
	@Deprecated
	@ResponseBody
	@RequestMapping(value = "/generateOrderByCart", method = RequestMethod.POST)
	@Transactional(propagation=Propagation.REQUIRED) 
	public WishOrder generateOrderByCart(HttpServletRequest request, @RequestBody List<CartlistJson> cartList) throws Exception {
		Cart cart = null;
		WishOrder order = new WishOrder();
		User user = (User) request.getSession().getAttribute("user");
		cart = cartService.getResource(user.getCartId());
		BigDecimal totalPrice = BigDecimal.ZERO;
		BigDecimal originalTotalPrice = BigDecimal.ZERO;
		order.setUserId(user.getUsrId());
		order.setPrice(cart.getPrice());
		order.setOriginalPrice(cart.getOriginalPrice());
		order.setCreatedDate(new Date());
		order.setUpdatedDate(new Date());
		order.setSourceId(WishConstants.order_source_direct);
		try {
			wishOrderService.addResource(order);
			for (CartlistJson cartJson: cartList) {
				CartDetail cartDetail = cartDetailService.getResource(cartJson.getCartDetailId());
				OrderDetail orderdetail = new OrderDetail();
				orderdetail.setWishId(cartDetail.getWishId());
				orderdetail.setCount(cartDetail.getCount());
				orderdetail.setOrderId(order.getWishOrderId());
				orderdetail.setOriginalPrice(cartDetail.getOriginalPrice());
				orderdetail.setPrice(cartDetail.getPrice());
				orderdetail.setCreatedDate(new Date());
				orderdetail.setUpdatedDate(new Date());
				totalPrice = totalPrice.add(cartJson.getPrice());
				originalTotalPrice = originalTotalPrice.add(cartJson.getOriginalPrice());
				orderDetailService.addResource(orderdetail);
				cartDetailService.deleteResource(cartDetail.getCartDetailId());
			}
			order.setPrice(totalPrice);
			order.setOriginalPrice(originalTotalPrice);
			wishOrderService.updateResource(order);
		}
		catch (Exception e) {
			throw e;
		}
		return order;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Wishlist> listOfWishlists(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
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
		String condition = " recommend = 1 and   wishlist_id = " + wishList.getWishlistId();
		wishlistDetailService.deleteAllResources(condition);
		BigDecimal typePrice = BigDecimal.ZERO;
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (TaoConfig taoConfig : taoConfigs) {
			typePrice = generateWishForType(taoConfig.getWishCataCode(), wishList);
			totalPrice = totalPrice.add(typePrice);
		}
		return totalPrice;
	}

	@SuppressWarnings("unchecked")
	private BigDecimal generateWishForType(String wishCataCode, Wishlist wishList) {
		String querySQL = null;
		BigDecimal totalPrice = BigDecimal.ZERO;
		BigDecimal originalPrice = new BigDecimal(0);
		querySQL = "select p from Wish p where 1=1 and wishCataCode = '%s'";
        if (gender != null && gender.equals(1)) {
        	querySQL = querySQL + " and  (gender = 0 or gender = 1) ";
        }
        else if (gender != null && gender.equals(2)){
        	querySQL = querySQL + " and  (gender = 0 or gender = 2) ";
        }
		querySQL = String.format(querySQL, wishCataCode);
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
			detail.setSelected(0);//not picked up to pay
			detail.setWishlistId(wishList.getWishlistId());
			detail.setWishType(randomWish.getGeneralCode());
			detail.setCreateDate(new Date());
			detail.setUpdatedDate(new Date());
			detail.setSourceId(2);// change from 1 to two, in order to put them into shopping cart
			detail.setRecommend(1);//by design
			wishlistDetailService.addResource(detail);
			totalPrice = totalPrice.add(detail.getPrice() == null? BigDecimal.ZERO:detail.getPrice());
			originalPrice = originalPrice.add(randomWish.getXianenPrice() == null? BigDecimal.ZERO:randomWish.getXianenPrice());
		}
		wishList.setPrice(wishList.getPrice().add(totalPrice));
		wishList.setOriginalPirce(wishList.getOriginalPrice().add(originalPrice));
		return totalPrice;
	}


	private void generateTaoCanRule(Integer level, String birthday) {
		String sql = "select p from TaoConfig p where taoCode = '%s'";
		Integer xingzuo = star(birthday);
		String taoCode = null;
		if (level.equals(1)) {
			taoCode = "A";
		}
		else if (level.equals(2)) {
			taoCode = "B";
		}
        else if (level.equals(3)) {
        	taoCode = "C";
		}
        else {
        	taoCode = "D";
        }
		taoCode = taoCode + xingzuo.toString();
		sql = String.format(sql, taoCode);
		Query query = em.createQuery(sql);
		taoConfigs = query.getResultList();
	}
	
	private Integer star(String birthday) {  
		Integer star = 0;
		if (birthday == null || birthday.equals("")) {
			return 1;
		}
		int month = Integer.valueOf(birthday.substring(5, 7));
		int day = Integer.valueOf(birthday.substring(8, 10));
        if (month == 1 && day >= 20 || month == 2 && day <= 18) {  
         star = 1;  
        }  
        if (month == 2 && day >= 19 || month == 3 && day <= 20) {  
         star = 2;  
        }  
        if (month == 3 && day >= 21 || month == 4 && day <= 19) {  
         star = 3;  
        }  
        if (month == 4 && day >= 20 || month == 5 && day <= 20) {  
         star = 4;  
        }  
        if (month == 5 && day >= 21 || month == 6 && day <= 21) {  
         star = 5;  
        }  
        if (month == 6 && day >= 22 || month == 7 && day <= 22) {  
         star = 6;  
        }  
        if (month == 7 && day >= 23 || month == 8 && day <= 22) {  
         star = 7;  
        }  
        if (month == 8 && day >= 23 || month == 9 && day <= 22) {  
         star = 8;  
        }  
        if (month == 9 && day >= 23 || month == 10 && day <= 22) {  
         star = 9;  
        }  
        if (month == 10 && day >= 23 || month == 11 && day <= 21) {  
         star = 10;  
        }  
        if (month == 11 && day >= 22 || month == 12 && day <= 21) {  
         star = 11;  
        }  
        if (month == 12 && day >= 22 || month == 1 && day <= 19) {  
         star = 12;  
        }  
        return star;  
   }
}
