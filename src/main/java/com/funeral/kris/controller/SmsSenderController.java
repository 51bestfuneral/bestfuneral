package com.funeral.kris.controller;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.Order;
import com.funeral.kris.model.User;
import com.funeral.kris.service.OrderService;
import com.funeral.kris.service.SmsSenderService;
import com.funeral.kris.service.SmsSenderServiceImpl;
import com.funeral.kris.service.UserService;



@Controller
@RequestMapping(value="/sms")
public class SmsSenderController {
	
	private SmsSenderService smsSenderService = new SmsSenderServiceImpl();
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/sendForPaid", method=RequestMethod.GET)
	public ModelAndView sendForPaid(int userId) throws Exception {
		ModelAndView modelAndView = new ModelAndView("home");
		User user =userService.getResource(userId);
		Map<String,String> messageInfo =  new HashMap<String,String>();
		messageInfo.put("name", user.getUserName());
		messageInfo.put("phone", user.getPhone());
		int flag=smsSenderService.sendForPaid(messageInfo);
		String message = "";
		if(flag==1){
			message= "send successful";
		}
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@RequestMapping(value="/sendForPassword", method=RequestMethod.GET)
	public ModelAndView sendForPassword(int userId,String code) throws Exception {
		ModelAndView modelAndView = new ModelAndView("home");
		User user =userService.getResource(userId);
		Map<String,String> messageInfo =  new HashMap<String,String>();
		messageInfo.put("code", code);
		messageInfo.put("username", user.getUserName());
		messageInfo.put("phone", user.getPhone());
		int flag=smsSenderService.sendForPassword(messageInfo);
		String message = "";
		if(flag==1){
			message= "send successful";
		}
		modelAndView.addObject("message", message);
		return modelAndView;
	}
}
