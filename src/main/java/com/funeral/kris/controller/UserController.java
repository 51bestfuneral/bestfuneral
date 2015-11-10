package com.funeral.kris.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.User;
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
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingUser(@ModelAttribute User user) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		userService.addResource(user);
		
		String message = "User was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<User> listOfUsers() {
		ModelAndView modelAndView = new ModelAndView("list-of-users");

		List<User> users = userService.getResources();
		modelAndView.addObject("users", users);

		return users;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-user-form");
		User user = userService.getResource(id);
		modelAndView.addObject("user",user);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingUser(@ModelAttribute User user, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		userService.updateResource(user);
		
		String message = "User was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
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

}
