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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.bean.ShoppingCart;
import com.funeral.kris.busModel.CartlistJson;
import com.funeral.kris.busModel.ExpressBean;
import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.init.AppContext;
import com.funeral.kris.model.Cart;
import com.funeral.kris.model.CartDetail;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.OrderDetail;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.WishOrder;
import com.funeral.kris.service.CartDetailService;
import com.funeral.kris.service.CartService;
import com.funeral.kris.service.ExpressInfoService;
import com.funeral.kris.service.OrderDetailService;
import com.funeral.kris.service.UserService;
import com.funeral.kris.service.WishOrderService;
import com.funeral.kris.service.WishService;
import com.funeral.kris.service.WishlistDetailService;
import com.funeral.kris.service.WishlistService;

@Controller
@RequestMapping(value = "/shoppingCartController")
public class ShoppingCartController {
	public static String express_method_saved_in_niannian_title = "暂存在念念 ";
	public static String express_method_saved_in_niannian_description = "(我们会暂时替您保管，在需要的时候提供给怿)";
	
	
	
	public static String express_method_standard_title = "标准 ";
	public static String express_method_standard_description = "(免费, 2-3日送达)";
	
	
	
	public static String express_method_express_title = "快递 ";
	public static String express_method_express_description  = "(1日送达，20块钱快递费用)";
	
	
	public static String province_SH="上海";
	public static String province_JS="江苏省";
	public static String province_ZJ="浙江省";

	
	@Autowired
	private WishOrderService wishOrderService;
	@Autowired
	private OrderDetailService orderDetailService;
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
	private ExpressInfoService expressInfoService;

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
			if (detail.getSelected() == null || detail.getSelected().intValue() != 1) {
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
		cartDetail.setSelectedPrice(wish.getSellingPrice());
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
		
		if(wishsMap==null){
		this.initialWishMap();
		}
		
		Wish wish=wishsMap.get(cartDetail.getWishId());
		
		cartDetail.setSelectedPrice(wish.getSellingPrice().multiply(new BigDecimal(cartDetail.getCount())));
		
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

		
		if(wishsMap==null){
			this.initialWishMap();
			}
			
			Wish wish=wishsMap.get(cartDetail.getWishId());
			
			cartDetail.setSelectedPrice(wish.getSellingPrice().multiply(new BigDecimal(cartDetail.getCount())));
		
		if (cartDetail.getCount().intValue() == 0) {

			cartDetailService.deleteResource(cartDetail.getCartDetailId());

		} else {

			cartDetailService.updateResource(cartDetail);

		}

		Cart cart = this.getCalculatedCart(cartDetail.getCartId());

		cartService.updateResource(cart);
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/getShoppingCartForPayMentConfirm", method = RequestMethod.GET)
	@Transactional(propagation = Propagation.REQUIRED)
	public ShoppingCart getShoppingCartForPayMentConfirm(HttpServletRequest request) throws Exception {

		String wishOrderId = request.getParameter("wishOrderId");

		ShoppingCart shoppingCart = new ShoppingCart();

		ExpressInfo info = expressInfoService.getExpressInfoByWishOrderId(Integer.parseInt(wishOrderId));

		shoppingCart.setExpressFee(info.getExpressFee());

		WishOrder wishOrder = wishOrderService.getResource(Integer.parseInt(wishOrderId));

		

		List<OrderDetail> orderDetailList = orderDetailService.getResourcesByWishOrderId(Integer.parseInt(wishOrderId));

		if(wishsMap==null){
			this.initialWishMap();
		}
		
		BigDecimal grossFee=BigDecimal.ZERO;
		BigDecimal netFee=BigDecimal.ZERO;
		
		
		
		if(orderDetailList!=null&&orderDetailList.size()>0){
			
			Iterator iterator=	orderDetailList.iterator();
			while(iterator.hasNext()){
				OrderDetail  orderDetail=	(OrderDetail) iterator.next();
				
				orderDetail.setWishName(wishsMap.get(orderDetail.getWishId()).getWishName());
				grossFee=grossFee.add(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getCount())));
			}
			
		}
		
		
		shoppingCart.setGrossFee(grossFee);

		shoppingCart.setNetFee(shoppingCart.getExpressFee().add(grossFee));
		
		shoppingCart.setOrderDetailList(orderDetailList);
		
		
		
		ExpressBean bean = new ExpressBean();

		bean.setExpressMethod(info.getDeliveryMethod());

		if (info.getDeliveryMethod().intValue() == 1) {
			bean.setExpressTitle(this.express_method_saved_in_niannian_title);
			bean.setExpressDescription(this.express_method_saved_in_niannian_description);
		} else if (info.getDeliveryMethod().intValue() == 2) {

			bean.setExpressTitle(this.express_method_standard_title);
			bean.setExpressDescription(this.express_method_standard_description);
			
		
		} else {

			bean.setExpressTitle(this.express_method_express_title);
			bean.setExpressDescription(this.express_method_express_description);
		}

		
		if(info.getProvince().equals("1")){
			
			bean.setProvince(this.province_JS);
		}else if(info.getProvince().equals("2")){
			bean.setProvince(this.province_SH);
		}else{
			bean.setProvince(this.province_ZJ);
		}
		bean.setExpressInfo(info);
		
		shoppingCart.setExpressBean(bean);

		return shoppingCart;

	}
	
	@ResponseBody
	@RequestMapping(value = "/generateOrderByCart", method = RequestMethod.GET)
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer generateOrderByCart(HttpServletRequest request) throws Exception {
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

		List<CartDetail> cartList = cartDetailService.getSelectedCartDetailsByCartId(cart.getCartId());

		try {
			wishOrderService.addResource(order);
			for (CartDetail cartDetail : cartList) {
				// CartDetail cartDetail =
				// cartDetailService.getResource(cartDetail.getCartDetailId());
				OrderDetail orderdetail = new OrderDetail();
				orderdetail.setWishId(cartDetail.getWishId());
				orderdetail.setCount(cartDetail.getCount());
				orderdetail.setOrderId(order.getWishOrderId());
				orderdetail.setOriginalPrice(cartDetail.getOriginalPrice());
				orderdetail.setPrice(cartDetail.getPrice());
				orderdetail.setCreatedDate(new Date());
				orderdetail.setUpdatedDate(new Date());
				totalPrice = totalPrice.add(cartDetail.getPrice().multiply(new BigDecimal(cartDetail.getCount())));
				originalTotalPrice = originalTotalPrice.add(cartDetail.getOriginalPrice().multiply(new BigDecimal(cartDetail.getCount())));
				orderDetailService.addResource(orderdetail);
				cartDetailService.deleteResource(cartDetail.getCartDetailId());
			}
			order.setPrice(totalPrice);
			order.setOriginalPrice(originalTotalPrice);
			wishOrderService.updateResource(order);
		} catch (Exception e) {
			throw e;
		}
		return order.getWishOrderId();
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

		List<CartlistJson> cartlistJsonList=new ArrayList<CartlistJson>();
		
		Integer allSelected = 0;
		Integer count = 0;
		BigDecimal orderTotalCost = BigDecimal.ZERO;
		List<Integer> selectedCartDetailIdList = new ArrayList<Integer>();
		Iterator iterator = cartDetails.iterator();
		if(wishsMap==null){
		this.initialWishMap();
		}
		while (iterator.hasNext()) {
			CartDetail detail = (CartDetail) iterator.next();
			
			CartlistJson cartlistJson=new CartlistJson();
			
			cartlistJson.setCartDetailId(detail.getCartDetailId());
			cartlistJson.setAmount(detail.getCount());
			cartlistJson.setCartId(cartId);
			cartlistJson.setImageUrl(wishsMap.get(detail.getWishId()).getImgUrl());
			cartlistJson.setPrice(detail.getPrice());
			cartlistJson.setSelected(detail.getSelected());
			cartlistJson.setWishId(detail.getWishId());
			cartlistJson.setWishName(wishsMap.get(detail.getWishId()).getWishName());
			cartlistJson.setSelectedPrice(detail.getSelectedPrice());
			cartlistJsonList.add(cartlistJson);
			
			

			if (detail.getSelected() != null && detail.getSelected().intValue() == 1) {
				count = count + detail.getCount();
				selectedCartDetailIdList.add(detail.getCartDetailId());

				orderTotalCost = orderTotalCost.add(detail.getPrice().multiply(new BigDecimal(detail.getCount())));
			}
		}

		if (cartDetailService.isAllSelected(cartId)) {
			allSelected = 1;
		}
		shoppingCart.setCartlistJsonList(cartlistJsonList);
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

		User user = AppContext.getUser();

		System.out.println(this.getClass() + "  listCart user= " + user);

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
