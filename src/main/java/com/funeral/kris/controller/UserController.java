package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.bean.CemeteryBean;
import com.funeral.kris.init.constants.LoginConstants;
import com.funeral.kris.model.Answer;
import com.funeral.kris.model.Cemetery;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wish;
import com.funeral.kris.service.UserService;
import com.funeral.kris.util.MD5;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addUserPage() {
		ModelAndView modelAndView = new ModelAndView("/pages/mainPage.html");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public void addingUser(@RequestBody User user) {

		userService.addResource(user);
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public User listOfUsers(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
		if (session!=null &&session.getAttribute(LoginConstants.LoginStatus) !=null && 
				session.getAttribute(LoginConstants.LoginStatus).toString().equals(LoginConstants.login)) {
			User user = (User)session.getAttribute("user");
			return user;
		}
		else {
		    return null;
		}
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-user-form");
		User user = userService.getResource(id);
		modelAndView.addObject("user",user);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Integer edditingUser(@RequestBody User user, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session!=null &&session.getAttribute(LoginConstants.LoginStatus) !=null && 
				session.getAttribute(LoginConstants.LoginStatus).toString().equals(LoginConstants.login)) {
			User userInsession = (User)session.getAttribute("user");
			if (userInsession.getUsrId().equals(user.getUsrId())) {
				userService.updateResource(user);
				session.setAttribute("user", user);
			}
			else {
				return 1;
			}
		}
		else {
		    return 1;
		}
		return 0;
	}

	@ResponseBody
	@RequestMapping(value="/editCemeteryRequest", method=RequestMethod.POST)
	public Integer edditingUserRequest(@RequestBody User user, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session!=null &&session.getAttribute(LoginConstants.LoginStatus) !=null && 
				session.getAttribute(LoginConstants.LoginStatus).toString().equals(LoginConstants.login)) {
			User userInsession = (User)session.getAttribute("user");
			userInsession.setRequestCemetery(user.getRequestCemetery());
			userInsession.setRequestDate(user.getRequestDate());
			userInsession.setRequestPhone(user.getRequestPhone());
			userService.updateResource(userInsession);
			session.setAttribute("user", userInsession);
		}
		else {
		    return 1;
		}
		return 0;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		userService.deleteResource(id);
		String message = "User was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/verifyUser", method=RequestMethod.GET)
	public List<User> verifyUser(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String pwd = MD5.GetMD5Code(request.getParameter("pwd"));
		String userType = request.getParameter("userType");
		return userService.verifyUser(userName, pwd, userType);
	}

	@ResponseBody
	@RequestMapping(value = "/listUsersByCemeteryRequest", method = RequestMethod.GET, produces = "application/json")
	public List<User> listUsersByCemeteryRequest() {
		return userService.getResourcesByRequestCemetery();
	}
	
	@ResponseBody
	@RequestMapping(value="/verifySession", method=RequestMethod.GET)
	public Integer validateUserSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user")!=null){
			return 1;
		}
		return 0;
	}
}
