package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.init.constants.LoginConstants;
import com.funeral.kris.model.Cart;
import com.funeral.kris.model.Cemetery;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.service.CartService;
import com.funeral.kris.service.SmsSenderService;
import com.funeral.kris.service.UserService;
import com.funeral.kris.service.WishlistService;
import com.funeral.kris.util.MD5;

@Controller
@RequestMapping(value = "/sign")

public class SignController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private CartService cartService;
	@Autowired
	private EntityManager em;

	@Autowired
	private SmsSenderService smsSenderService;
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void sign(HttpServletRequest request, @RequestBody User user) throws Exception {
		String account = user.getUserName();
		Integer wishlistId = 0;
		Integer cartId = 0;
		
		if (account.contains("@")) {

			user.setEmail(account);
		} else {

			user.setPhone(account);
		}
		user.setPassword(user.getPassword());
		user.setStatus(LoginConstants.accountValid);
		user.setUserType(LoginConstants.userTypeInidCustomer);
		user.setInvalidLoginTimes(0);
		userService.addResource(user);
		System.out.println("begin to create wish and cart-----getUsrId--"+user.getUsrId()+" getUserName="+user.getUserName());

		wishlistId = createWishListForUser(user);
		
		
		cartId = createShoppingCartForUser(user);
		
		System.out.println("end to create wish and cart----");

		
		user.setWishlistId(wishlistId);
		user.setCartId(cartId);
		userService.updateResource(user);
		HttpSession session = request.getSession(true);
		session.setAttribute(LoginConstants.LoginStatus, LoginConstants.login);
		session.setAttribute("user", user);	
	}

	@ResponseBody
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public Map<String, Integer> checkValid(HttpServletRequest request) throws Exception {
		String account = request.getParameter("userName").toString();
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap.put("resultCode", LoginConstants.valid);

		if (account.contains("@")) {

			if (!checkEmailUnique(account)) {
				resultMap.put("resultCode", LoginConstants.emailInvalid);
			}
		} else {
			if (!checkPhoneUnique(account)) {
				resultMap.put("resultCode", LoginConstants.phoneInvalid);
			}
		}
		return resultMap;
	}

	private boolean checkEmailUnique(String phone) {
		String querySQL = "select u from User u where u.email= '%s'";
		querySQL = String.format(querySQL, phone);
		List<User> users = em.createQuery(querySQL).getResultList();

		if (users != null && users.size() > 0) {
			return false;
		}
		return true;
	}

	private boolean checkPhoneUnique(String phone) {
		String querySQL = "select u from User u where u.phone= '%s'";
		querySQL = String.format(querySQL, phone);
		List<User> users = em.createQuery(querySQL).getResultList();

		if (users != null && users.size() > 0) {
			return false;
		}
		return true;
	}
	
	private Integer createWishListForUser(User user) {
		Date sysDate = new Date();
		Wishlist wishlist = new Wishlist();
		wishlist.setUserId(user.getUsrId());
        wishlist.setCreateDate(sysDate);
        wishlist.setUpdatedDate(sysDate);
        wishlist.setPrice(BigDecimal.ZERO);
        wishlist.setStatus(LoginConstants.WISHLISTSTATUS_INIT);
        wishlistService.addResource(wishlist);
        return wishlist.getWishlistId();
	}

	private Integer createShoppingCartForUser(User user) {
		Date sysDate = new Date();
		Cart cart = new Cart();
		cart.setUserId(user.getUsrId());
		cart.setPrice(BigDecimal.ZERO);
		cart.setOriginalPrice(BigDecimal.ZERO);
		cart.setCreatedDate(sysDate);
		cart.setUpdatedDate(sysDate);
		cartService.addResource(cart);
		return cart.getCartId();
	}

	@ResponseBody
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	public void updatePassword(HttpServletRequest request, @RequestBody User user) throws Exception {
		String account = user.getUserName();
		User userAccount =userService.getByAccount(account);
		userAccount.setPassword(user.getPassword());
		userAccount.setStatus(LoginConstants.accountValid);
		userAccount.setUserType(LoginConstants.userTypeInidCustomer);
		userAccount.setInvalidLoginTimes(0);
		userService.updateResource(userAccount);
	}
	
	@ResponseBody
	@RequestMapping(value = "send", method = RequestMethod.POST)
	public String send(HttpServletRequest request, @RequestBody User user) throws Exception{
		String account = user.getUserName();
		User userAccount =userService.getByAccount(account);
		if(userAccount==null){
			return null;
		}else{
			user.setUsrId(userAccount.getUsrId());
		}
		Random random = new Random();
		int i = 0;
		while(i<100000){
			i = random.nextInt(999999);
		}
		String vaildCode = i+"";
		String phone = userAccount.getPhone();
		Map<String,String> messageInfo = new HashMap<String,String>();
		messageInfo.put("phone", phone);
		messageInfo.put("code", vaildCode);
		messageInfo.put("product", "念念");
		int flag = smsSenderService.sendForPassword(messageInfo);
		if(flag ==1){
			return vaildCode;
		}
		return null;
	}
}
