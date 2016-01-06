package com.funeral.kris.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.TFQuestion;
import com.funeral.kris.service.FQuestionService;

@Controller
@RequestMapping(value = "/fQuestion")
public class FQuestionController {

	@Autowired
	private FQuestionService fQuestionService;

	@ResponseBody
	@RequestMapping(value = "/getQuestionById{id}", method = RequestMethod.GET, produces = "application/json")
	public TFQuestion getQuestionById(Long id) {
		ModelAndView modelAndView = new ModelAndView("list-of-tFQuestion");

		System.out.println(" questionId =" + id);

		if (id == null || "null".equals(id)) {

			return new TFQuestion();
		}

		TFQuestion tFQuestion = fQuestionService.getResource(id);

		modelAndView.addObject("tFQuestion", tFQuestion);

		return tFQuestion;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)

	public void add(@RequestBody TFQuestion tFQuestion) {

		System.out.println("tFQuestion=" + tFQuestion.getTitle() + " content =" + tFQuestion.getQuestionContent());

		fQuestionService.addResource(tFQuestion);

	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public List<TFQuestion> listAll() {

		ModelAndView modelAndView = new ModelAndView("list-of-questionList");

		List<TFQuestion> questionList = fQuestionService.getResources();

		System.out.println("   questionList.size()  =" + questionList.size());

		modelAndView.addObject("questionList", questionList);

		return questionList;
	}
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void edit(@RequestBody TFQuestion tFQuestion) {
		System.out.println("----------tFQuestion=" + tFQuestion.getTitle() + " content =" + tFQuestion.getQuestionContent());

		fQuestionService.updateResource(tFQuestion);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable long id) {

		ModelAndView modelAndView = new ModelAndView("home");

		fQuestionService.deleteResource(id);

		String message = "question was successfully deleted!";
		modelAndView.addObject("message", message);
		return modelAndView;

	}

	public Object verify(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
