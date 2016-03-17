package com.funeral.kris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.init.constants.LoginConstants;
import com.funeral.kris.model.User;
import com.funeral.kris.service.UserService;
import com.funeral.kris.util.MD5;

@Controller
@RequestMapping(value = "/sign")

public class SignController {
	
	@Autowired
	private UserService userService;
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void sign(@RequestBody User user) throws Exception {
		String account = user.getUserName();

		System.out.println("   sign ......account="+account+" getPassword="+user.getPassword()+" user="+user);
		
		
		if (account.contains("@")) {

			user.setEmail(account);
		} else {

			user.setPhone(account);
		}
		user.setPassword(MD5.GetMD5Code(user.getPassword()));
		user.setStatus(LoginConstants.accountValid);
		user.setUserType(LoginConstants.userTypeInidCustomer);
		user.setInvalidLoginTimes(0);
		userService.addResource(user);
		
	}
	
}
