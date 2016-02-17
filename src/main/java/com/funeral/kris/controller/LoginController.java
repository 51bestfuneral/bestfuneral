package com.funeral.kris.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.init.constants.LoginConstants;
import com.funeral.kris.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "verifyLogin", method = RequestMethod.GET, produces = "application/json")
	public Integer verifyLogin(HttpServletRequest request) throws Exception {

		String account =request.getParameter("userName");
		String pwd =request.getParameter("password");
		Integer checkResult = userService.checkLogin(account, pwd);
		
		HttpSession  session=request.getSession();
		
		session.setAttribute("userName", account);
		
		Random random = new Random();
		int number=random.nextInt(1000000);
		
		session.setAttribute("sessionId", System.currentTimeMillis()+"_"+number);
		
		
			if(checkResult.intValue()==LoginConstants.validatePass){
			session.setAttribute(LoginConstants.LoginStatus, LoginConstants.login);
			}else{
				session.setAttribute(LoginConstants.LoginStatus, LoginConstants.OffLine);	
			}

		return checkResult;

	}

}
