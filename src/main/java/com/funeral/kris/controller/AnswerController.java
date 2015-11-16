package com.funeral.kris.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.Answer;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.WishType;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.service.AnswerService;
import com.funeral.kris.service.WishTypeService;
import com.funeral.kris.service.WishlistDetailService;
import com.funeral.kris.service.WishlistService;

@Controller
@RequestMapping(value="/answer")
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	@Autowired
	private WishlistService wishListService;
	@Autowired
	private WishTypeService wishTypeService;
	@Autowired
	private WishlistDetailService wishlistDetailService;
	@Autowired
	private EntityManager em;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addAnswerPage() {
		ModelAndView modelAndView = new ModelAndView("add-answer-form");
		modelAndView.addObject("answer", new Answer());
		return modelAndView;
	}

	@ResponseBody 
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public void addingAnswer(@RequestBody List<Answer> answers) {
		Date a = new Date();
		String userId = answers.get(0).getUserId();
		String ansListId = userId +"-"+ String.valueOf(a.getTime());
		for (Answer answer : answers) {
			answer.setAnsListId(ansListId);
		    answerService.addResource(answer);
		}
		generateWishList(userId, ansListId);
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<Answer> listOfAnswers() {
		ModelAndView modelAndView = new ModelAndView("list-of-answers");

		List<Answer> answers = answerService.getResources();
		modelAndView.addObject("answers", answers);

		return answers;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editAnswerPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-answer-form");
		Answer answer = answerService.getResource(id);
		modelAndView.addObject("answer",answer);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingAnswer(@ModelAttribute Answer answer, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		answerService.updateResource(answer);
		
		String message = "Answer was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteAnswer(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		answerService.deleteResource(id);
		String message = "Answer was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	private void generateWishList(String usrId, String ansListId) {
		Wishlist wishList = new Wishlist();
		wishList.setAnsListId(ansListId);
		wishList.setWishlistId(ansListId);
		wishList.setStatus("I");
		wishList.setUserId(usrId);
		wishList.setPrice(0d);
		wishListService.addResource(wishList);
		generateWishDetail(ansListId);
	}
	
	private void generateWishDetail(String wishListId) {
		List<WishType> wishTypeList = wishTypeService.getResources();
		for (WishType wishType: wishTypeList) {
			generateWishForType(wishType.getWishType(), wishListId);
		}
	}
	
	private void generateWishForType(String wishType, String wishListId) {
		String querySQL = "select p from wishs p where wishType = '%s'";
		Random random = new Random();
		WishlistDetail detail = new WishlistDetail();
		Wish randomWish = null;
		int randomIndex = 0;
		querySQL = String.format(querySQL, wishType);
		List<Wish> wishs = em.createQuery(querySQL).getResultList();

		if (wishs!=null && wishs.size() > 0) {
			randomIndex = random.nextInt(wishs.size());
			randomWish = wishs.get(randomIndex);
			detail.setWishId(randomWish.getWishId());
			detail.setPrice(randomWish.getPrice());
			detail.setCount(1);
			detail.setWishlistId(wishListId);
			detail.setWishType(wishType);
			wishlistDetailService.addResource(detail);
		}
	}
}
