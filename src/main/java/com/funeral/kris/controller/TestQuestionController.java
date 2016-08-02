package com.funeral.kris.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.funeral.kris.model.TFQuestion;
import com.funeral.kris.service.FQuestionService;

public class TestQuestionController implements Controller {

	@Autowired
	private FQuestionService fQuestionService;

	@ResponseBody
	@RequestMapping(value = "/getQuestionById{id}", method = RequestMethod.GET, produces = "application/json")
	public TFQuestion getQuestionById(Long id) {

		System.out.println(" questionId =" + id);

		if (id == null || "null".equals(id)) {

			return new TFQuestion();
		}

		TFQuestion tFQuestion = fQuestionService.getResource(id);

		return tFQuestion;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = (String) request.getParameter("id");

		System.out.println(this.getClass() + " id=" + id);
		ModelAndView modelAndView = new ModelAndView("/pages/html/customizatonPages/costCalculator");

		return modelAndView;
	}

}
