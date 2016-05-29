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
import com.funeral.kris.busModel.CartlistJson;
import com.funeral.kris.busModel.WishListJson;
import com.funeral.kris.busModel.WishOrderDetailJson;
import com.funeral.kris.busModel.WishOrderJson;
import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.model.Cart;
import com.funeral.kris.model.CartDetail;
import com.funeral.kris.model.OrderDetail;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.WishOrder;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.service.CartDetailService;
import com.funeral.kris.service.CartService;
import com.funeral.kris.service.OrderDetailService;
import com.funeral.kris.service.UserService;
import com.funeral.kris.service.WishOrderService;
import com.funeral.kris.service.WishService;
import com.funeral.kris.service.WishlistDetailService;
import com.funeral.kris.service.WishlistService;

@Controller
@RequestMapping(value = "/wishlistDetail")
public class WishlistDetailController {

	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Autowired
	private CartDetailService cartDetailService;
	@Autowired
	private WishOrderService wishOrderService;
	@Autowired
	private OrderDetailService orderDetailService;
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
	public List<WishListJson> addingWishlistDetail(
			@RequestBody List<Wish> wishs, HttpServletRequest request) {

		List<WishListJson> successList = new ArrayList<WishListJson>();
		Integer wishlistId = Integer
				.valueOf(request.getParameter("wishlistId"));
		Wishlist wishlist = wishlistService.getResource(wishlistId);
		wishlist.setPrice(BigDecimal.ZERO);
		wishlist.setOriginalPirce(BigDecimal.ZERO);
		wishlistDetailService.deleteAllResources("wishlist_id=" + wishlistId
				+ " and recommend= 1");
		Date sysDate = new Date();
		if (wishsMap == null) {
			initialWishMap();
		}

		for (Wish wish : wishs) {
			WishlistDetail wishlistDetail = new WishlistDetail();
			WishListJson detailJson = new WishListJson();
			wish = wishsMap.get(wish.getWishId());
			wishlistDetail.setWishId(wish.getWishId());
			wishlistDetail.setCount(1);
			wishlistDetail.setPrice(wish.getSellingPrice());
			wishlistDetail.setOriginalPrice(wish.getXianenPrice());
			wishlistDetail.setSourceId(1);
			wishlistDetail.setRecommend(1);
			wishlistDetail.setWishType(wish.getGeneralCode());
			wishlistDetail.setWishlistId(wishlistId);
			wishlistDetail.setCreateDate(sysDate);
			wishlistDetail.setUpdatedDate(sysDate);
			wishlistDetailService.addResource(wishlistDetail);
			detailJson.setAmount(wishlistDetail.getCount());
			detailJson.setImageUrl(wish.getImgUrl());
			detailJson.setOriginalPrice(wish.getXianenPrice());
			detailJson.setPrice(wish.getSellingPrice());
			detailJson.setWishId(wish.getWishId());
			detailJson.setWishName(wish.getWishName());
			detailJson.setWishlistId(wishlistId);
			successList.add(detailJson);
			wishlist.setPrice(wishlist.getPrice().add(wish.getSellingPrice()));
			wishlist.setOriginalPirce(wishlist.getOriginalPrice().add(
					wish.getXianenPrice() == null ? BigDecimal.ZERO : wish
							.getXianenPrice()));
		}
		wishlistService.updateResource(wishlist);
		return successList;
	}

	@ResponseBody
	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public void buy(HttpServletRequest request) {

	}

	@ResponseBody
	@RequestMapping(value = "/addSingleToWish", method = RequestMethod.POST)
	public List<WishlistDetail> addingWishlistSingle(HttpServletRequest request) {

		List<WishlistDetail> successList = new ArrayList<WishlistDetail>();
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return null;
		}

		Integer wishlistId = user.getWishlistId();
		Integer wishId = Integer.valueOf(request.getParameter("wishId"));
		Wish wish = wishService.getResource(wishId);
		Wishlist wishlist = wishlistService.getResource(wishlistId);

		Date sysDate = new Date();
		if (wishsMap == null) {
			initialWishMap();
		}

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
		wishlistDetailService.addResource(wishlistDetail);
		wishlist.setPrice(wishlist.getPrice());
		successList.add(wishlistDetail);

		wishlist = this.getCalculatedWishList(wishlistId);

		wishlistService.updateResource(wishlist);

		return successList;
	}

	@ResponseBody
	@RequestMapping(value = "/removeSingleFromWish", method = RequestMethod.DELETE)
	public Integer removeWishlistSingle(HttpServletRequest request) {

		String wishlistDetailId = request.getParameter("wishlistDetailId");
		wishlistDetailService.deleteResource(Integer.valueOf(wishlistDetailId));
		return 0;
	}

	@ResponseBody
	@RequestMapping(value = "/saveCart", method = RequestMethod.POST)
	public int saveCartList(@RequestBody List<CartlistJson> cartDetailList,
			HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		List<CartDetail> currentCartDetails = wishlistDetailService
				.getResourceByCartId(user.getCartId());
		Map<Integer, CartDetail> currentCartDetailMap = convertToMap(currentCartDetails);
		Map<Integer, CartlistJson> changedCartDetailMap = convertToMapFromJson(cartDetailList);
		processCartSave(currentCartDetails, currentCartDetailMap,
				changedCartDetailMap);
		return 0;
	}

	private Map<Integer, CartDetail> convertToMap(
			List<CartDetail> cartDetailList) {
		Map<Integer, CartDetail> cartMap = new HashMap<Integer, CartDetail>();
		for (CartDetail carDetail : cartDetailList) {
			cartMap.put(carDetail.getCartDetailId(), carDetail);
		}
		return cartMap;
	}

	private Map<Integer, CartlistJson> convertToMapFromJson(
			List<CartlistJson> cartDetailList) {
		Map<Integer, CartlistJson> cartMap = new HashMap<Integer, CartlistJson>();
		for (CartlistJson carDetail : cartDetailList) {
			cartMap.put(carDetail.getCartDetailId(), carDetail);
		}
		return cartMap;
	}

	private void processCartSave(List<CartDetail> currentCartDetailList,
			Map<Integer, CartDetail> currentCartDetailMap,
			Map<Integer, CartlistJson> changedCartDetailMap) {
		for (CartDetail cartDetail : currentCartDetailList) {
			if (changedCartDetailMap.containsKey(cartDetail.getCartDetailId())) {
				if (!cartDetail.getCount().equals(
						changedCartDetailMap.get(cartDetail.getCartDetailId())
								.getAmount())) {
					cartDetail.setCount(changedCartDetailMap.get(
							cartDetail.getCartDetailId()).getAmount());
					cartDetailService.updateResource(cartDetail);
				}
			} else {
				cartDetailService.deleteResource(cartDetail.getCartDetailId());
			}
		}
	}

	public int checkExist(int wishId, int wishlistId) {

		List<WishlistDetail> list = wishlistDetailService
				.getResourceByWishListId(wishlistId);

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
					&& wishlistDetail.getSelected() != null
					&& wishlistDetail.getSelected().intValue() == 1) {
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
				wishListJson.setWishDetailId(wishlistDetail
						.getWishlistDetailId());
				wishListJson.setWishlistId(wishlistDetail.getWishlistId());
				wishListJson
						.setSelectedPrice(wishlistDetail.getSelectedPrice());
				wishlistJsons.add(wishListJson);
			}
		}
		return wishlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/getDirectWishListForPaymnetConfirm", method = RequestMethod.GET, produces = "application/json")
	public List<WishListJson> getDirectWishListForPaymnetConfirm(
			HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		String wishlistId = request.getParameter("wishlistId");

		List<WishListJson> wishlistJsons = new ArrayList<WishListJson>();
		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getSelectedWishlistDetailByWishListId(Integer
						.parseInt(wishlistId));

		for (WishlistDetail wishlistDetail : wishlistDetails) {
			if (wishlistDetail.getSourceId() != null
					&& wishlistDetail.getSourceId().intValue() == WishConstants.wish_source_direct
					&& wishlistDetail.getSelected() != null
					&& wishlistDetail.getSelected().intValue() == 1) {
				WishListJson wishListJson = new WishListJson();
				Integer wishId = wishlistDetail.getWishId();
				Wish wish = wishsMap.get(wishId);
				wishListJson.setWishId(wishId);
				wishListJson.setAmount(wishlistDetail.getCount());
				wishListJson.setWishName(wish.getWishName());
				wishListJson.setWishDesc(wish.getWishDesc());
				wishListJson.setImageUrl(wish.getImgUrl());
				wishListJson.setPrice(new BigDecimal(wish.getSellingPrice()
						.doubleValue() * wishlistDetail.getCount()));
				// wishListJson.setOriginalPrice(wish.getProcurementCost());
				wishListJson.setWishDetailId(wishlistDetail
						.getWishlistDetailId());
				wishListJson.setWishlistId(wishlistDetail.getWishlistId());
				wishListJson
						.setSelectedPrice(wishlistDetail.getSelectedPrice());
				wishlistJsons.add(wishListJson);
			}
		}
		return wishlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/getDirectWishListForShoppingCart", method = RequestMethod.GET, produces = "application/json")
	public List<CartlistJson> getDirectWishListForShoppingCart(
			HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		User user = (User) request.getSession().getAttribute("user");
		Integer cartId = user.getCartId();

		List<CartlistJson> cartlistJsons = new ArrayList<CartlistJson>();
		List<CartDetail> cartDetails = wishlistDetailService
				.getResourceByCartId(cartId);
		for (CartDetail cartDetail : cartDetails) {
			CartlistJson cartListJson = new CartlistJson();
			Integer wishId = cartDetail.getWishId();
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
			cartListJson.setSumPrice(wish.getSellingPrice().multiply(
					new BigDecimal(cartDetail.getCount())));
			cartlistJsons.add(cartListJson);
		}
		return cartlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/getShoppingCartCount", method = RequestMethod.GET, produces = "application/json")
	public Integer getShoppingCartCount(HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		User user = (User) request.getSession().getAttribute("user");
		Integer cartId = user.getCartId();

		List<CartlistJson> cartlistJsons = new ArrayList<CartlistJson>();
		List<CartDetail> cartDetails = wishlistDetailService
				.getResourceByCartId(cartId);
		return cartDetails.size();
	}

	@ResponseBody
	@RequestMapping(value = "/getSetWishList", method = RequestMethod.GET, produces = "application/json")
	public List<WishListJson> getSetWishList(HttpServletRequest request) {
		if (wishsMap == null) {
			initialWishMap();
		}
		List<WishListJson> wishlistJsons = new ArrayList<WishListJson>();
		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getResources(request);
		for (WishlistDetail wishlistDetail : wishlistDetails) {
			if (wishlistDetail.getRecommend() != null
					&& wishlistDetail.getRecommend().intValue() == WishConstants.wish_source_set) {
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
				wishListJson.setWishDetailId(wishlistDetail
						.getWishlistDetailId());
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
		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getResources(request);
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
	@RequestMapping(value = "/getAllCartList", method = RequestMethod.GET, produces = "application/json")
	public List<CartlistJson> getAllCartList(HttpServletRequest request) {

		if (wishsMap == null) {
			initialWishMap();
		}
		List<CartlistJson> cartlistJsons = new ArrayList<CartlistJson>();
		List<CartDetail> cartDetails = cartDetailService.getResources(request);
		for (CartDetail cartDetail : cartDetails) {

			CartlistJson cartListJson = new CartlistJson();
			Integer wishId = cartDetail.getWishId();
			Wish wish = wishsMap.get(wishId);
			cartListJson.setWishId(wishId);
			cartListJson.setAmount(cartDetail.getCount());
			cartListJson.setWishName(wish.getWishName());
			cartListJson.setWishDesc(wish.getWishDesc());
			cartListJson.setImageUrl(wish.getImgUrl());
			cartListJson.setPrice(wish.getSellingPrice());
			cartListJson.setOriginalPrice(wish.getXianenPrice());
			cartListJson.setCartDetailId(cartDetail.getCartDetailId());
			cartListJson.setCartId(cartDetail.getCartId());
			cartlistJsons.add(cartListJson);

		}

		return cartlistJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/listWishOrder", method = RequestMethod.GET)
	public List<WishOrderJson> listWishOrder(HttpServletRequest request) {
		List<WishOrderJson> orderJsons = new ArrayList<WishOrderJson>();
		User user = (User) request.getSession().getAttribute("user");
		List<WishOrder> wishOrderList = wishOrderService
				.getResourceByUserId(user.getUsrId());

		if (wishsMap == null) {
			initialWishMap();
		}
		for (WishOrder wishOrder : wishOrderList) {
			WishOrderJson orderJson = new WishOrderJson();
			List<WishOrderDetailJson> detailJsons = new ArrayList<WishOrderDetailJson>();
			orderJson.setUserId(user.getUsrId());
			orderJson.setWishOrderId(wishOrder.getWishOrderId());
			orderJson.setStatusId(wishOrder.getStatusId());

			System.out.println(this.getClass() + " listWishOrder  getSatusId="
					+ wishOrder.getStatusId());

			if (wishOrder.getStatusId().intValue() == WishConstants.wishlist_status_init
					|| wishOrder.getStatusId().intValue() == WishConstants.wishorder_status_pendingPay) {
				orderJson
						.setStatusDisception(WishConstants.wishorder_status_disc_pendingPay);

			} else if (wishOrder.getStatusId().intValue() == WishConstants.wishorder_status_closed) {

				orderJson
						.setStatusDisception(WishConstants.wishorder_status_disc_closed);
				orderJson.setStyle("visibility: hidden;");
			} else if (wishOrder.getStatusId().intValue() == WishConstants.wishorder_status_rejected) {

				orderJson
						.setStatusDisception(WishConstants.wishorder_status_disc_rejected);
				orderJson.setStyle("visibility: hidden;");

			} else if (wishOrder.getStatusId().intValue() == WishConstants.wishorder_status_paied) {
				orderJson
						.setStatusDisception(WishConstants.wishorder_status_disc_paied);
				orderJson.setStyle("visibility: hidden;");

			}

			List<OrderDetail> wishOrderDetailList = wishlistDetailService
					.getWishOrderDetailByOrderId(wishOrder.getWishOrderId());
			BigDecimal totalPrice = BigDecimal.ZERO;
			for (OrderDetail orderDetail : wishOrderDetailList) {
				WishOrderDetailJson detailJson = new WishOrderDetailJson();
				Wish wish = wishsMap.get(orderDetail.getWishId());
				BigDecimal sumPrice = wish.getSellingPrice().multiply(
						new BigDecimal(orderDetail.getCount()));
				totalPrice = totalPrice.add(sumPrice);
				detailJson.setCount(orderDetail.getCount());
				detailJson.setOrderDetailId(orderDetail.getOrderDetailId());
				detailJson.setOriginalPrice(wish.getXianenPrice());
				detailJson.setPrice(wish.getSellingPrice());
				detailJson.setSumPirce(sumPrice);
				detailJson.setWishDesc(wish.getRemark());
				detailJson.setWishName(wish.getWishName());
				detailJson.setWishId(orderDetail.getWishId());
				detailJson.setWishOrderId(orderDetail.getOrderDetailId());
				detailJson.setImgUrl(wish.getImgUrl());
				detailJsons.add(detailJson);
			}
			orderJson.setTotalPrice(wishOrder.getPrice());
			orderJson.setDetailJson(detailJsons);
			orderJsons.add(orderJson);
		}
		return orderJsons;
	}

	@ResponseBody
	@RequestMapping(value = "/removeWishOrder", method = RequestMethod.DELETE)
	public Integer removeWishOrder(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		wishOrderService.deleteResource(Integer.valueOf(orderId));
		orderDetailService.deleteResourceByOrderId(Integer.valueOf(orderId));
		return 0;
	}

	@ResponseBody
	@RequestMapping(value = "/loadShoppingCart", method = RequestMethod.GET, produces = "application/json")
	public ShoppingCart loadShoppingCart(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		String wishlistId = request.getParameter("wishlistId");
		Wishlist wishlist = wishlistService.getResource(Integer
				.parseInt(wishlistId));
		ShoppingCart shoppingCart = new ShoppingCart();
		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getResourceByWishListId(wishlist.getWishlistId());
		List<WishlistDetail> directDetailsList = wishlistDetailService
				.getDirectWishlistDetailByWishListId(wishlist.getWishlistId());
		Integer allSelected = 0;
		boolean selected = false;
		Integer count = 0;
		BigDecimal orderTotalCost = BigDecimal.ZERO;
		List<Integer> selectedWishDetailIdList = new ArrayList<Integer>();
		Iterator iterator = wishlistDetails.iterator();
		while (iterator.hasNext()) {
			WishlistDetail detail = (WishlistDetail) iterator.next();

			if (detail.getSelected() != null
					&& detail.getSelected().intValue() == 1
					&& detail.getSourceId() != null
					&& detail.getSourceId().intValue() == WishConstants.wish_source_direct) {
				count = count + detail.getCount();
				selectedWishDetailIdList.add(detail.getWishlistDetailId());

				orderTotalCost = orderTotalCost.add(detail.getPrice().multiply(
						new BigDecimal(detail.getCount())));
			}
		}

		if (selectedWishDetailIdList.size() == directDetailsList.size()
				&& selectedWishDetailIdList.size() > 0) {
			allSelected = 1;
		}
		shoppingCart.setSelectedCartDetailIdList(selectedWishDetailIdList);
		shoppingCart.setCount(count);
		shoppingCart.setGrossFee(orderTotalCost);
		shoppingCart.setUserId(user.getUsrId());
		shoppingCart.setAllSelected(allSelected);
		return shoppingCart;
	}

	@ResponseBody
	@RequestMapping(value = "/selectOneItem", method = RequestMethod.GET, produces = "application/json")
	public ShoppingCart selectOneItem(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("user");

		String wishlistDetailId = request.getParameter("wishDetaillistId");
		String wishlistId = request.getParameter("wishlistId");

		// String action = request.getParameter("action");

		BigDecimal price = BigDecimal.ZERO;
		BigDecimal originalPrice = BigDecimal.ZERO;

		ShoppingCart shoppingCart = new ShoppingCart();

		Wishlist wishlist = wishlistService.getResource(Integer
				.parseInt(wishlistId));
		WishlistDetail wishlistDetail = wishlistDetailService
				.getResource(Integer.parseInt(wishlistDetailId));

		if (wishlistDetail.getSelected() != null
				&& wishlistDetail.getSelected().intValue() == 0) {
			wishlistDetail.setSelected(1);

			if (wishlistDetail.getPrice() != null) {
				price = price.add(wishlist.getPrice().add(
						wishlistDetail.getPrice().multiply(
								new BigDecimal(wishlistDetail.getCount()))));
			}

			if (wishlist.getOriginalPrice() != null
					&& wishlistDetail.getOriginalPrice() != null) {
				originalPrice = originalPrice.add(wishlist.getOriginalPrice()
						.add(wishlistDetail.getOriginalPrice().multiply(
								new BigDecimal(wishlistDetail.getCount()))));

			}
			wishlist.setPrice(price);
			wishlist.setOriginalPirce(originalPrice);
		} else {
			if (wishlistDetail.getPrice() != null) {
				wishlist.setPrice(wishlist.getPrice().subtract(
						wishlistDetail.getPrice().multiply(
								new BigDecimal(wishlistDetail.getCount()))));
			}
			if (wishlistDetail.getOriginalPrice() != null) {
				wishlist.setOriginalPirce(wishlist.getOriginalPrice().subtract(
						wishlistDetail.getOriginalPrice().multiply(
								new BigDecimal(wishlistDetail.getCount()))));
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

			if (detail.getSelected() != null
					&& detail.getSelected().intValue() == 1) {
				count = count + detail.getCount();
				selectedWishDetailIdList.add(detail.getWishlistDetailId());
				if (detail.getPrice() != null) {
					orderTotalCost = orderTotalCost.add(detail.getPrice()
							.multiply(new BigDecimal(detail.getCount())));
				}
			}
		}

		if (selectedWishDetailIdList.size() == wishlistDetails.size()
				&& selectedWishDetailIdList.size() > 0) {
			allSelected = 1;
		}

		shoppingCart.setSelectedCartDetailIdList(selectedWishDetailIdList);

		shoppingCart.setCount(count);

		shoppingCart.setGrossFee(orderTotalCost);

		shoppingCart.setUserId(user.getUsrId());

		shoppingCart.setAllSelected(allSelected);

		return shoppingCart;

	}

	@ResponseBody
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET, produces = "application/json")
	public ShoppingCart selectAll(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("user");

		String wishlistId = request.getParameter("wishlistId");

		System.out.println(" wishlistId======== " + wishlistId);

		// Wishlist wishlist =
		// wishlistService.getResource(Integer.parseInt(wishlistId));

		int allSelected = 1;
		List<WishlistDetail> wishDetails = wishlistDetailService
				.getResourceByWishListId(Integer.parseInt(wishlistId));

		Iterator itera = wishDetails.iterator();

		while (itera.hasNext()) {

			WishlistDetail wishlistDetail = (WishlistDetail) itera.next();

			if (wishlistDetail.getSelected() != null
					&& wishlistDetail.getSelected().intValue() == 0) {
				allSelected = 0;
				break;
			}

		}
		// String action = request.getParameter("action");

		ShoppingCart shoppingCart = new ShoppingCart();

		System.out.println(" allSelected======== " + allSelected);

		if (allSelected == 0) {
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

		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getResourceByWishListId(Integer.parseInt(wishlistId));

		List<WishlistDetail> directDetailsList = wishlistDetailService
				.getDirectWishlistDetailByWishListId(Integer
						.parseInt(wishlistId));

		boolean selected = false;

		BigDecimal orderTotalCost = BigDecimal.ZERO;

		Iterator iterator = wishlistDetails.iterator();

		while (iterator.hasNext()) {
			WishlistDetail detail = (WishlistDetail) iterator.next();

			if (detail.getSelected() != null
					&& detail.getSelected().intValue() == 1
					&& detail.getSourceId() != null
					&& detail.getSourceId().intValue() == WishConstants.wish_source_direct) {
				count = count + detail.getCount();
				selectedWishDetailIdList.add(detail.getWishlistDetailId());

				orderTotalCost = orderTotalCost.add(detail.getPrice().multiply(
						new BigDecimal(detail.getCount())));
			}
		}

		if (selectedWishDetailIdList.size() == directDetailsList.size()
				&& selectedWishDetailIdList.size() > 0) {
			allSelected = 1;
		}

		shoppingCart.setSelectedCartDetailIdList(selectedWishDetailIdList);

		shoppingCart.setCount(count);

		shoppingCart.setGrossFee(orderTotalCost);

		shoppingCart.setUserId(user.getUsrId());

		shoppingCart.setAllSelected(allSelected);

		return shoppingCart;

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

		WishlistDetail wishlistDetail = wishlistDetailService
				.getResource(Integer.parseInt(wishDetailId));

		int count = wishlistDetail.getCount() + 1;
		wishlistDetail.setCount(count);
		wishlistDetailService.updateResource(wishlistDetail);

		Wishlist wishlist = this.getCalculatedWishList(wishlistDetail
				.getWishlistId());

		wishlistService.updateResource(wishlist);

		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request) {

		String wishDetailId = request.getParameter("wishDetailId");

		WishlistDetail wishlistDetail = wishlistDetailService
				.getResource(Integer.parseInt(wishDetailId));

		wishlistDetailService.deleteResource(Integer.parseInt(wishDetailId));

		Wishlist wishlist = this.getCalculatedWishList(wishlistDetail
				.getWishlistId());

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

		List<WishlistDetail> wishlistDetails = wishlistDetailService
				.getSelectedWishlistDetailByWishListId(wishListId);

		BigDecimal price = BigDecimal.ZERO;
		BigDecimal originalPirce = BigDecimal.ZERO;

		if (wishlistDetails != null && wishlistDetails.size() > 0) {

			Iterator iterator = wishlistDetails.iterator();

			while (iterator.hasNext()) {

				WishlistDetail detail = (WishlistDetail) iterator.next();

				if (detail.getPrice() != null) {

					price = price.add(detail.getPrice().multiply(
							new BigDecimal(detail.getCount())));
				}

				if (detail.getOriginalPrice() != null) {

					originalPirce = originalPirce.add(detail.getOriginalPrice()
							.multiply(new BigDecimal(detail.getCount())));

				}

			}

		}

		wishlist.setPrice(price);

		wishlist.setOriginalPirce(originalPirce);

		return wishlist;

	}

}
