package com.funeral.kris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.Question;
import com.funeral.kris.service.QuestionService;

@Controller
@RequestMapping(value="/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addQuestionPage() {
		ModelAndView modelAndView = new ModelAndView("add-question-form");
		modelAndView.addObject("question", new Question());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingQuestion(@ModelAttribute Question question) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		questionService.addResource(question);
		
		String message = "Question was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<Question> listOfQuestions() {
		ModelAndView modelAndView = new ModelAndView("list-of-questions");

		List<Question> questions = questionService.getResources();
		modelAndView.addObject("questions", questions);

		return questions;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editQuestionPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-question-form");
		Question question = questionService.getResource(id);
		modelAndView.addObject("question",question);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingQuestion(@ModelAttribute Question question, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		questionService.updateResource(question);
		
		String message = "Question was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteQuestion(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		questionService.deleteResource(id);
		String message = "Question was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
