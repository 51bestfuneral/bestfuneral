package com.funeral.kris.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.init.constants.LoginConstants;
import com.funeral.kris.model.User;
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
		Integer checkResult = null;
		checkResult = userService.checkLogin(account, pwd, request);
System.out.println(this.getClass()+" verifyLogin   checkResult ="+checkResult+"  account="+account+" pwd="+pwd);
		return checkResult;
	}

	@ResponseBody
	@RequestMapping(value = "validateLogin", method = RequestMethod.GET, produces = "application/json")
	public User validateLogin(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);

		if (session.getAttribute(LoginConstants.LoginStatus) != null &&
				session.getAttribute(LoginConstants.LoginStatus).toString().equals(LoginConstants.login)) {
			return (User)session.getAttribute("user");
		}
		else {
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "getCurrentUser", method = RequestMethod.GET, produces = "application/json")
	public User getCurrentUser(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute(LoginConstants.LoginStatus) != null &&
				session.getAttribute(LoginConstants.LoginStatus).toString().equals(LoginConstants.login)) {
			return (User)session.getAttribute("user");
		}
		else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "logoff", method = RequestMethod.GET, produces = "application/json")
	public void logoff(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);

		if (session.getAttribute(LoginConstants.LoginStatus) != null &&
				session.getAttribute(LoginConstants.LoginStatus).toString().equals(LoginConstants.login)) {
			session.removeAttribute("user"); 
			session.removeAttribute(LoginConstants.LoginStatus);
		}
	}
}
