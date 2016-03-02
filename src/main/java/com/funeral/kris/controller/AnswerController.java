package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
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

import com.funeral.kris.init.constants.LoginConstants;

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
//		answer.setAnsListId("����");
//		answer.setAnswerDesc("������Ŷ");
		answer.setAnswerId("123");
		answer.setUserId(123);
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
	public Wishlist addingAnswer(@RequestBody List<Answer> answers, HttpServletRequest request) {
		Date a = new Date();
		Integer userId = answers.get(0).getUserId();
		Integer wishlistId = Integer.valueOf(request.getParameter("wishlistId"));
		Integer level = Integer.valueOf(request.getParameter("level"));
		String ansListId = userId +"-"+ String.valueOf(a.getTime());
		for (Answer answer : answers) {
			answer.setAnsListId(ansListId);
		    answerService.addResource(answer);
		    
		    if (answer.getOptionId() != null && answer.getOptionId()>0) {
		        generateOptionRule(String.valueOf(answer.getOptionId()));
		    }
		}
		return generateWishList(userId, ansListId, wishlistId, level);
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

	private Wishlist generateWishList(Integer usrId, String ansListId, Integer wishlistId, Integer level) {
		Wishlist wishList = new Wishlist();
		Double totalPrice = 0d;
		wishList.setAnsListId(ansListId);
		wishList.setWishlistId(wishlistId);
		wishList.setStatus(LoginConstants.WISHLISTSTATUS_FINISHED);
		wishList.setUserId(usrId);
		wishList.setPrice(0d);
		totalPrice = generateWishDetail(wishList, level);
		wishList.setPrice(totalPrice);
		wishListService.updateResource(wishList);
		return wishList;
	}

	private Double generateWishDetail(Wishlist wishlist, Integer level) {
		String condition = "wishlist_id = "+wishlist.getWishlistId();
		wishlistDetailService.deleteAllResources(condition);
		Double typePrice = 0d;
		Double totalPrice = 0d;
		List<WishType> wishTypeList = wishTypeService.getResources();
		for (WishType wishType: wishTypeList) {
			if (wishType.getLevel()<= level) {
			    typePrice = generateWishForType(wishType.getWishType(), wishlist);
			    totalPrice = totalPrice + typePrice;
			}
		}
		totalPrice = totalPrice + generateWishForAddtional(wishlist);
		return totalPrice;
	}

	@SuppressWarnings("unchecked")
	private Double generateWishForType(String wishType, Wishlist wishList) {
		String querySQL = null;
        Double totalPrice = 0d;
        BigDecimal totalOriginalPrice = BigDecimal.ZERO;
		if (wishType.equals("Cemetery")) {
		    querySQL = "select p from Cemetery p ";

		    if (optionRuleMap.containsKey(wishType)) {
				querySQL = querySQL + " AND cemeteryId in(" + optionRuleMap.get(wishType)+")";
			}
		}
		else {
			querySQL = "select p from Wish p where 1=1 and wishType = '%s'";
			querySQL = String.format(querySQL, wishType);
			if (optionRuleMap.containsKey(wishType)) {
				querySQL = querySQL + " AND wishId in(" + optionRuleMap.get(wishType)+")";
			}
		}
		Random random = new Random();
		int randomIndex = 0;

		if (wishType.equals("Cemetery")) {
			List<Cemetery> cemeterys = em.createQuery(querySQL).getResultList();
			Cemetery randomWish = null;

			if (cemeterys!=null && cemeterys.size() > 0) {
				WishlistDetail detail = new WishlistDetail();
				randomIndex = random.nextInt(cemeterys.size());
				randomWish = cemeterys.get(randomIndex);
				detail.setWishId(randomWish.getCemeteryId());
				detail.setPrice(Double.valueOf(randomWish.getPrice().toString()));
				detail.setOriginalPrice(randomWish.getOriginalPrice());
				detail.setCount(1);
				detail.setWishlistId(wishList.getWishlistId());
				detail.setWishType(wishType);
				detail.setCreateDate(new Date());
				detail.setUpdatedDate(new Date());
				wishlistDetailService.addResource(detail);
				totalPrice = totalPrice + detail.getPrice();
				totalOriginalPrice = totalOriginalPrice.add(randomWish.getOriginalPrice());
			}
		}
		else {
			List<Wish> wishs = em.createQuery(querySQL).getResultList();
			Wish randomWish = null;

			if (wishs!=null && wishs.size() > 0) {
				WishlistDetail detail = new WishlistDetail();
				randomIndex = random.nextInt(wishs.size());
				randomWish = wishs.get(randomIndex);
				detail.setWishId(randomWish.getWishId());
				detail.setPrice(Double.parseDouble(randomWish.getSellingPrice().toString()));
				detail.setOriginalPrice(randomWish.getProcurementCost());
				detail.setCount(1);
				detail.setWishlistId(wishList.getWishlistId());
				detail.setWishType(wishType);
				detail.setCreateDate(new Date());
				detail.setUpdatedDate(new Date());
				wishlistDetailService.addResource(detail);
				totalPrice = totalPrice + detail.getPrice();
				totalOriginalPrice = totalOriginalPrice.add(randomWish.getProcurementCost());
			}
		}
		wishList.setPrice(totalPrice);
		wishList.setOriginalPirce(totalOriginalPrice);
		return totalPrice;
	}

	private void generateOptionRule(String optionIdStr) {
		String sql = "select p from OptionRule p where optionId = '%s'";
		sql = String.format(sql, optionIdStr);
		@SuppressWarnings("unchecked")
		List<OptionRule> optionRuleList = em.createQuery(sql).getResultList();
		for (OptionRule optionRule: optionRuleList) {
            String ruleStr = optionRule.getRule();
            
            if (optionRule.getRuleType() == null || optionRule.getRuleType().equals("")) {
            	if (!optionRuleMap.containsKey("specialAddWish")) {
            	    optionRuleMap.put("specialAddWish",ruleStr);
            	}
            	else {
            		String combineRule = optionRuleMap.get("specialAddWish") + " , "
        			        + ruleStr;
        			optionRuleMap.put(optionRule.getRuleType(), combineRule);
            	}
            }
            else if (!optionRuleMap.containsKey(optionRule.getRuleType())) {
				optionRuleMap.put(optionRule.getRuleType(), ruleStr);
			}
			else {
				String combineRule = optionRuleMap.get(optionRule.getRuleType()) + " , "
			        + ruleStr;
				optionRuleMap.put(optionRule.getRuleType(), combineRule);
			}
		}
	}
	
	private Double generateWishForAddtional(Wishlist wishList) {
		Double totalPrice = 0d;
		BigDecimal totalOriginalPrice = BigDecimal.ZERO;
		if (optionRuleMap.containsKey("specialAddWish")) {
			
			String querySQL = null;
			querySQL = "select p from Wish p where 1=1 ";
			querySQL = querySQL + " AND wishId in(" + optionRuleMap.get("specialAddWish")+")";
			List<Wish> wishs = em.createQuery(querySQL).getResultList();
			for (Wish wish: wishs) {
				WishlistDetail detail = new WishlistDetail();
				detail.setWishId(wish.getWishId());
				detail.setPrice(wish.getSellingPrice().doubleValue());
				detail.setCount(1);
				detail.setWishlistId(wishList.getWishlistId());
				detail.setWishType(wish.getWishType());
				detail.setCreateDate(new Date());
				detail.setUpdatedDate(new Date());
				wishlistDetailService.addResource(detail);
				totalPrice = totalPrice + detail.getPrice();
				totalOriginalPrice = totalOriginalPrice.add(new BigDecimal(detail.getPrice()));
			}
		}
		wishList.setPrice(wishList.getPrice()+totalPrice);
		wishList.setOriginalPirce(wishList.getOriginalPirce().add(totalOriginalPrice));
		return totalPrice;
	}
}
