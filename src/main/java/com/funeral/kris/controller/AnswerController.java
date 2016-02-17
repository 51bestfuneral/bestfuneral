package com.funeral.kris.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.funeral.kris.model.Cemetery;
import com.funeral.kris.model.OptionRule;
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

	private Map<String, String> optionRuleMap = new HashMap<String, String>();
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addAnswerPage() {
		String querySQL ="";
		Answer answer = new Answer();
		answer.setAnsListId("����");
		answer.setAnswerDesc("������Ŷ");
		answer.setAnswerId("123");
		answer.setUserId("123");
		ModelAndView modelAndView = new ModelAndView("add-answer-form");
		modelAndView.addObject("answer", new Answer());
		
		querySQL = "select p from Cemetery p where p.cemeteryDesc= '%s'";
		querySQL = String.format(querySQL, "����");
		List<Cemetery> cemeterys = em.createQuery(querySQL).getResultList();
		answerService.addResource(answer);
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
		    
		    if (answer.getOptionId() != null && answer.getOptionId()>0) {
		        generateOptionRule(String.valueOf(answer.getOptionId()));
		    }
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

	@SuppressWarnings("unchecked")
	private void generateWishForType(String wishType, String wishListId) {
		String querySQL = null;

		if (wishType.equals("Cemetery")) {
		    querySQL = "select p from Cemetery p ";
		}
		else {
			querySQL = "select p from Wish p where 1=1 and wishType = '%s'";
			querySQL = String.format(querySQL, wishType);
		}
		
		if (optionRuleMap.containsKey(wishType)) {
			querySQL = querySQL + " AND " + optionRuleMap.get(wishType);
		}
		Random random = new Random();
		WishlistDetail detail = new WishlistDetail();
		int randomIndex = 0;

		if (wishType.equals("Cemetery")) {
			List<Cemetery> cemeterys = em.createQuery(querySQL).getResultList();
			Cemetery randomWish = null;

			if (cemeterys!=null && cemeterys.size() > 0) {
				randomIndex = random.nextInt(cemeterys.size());
				randomWish = cemeterys.get(randomIndex);
				detail.setWishId(randomWish.getCemeteryId().toString());
				detail.setPrice(Double.valueOf(randomWish.getPrice().toString()));
				detail.setCount(1);
				detail.setWishlistId(wishListId);
				detail.setWishType(wishType);
				wishlistDetailService.addResource(detail);
			}
		}
		else {
			List<Wish> wishs = em.createQuery(querySQL).getResultList();
			Wish randomWish = null;

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

	private void generateOptionRule(String optionIdStr) {
		String sql = "select p from OptionRule p where optionId = '%s'";
		sql = String.format(sql, optionIdStr);
		@SuppressWarnings("unchecked")
		List<OptionRule> optionRuleList = em.createQuery(sql).getResultList();
		for (OptionRule optionRule: optionRuleList) {

			if (optionRuleMap.containsKey(optionRule.getRuleType())) {
				optionRuleMap.put(optionRule.getRuleType(), optionRule.getRule());
			}
			else {
				String combineRule = optionRuleMap.get(optionRule.getRuleType()) + " AND "
			        + optionRule.getRule();
				optionRuleMap.put(optionRule.getRuleType(), combineRule);
			}
		}
	}
}
