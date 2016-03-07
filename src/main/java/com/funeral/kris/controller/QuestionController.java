package com.funeral.kris.controller;

import java.util.ArrayList;
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

import com.funeral.kris.busModel.QuestionListJson;
import com.funeral.kris.model.Option;
import com.funeral.kris.model.Question;
import com.funeral.kris.model.User;
import com.funeral.kris.service.OptionService;
import com.funeral.kris.service.QuestionService;

@Controller
@RequestMapping(value="/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private OptionService optionService;
	
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
	public List<QuestionListJson> listOfQuestions(HttpServletRequest request) {
		boolean genderControll = false;
		Integer gender = null;
		if (request.getSession().getAttribute("user") != null) {
		    User user = (User)request.getSession().getAttribute("user");
		    if (user != null) {
		    	gender = user.getGender();
		    	if (gender == null) {
		    		genderControll = false;
		    	}
		    	else {
		    	    genderControll = true;
		    	}
		    }
		}
		
		if(gender==null){
			
			gender=1;
		}
		List<Question> questions = questionService.getResources(request);
        List<QuestionListJson> questionJsonList = new ArrayList<QuestionListJson>();
        for (Question question : questions) {
        	if (genderControll && question.getGender() != null && question.getGender() > 0) {
        		if (gender.equals(0) && question.getGender().equals(2)){
        			continue;
        		}
        		else if (gender.equals(1) && question.getGender().equals(1)) {
        			continue;
        		}
        	}
        	QuestionListJson questionJson = new QuestionListJson();
            List<Option> options = optionService.getOptionListByQuestionId(question.getQuestionId().toString());
            questionJson.setQuestionId(question.getQuestionId().toString());
            questionJson.setQuestionContent(question.getQuestionContent());
            questionJson.setOptionList(options);
            questionJson.setQuestionTitle(question.getQuestionTitle());
            questionJson.setOptionCount(options.size());
            questionJsonList.add(questionJson);
        }
		return questionJsonList;
	}

	@ResponseBody
	@RequestMapping(value="/findNext",method=RequestMethod.GET, produces = "application/json")
	public List<Question> findNextQuestion(HttpServletRequest request) {
		List<Question> questions = questionService.findNextResource(request);

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
