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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.bean.ShoppingCart;
import com.funeral.kris.busModel.CartlistJson;
import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.init.AppContext;
import com.funeral.kris.model.Cart;
import com.funeral.kris.model.CartDetail;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wish;
import com.funeral.kris.service.CartDetailService;
import com.funeral.kris.service.CartService;
import com.funeral.kris.service.UserService;
import com.funeral.kris.service.WishService;
import com.funeral.kris.service.WishlistDetailService;
import com.funeral.kris.service.WishlistService;

@Controller
@RequestMapping(value = "/shoppingCartController")
public class ShoppingCartController {

	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private UserService userService;
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Autowired
	private CartService cartService;
	@Autowired
	private CartDetailService cartDetailService;
	@Autowired
	private WishService wishService;
	public Map<Integer, Wish> wishsMap = null;

	private void initialWishMap() {
		HttpServletRequest fakeRequest = null;
		List<Wish> wishs = wishService.getResources(fakeRequest);
		wishsMap = new HashMap<Integer, Wish>();
		for (Wish wish : wishs) {
			wishsMap.put(wish.getWishId(), wish);
		}

	}

	@ResponseBody
	@RequestMapping(value = "/getCart", method = RequestMethod.GET, produces = "application/json")
	public int getCart(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		return user.getCartId();
	}

	@ResponseBody
	@RequestMapping(value = "/getAllGoodsForShoppingCart", method = RequestMethod.GET, produces = "application/json")
	public List<CartlistJson> getAllGoodsForShoppingCart(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		Integer cartId = user.getCartId();

		List<CartlistJson> cartlistJsons = new ArrayList<CartlistJson>();
		List<CartDetail> cartDetails = cartDetailService.getResourceByCartId(cartId);
		for (CartDetail cartDetail : cartDetails) {

			CartlistJson cartListJson = new CartlistJson();
			Integer wishId = cartDetail.getWishId();

			if (wishsMap == null) {
				initialWishMap();
			}

			Wish wish = wishsMap.get(wishId);
			cartListJson.setWishId(wishId);
			cartListJson.setAmount(cartDetail.getCount());
			cartListJson.setWishName(wish.getWishName());
			cartListJson.setWishDesc(wish.getWishDesc());
			cartListJson.setImageUrl(wish.getImgUrl());
			cartListJson.setPrice(wish.getSellingPrice());
			cartListJson.setOriginalPrice(wish.getProcurementCost());
			cartListJson.setCartDetailId(cartDetail.getCartDetailId());
			cartListJson.setCartId(cartId);
			cartListJson.setSelected(cartDetail.getSelected());
			cartListJson.setSelectedPrice(wish.getSellingPrice().multiply(new BigDecimal(cartDetail.getCount())));
			cartlistJsons.add(cartListJson);
		}
		return cartlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/getDirectWishListForPaymnetConfirm", method = RequestMethod.GET, produces = "application/json")
	public List<CartlistJson> getCartDetailsForPaymnetConfirm(HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		String cartId = request.getParameter("cartId");

		List<CartlistJson> cartlistJsons = new ArrayList<CartlistJson>();
		List<CartDetail> cartDetails = cartDetailService.getResourceByCartId(Integer.parseInt(cartId));

		for (CartDetail cartDetail : cartDetails) {
			if (cartDetail.getSelected() != null && cartDetail.getSelected().intValue() == 1) {
				CartlistJson cartlistJson = new CartlistJson();
				Integer wishId = cartDetail.getWishId();
				Wish wish = wishsMap.get(wishId);

				cartlistJson.setCartId(Integer.parseInt(cartId));
				cartlistJson.setCartDetailId(cartDetail.getCartDetailId());
				cartlistJson.setOriginalPrice(
						cartDetail.getOriginalPrice().multiply(new BigDecimal(cartDetail.getCount())));
				cartlistJson.setPrice(cartDetail.getPrice().multiply(new BigDecimal(cartDetail.getCount())));
				cartlistJson.setSelected(cartDetail.getSelected());
				cartlistJson.setWishName(wish.getWishName());
				cartlistJson.setWishId(wishId);
				cartlistJsons.add(cartlistJson);
			}
		}
		return cartlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/selectOneItem", method = RequestMethod.GET, produces = "application/json")
	public void selectOneItem(HttpServletRequest request) {

		String cartDetailId = request.getParameter("cartDetailId");

		CartDetail detail = cartDetailService.getResource(Integer.parseInt(cartDetailId));

		if (detail != null) {
			if (detail.getSelected()==null||detail.getSelected().intValue() != 1) {
				detail.setSelected(1);
			} else {
				detail.setSelected(0);

			}

		}
		cartDetailService.updateResource(detail);
	}

	@ResponseBody
	@RequestMapping(value = "/addSingleToCart", method = RequestMethod.POST)
	public List<CartDetail> addingCartSingle(HttpServletRequest request) {

		List<CartDetail> successList = new ArrayList<CartDetail>();
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return null;
		}

		Integer cartId = user.getCartId();
		Cart cart = cartService.getResource(cartId);
		Integer wishId = Integer.valueOf(request.getParameter("wishId"));
		Wish wish = wishService.getResource(wishId);

		Date sysDate = new Date();
		if (wishsMap == null) {
			initialWishMap();
		}

		CartDetail cartDetail = new CartDetail();
		wish = wishsMap.get(wish.getWishId());
		cartDetail.setWishId(wish.getWishId());
		cartDetail.setCount(1);
		cartDetail.setPrice(wish.getSellingPrice());
		cartDetail.setOriginalPrice(wish.getXianenPrice());
		cartDetail.setWishType(wish.getGeneralCode());
		cartDetail.setSourceId(WishConstants.wish_source_direct);
		cartDetail.setCartId(cartId);
		cartDetail.setCreatedDate(sysDate);
		cartDetail.setUpdatedDate(sysDate);
		cartDetail.setSelected(0);
		cartDetailService.addResource(cartDetail);
		cart.setPrice(cart.getPrice().add(wish.getSellingPrice()));
		cart.setOriginalPrice(cart.getOriginalPrice().add(wish.getXianenPrice()));
		successList.add(cartDetail);
		cartService.updateResource(cart);

		return successList;
	}

	@ResponseBody
	@RequestMapping(value = "/removeSingleFromCart", method = RequestMethod.DELETE)
	public Integer removeCartSingle(HttpServletRequest request) {
        String cartDetailId = request.getParameter("cartDetailId");
        cartDetailService.deleteResource(Integer.valueOf(cartDetailId));
        return 0;
	}

	@ResponseBody
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET, produces = "application/json")
	public ShoppingCart selectAll(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("user");

		Integer cartId = user.getCartId();
		// String cartId = request.getParameter("cartId");

		System.out.println(" cartId======== " + cartId);

		// Wishlist wishlist =
		// wishlistService.getResource(Integer.parseInt(wishlistId));
		ShoppingCart shoppingCart = new ShoppingCart();
		if (cartDetailService.isAllSelected(cartId)) {
			shoppingCart.setAllSelected(0);
		} else {
			shoppingCart.setAllSelected(1);
		}
		Integer count = 0;

		List<Integer> selectedCartDetailIdList = new ArrayList<Integer>();

		List<CartDetail> cartDetailList = cartDetailService.getResourceByCartId(cartId);

		Iterator iter = cartDetailList.iterator();

		while (iter.hasNext()) {

			CartDetail cartDetail = (CartDetail) iter.next();

			if (shoppingCart.getAllSelected().intValue() == 1) {
				cartDetail.setSelected(1);
				
				cartDetail.setSelectedPrice(cartDetail.getPrice().multiply(new BigDecimal(cartDetail.getCount())));
				cartDetailService.updateResource(cartDetail);
				// price = BigDecimal.ZERO;
				// originalPrice = BigDecimal.ZERO;
				selectedCartDetailIdList.add(cartDetail.getCartDetailId());

			}

			else {
				cartDetail.setSelected(0);
				cartDetailService.updateResource(cartDetail);
				// price=price.add(wishDetail.getPrice().multiply(new
				// BigDecimal(wishDetail.getCount())));
				// originalPrice=originalPrice.add(wishDetail.getOriginalPrice().multiply(new
				// BigDecimal(wishDetail.getCount())));
			}

		}

		List<CartDetail> cartDetailLists = cartDetailService.getResourceByCartId(cartId);

		boolean selected = false;

		BigDecimal orderTotalCost = BigDecimal.ZERO;

		Iterator iterator = cartDetailLists.iterator();

		while (iterator.hasNext()) {
			CartDetail cartDetail = (CartDetail) iterator.next();

			if (cartDetail.getSelected() != null && cartDetail.getSelected().intValue() == 1) {
				count = count + cartDetail.getCount();
				selectedCartDetailIdList.add(cartDetail.getCartDetailId());

				orderTotalCost = orderTotalCost
						.add(cartDetail.getPrice().multiply(new BigDecimal(cartDetail.getCount())));
			}
		}

		shoppingCart.setSelectedCartDetailIdList(selectedCartDetailIdList);

		shoppingCart.setCount(count);

		shoppingCart.setGrossFee(orderTotalCost);

		shoppingCart.setUserId(user.getUsrId());

		return shoppingCart;

	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request) {
		String cartDetailId = request.getParameter("cartDetailId");

		CartDetail cartDetail = cartDetailService.getResource(Integer.parseInt(cartDetailId));

		cartDetailService.deleteResource(cartDetail.getCartDetailId());

		Cart cart = this.getCalculatedCart(cartDetail.getCartId());

		cartService.updateResource(cart);
	}

	@ResponseBody
	@RequestMapping(value = "/oneMore", method = RequestMethod.GET)
	public ModelAndView oneMore(HttpServletRequest request) {

		String cartDetailId = request.getParameter("cartDetailId");

		CartDetail cartDetail = cartDetailService.getResource(Integer.parseInt(cartDetailId));

		int count = cartDetail.getCount() + 1;
		cartDetail.setCount(count);
		cartDetailService.updateResource(cartDetail);

		Cart cart = this.getCalculatedCart(cartDetail.getCartId());

		cartService.updateResource(cart);

		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/subtraction", method = RequestMethod.GET)
	public ModelAndView subtraction(HttpServletRequest request) {

		String cartDetailId = request.getParameter("cartDetailId");

		CartDetail cartDetail = cartDetailService.getResource(Integer.parseInt(cartDetailId));

		int count = cartDetail.getCount() - 1;
		cartDetail.setCount(count);

		if (cartDetail.getCount().intValue() == 0) {

			cartDetailService.deleteResource(cartDetail.getCartDetailId());

		} else {

			cartDetailService.updateResource(cartDetail);

		}

		Cart cart = this.getCalculatedCart(cartDetail.getCartId());

		cartService.updateResource(cart);
		return null;
	}

	public Cart getCalculatedCart(int cartId) {

		Cart cart = cartService.getResource(cartId);

		List<CartDetail> cartDetailList = cartDetailService.getResourceByCartId(cart.getCartId());

		BigDecimal price = BigDecimal.ZERO;
		BigDecimal originalPirce = BigDecimal.ZERO;

		if (cartDetailList != null && cartDetailList.size() > 0) {

			Iterator iterator = cartDetailList.iterator();

			while (iterator.hasNext()) {

				CartDetail detail = (CartDetail) iterator.next();

				if (detail.getPrice() != null) {

					price = price.add(detail.getPrice().multiply(new BigDecimal(detail.getCount())));
				}

				if (detail.getOriginalPrice() != null) {

					originalPirce = originalPirce
							.add(detail.getOriginalPrice().multiply(new BigDecimal(detail.getCount())));

				}

			}

		}

		cart.setPrice(price);

		cart.setOriginalPrice(originalPirce);

		return cart;

	}

	@ResponseBody
	@RequestMapping(value = "/loadShoppingCart", method = RequestMethod.GET, produces = "application/json")
	public ShoppingCart loadShoppingCart(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		Integer cartId = user.getCartId();
		ShoppingCart shoppingCart = new ShoppingCart();
		List<CartDetail> cartDetails = cartDetailService.getResourceByCartId(cartId);

		Integer allSelected = 0;
		Integer count = 0;
		BigDecimal orderTotalCost = BigDecimal.ZERO;
		List<Integer> selectedCartDetailIdList = new ArrayList<Integer>();
		Iterator iterator = cartDetails.iterator();
		while (iterator.hasNext()) {
			CartDetail detail = (CartDetail) iterator.next();

			if (detail.getSelected() != null && detail.getSelected().intValue() == 1) {
				count = count + detail.getCount();
				selectedCartDetailIdList.add(detail.getCartDetailId());

				orderTotalCost = orderTotalCost.add(detail.getPrice().multiply(new BigDecimal(detail.getCount())));
			}
		}

		if (cartDetailService.isAllSelected(cartId)) {
			allSelected = 1;
		}
		shoppingCart.setSelectedCartDetailIdList(selectedCartDetailIdList);
		shoppingCart.setCount(count);
		shoppingCart.setGrossFee(orderTotalCost);
		shoppingCart.setUserId(user.getUsrId());
		shoppingCart.setAllSelected(allSelected);
		return shoppingCart;
	}

	@ResponseBody
	@RequestMapping(value = "listCountOfCart", method = RequestMethod.GET, produces = "application/json")
	public Integer listCountOfCart(HttpServletRequest request) throws Exception {

		int count = 0;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		List<CartDetail> cartDetails = cartDetailService.getResourceByCartId(user.getCartId());

		if (cartDetails != null) {
			count = cartDetails.size();
		}

		return count;
	}

	@ResponseBody
	@RequestMapping(value = "/listCart", method = RequestMethod.GET, produces = "application/json")
	public Cart listOfCart(HttpServletRequest request) throws Exception {

		User user=AppContext.getUser();

		System.out.println(this.getClass()+"  listCart user= "+user);

		
		HttpSession session = request.getSession(false);
		 user = (User) session.getAttribute("user");
		if (user != null) {
			Integer cartId = user.getCartId();
			Cart cart = cartService.getResource(cartId);
			return cart;
		} else {
			return null;
		}
	}

}
