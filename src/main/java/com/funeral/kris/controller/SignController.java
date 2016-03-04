package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.funeral.kris.model.Cemetery;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wishlist;
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
	private EntityManager em;

	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void sign(HttpServletRequest request, @RequestBody User user) throws Exception {
		String account = user.getUserName();
		
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
		createWishListForUser(user);
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
	
	private void createWishListForUser(User user) {
		Date sysDate = new Date();
		Wishlist wishlist = new Wishlist();
		wishlist.setUserId(user.getUsrId());
        wishlist.setCreateDate(sysDate);
        wishlist.setUpdatedDate(sysDate);
        wishlist.setPrice(BigDecimal.ZERO);
        wishlist.setStatus(LoginConstants.WISHLISTSTATUS_INIT);
        wishlistService.addResource(wishlist);
	}
}
