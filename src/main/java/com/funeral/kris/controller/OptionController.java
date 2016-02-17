package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import com.funeral.kris.constants.QUESTION;
import com.funeral.kris.model.Option;
import com.funeral.kris.service.OptionService;

@Controller
@RequestMapping(value = "/option")
public class OptionController {

	@Autowired
	private OptionService optionService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addOptionPage() {
		ModelAndView modelAndView = new ModelAndView("add-option-form");
		modelAndView.addObject("option", new Option());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addingOption(@ModelAttribute Option option) {

		ModelAndView modelAndView = new ModelAndView("home");
		optionService.addResource(option);

		String message = "Option was successfully added.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.GET, produces = "application/json")
	public List<Option> saveOption(HttpServletRequest request) {

		System.out.println(this.getClass()+"  saveOption....begin...");
		Option option = new Option();

		String questionId = request.getParameter("questionId");
		String imgUrl = request.getParameter("imgUrl");
		String optionDesc = request.getParameter("optionDesc");
		String optionId = request.getParameter("optionId");
		
		
		System.out.println(this.getClass() + " listOfOptions    questionId= " + questionId
		+ "imgUrl=" +imgUrl+" optionDesc= "+optionDesc+" optionId="+optionId);

		
		
		if("null".equals(optionId)||"".equals(optionId)||optionId==null||"undefined".equals(optionId)){
			
			
		}else{
			
			option.setOptionId(Long.parseLong(optionId));
		}

		option.setQuestionId(questionId);
		option.setSequence(1);
		option.setImgUrl(imgUrl);
		option.setOptionDesc(optionDesc);
		option.setCreateDate(new Date());
		option.setUpdatedDate(new Date());
		optionService.addResource(option);

		List<Option>  list=optionService.getOptionListByQuestionId(questionId);
		return list;
		
		
		
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<Option> listOfOptions(HttpServletRequest request) {

		System.out.println(this.getClass() + " listOfOptions    questionId= " + request.getParameter("questionId"));

		List<Option> options = optionService.getResources(request);

		return options;
	}
	@ResponseBody
	@RequestMapping(value = "/getOptionListByQuestionId", method = RequestMethod.GET, produces = "application/json")
	public List<Option> getOptionListByQuestionId(HttpServletRequest request) {
		
		
	String questionId=	request.getParameter("questionId");
		
	System.out.println(this.getClass() + " listOfOptions    questionId= " + questionId);

	
	List<Option>  list=optionService.getOptionListByQuestionId(questionId);
		
		return list;
	}
	@ResponseBody
	@RequestMapping(value = "/getOptionByOptionId", method = RequestMethod.GET, produces = "application/json")
	public Option getOptionByOptionId(HttpServletRequest request) {
		
		
		String optionId=	request.getParameter("optionId");
		
		System.out.println(this.getClass() + " getOptionByOptionId    optionId= " + optionId);
		
		
		Option option = optionService.getResource(Long.parseLong(optionId));
		
		
		
		return option;
	}
	@ResponseBody
	@RequestMapping(value = "/deleteOption", method = RequestMethod.GET, produces = "application/json")
	public List<Option> deleteOption(HttpServletRequest request) {
		
		
		String optionId=	request.getParameter("optionId");
		String questionId=	request.getParameter("questionId");
		
		System.out.println(this.getClass() + " deleteOption    optionId= " + optionId+" questionId="+questionId);
		optionService.deleteResource(Long.parseLong(optionId));
		
		List<Option>  list=optionService.getOptionListByQuestionId(questionId);

		return list;
		
		
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editOptionPage(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("edit-option-form");
		Option option = optionService.getResource(id);
		modelAndView.addObject("option", option);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView edditingOption(@ModelAttribute Option option, @PathVariable Integer id) {

		ModelAndView modelAndView = new ModelAndView("home");

		optionService.updateResource(option);

		String message = "Option was successfully edited.";
		modelAndView.addObject("message", message);

		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteOption(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("home");
		optionService.deleteResource(id);
		String message = "Option was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
