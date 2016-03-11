package com.funeral.kris.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.Message;
import com.funeral.kris.model.MessageUser;
import com.funeral.kris.model.Wish;
import com.funeral.kris.service.MessageService;

@Controller
@RequestMapping(value="/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addMessagePage() {
		ModelAndView modelAndView = new ModelAndView("add-message-form");
		modelAndView.addObject("message", new Message());
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public int addingMessage(@RequestBody Message message) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		if (message.getMessageId() != null && !message.getMessageId().equals(0)) {
			messageService.updateResource(message);
		}
		else {
			messageService.addResource(message);
		}

		String alertMessage = "Message was successfully added.";
		modelAndView.addObject("message", alertMessage);
		
		return message.getMessageId();
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<Message> listOfMessages(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("list-of-messages");

		List<Message> messages = messageService.getResources(request);
		modelAndView.addObject("messages", messages);

		return messages;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editMessagePage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-message-form");
		Message message = messageService.getResource(id);
		modelAndView.addObject("message",message);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingMessage(@ModelAttribute Message message, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		messageService.updateResource(message);
		
		String alertMessage = "Message was successfully edited.";
		modelAndView.addObject("message", alertMessage);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteMessage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		messageService.deleteResource(id);
		String message = "Message was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
