package com.funeral.kris.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.funeral.kris.model.Option;
import com.funeral.kris.model.OptionRule;
import com.funeral.kris.model.User;
import com.funeral.kris.model.Wish;
import com.funeral.kris.model.WishType;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.service.AnswerService;
import com.funeral.kris.service.OptionService;
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
	private OptionService optionService;
	@Autowired
	private EntityManager em;
	private Integer gender;
	private String wishTypeList = "";

	private Map<String, String> optionRuleMap = new HashMap<String, String>();
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addAnswerPage() {
		String querySQL ="";
		Answer answer = new Answer();
		ModelAndView modelAndView = new ModelAndView("add-answer-form");
		modelAndView.addObject("answer", new Answer());
		
		querySQL = "select p from Cemetery p where p.cemeteryDesc= '%s'";
		List<Cemetery> cemeterys = em.createQuery(querySQL).getResultList();
		answerService.addResource(answer);
		return modelAndView;
	}

	@ResponseBody 
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Wishlist addingAnswer(@RequestBody List<Answer> answers, HttpServletRequest request) {
		Date a = new Date();
		User user = (User)request.getSession().getAttribute("user");
		Integer userId = user.getUsrId();
		gender = user.getGender();
		Integer wishlistId = Integer.valueOf(request.getParameter("wishlistId"));
		Integer level = Integer.valueOf(request.getParameter("level"));
		String ansListId = userId +"-"+ String.valueOf(a.getTime());
		for (Answer answer : answers) {

		    if (answer.getOptionId() != null && answer.getOptionId()>0) {
		        generateOptionRule(answer.getOptionId(), level);
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
		BigDecimal totalPrice = BigDecimal.ZERO;
		wishList.setAnsListId(ansListId);
		wishList.setWishlistId(wishlistId);
		wishList.setStatus(LoginConstants.WISHLISTSTATUS_FINISHED);
		wishList.setUserId(usrId);
		wishList.setPrice(BigDecimal.ZERO);
		wishList.setLevel(level);
		wishList.setOriginalPirce(BigDecimal.ZERO);
		totalPrice = generateWishDetail(wishList, level);
		wishListService.updateResource(wishList);
		return wishList;
	}

	private BigDecimal generateWishDetail(Wishlist wishlist, Integer level) {
		String condition = "wishlist_id = "+wishlist.getWishlistId() + " and wish_type in("+wishTypeList+")";
		wishlistDetailService.deleteAllResources(condition);
		List<WishlistDetail> restDetails = wishlistDetailService.getResourceByWishListId(wishlist.getWishlistId());
		for (WishlistDetail detail: restDetails) {
			if (detail.getSourceId()!= null && detail.getSourceId().equals(1)) {
			wishlist.setPrice(wishlist.getPrice().add(detail.getPrice()));
			wishlist.setOriginalPirce(wishlist.getOriginalPrice().add(detail.getOriginalPrice()==null?BigDecimal.ZERO:detail.getOriginalPrice()));
			}
		}
		BigDecimal typePrice = BigDecimal.ZERO;
		BigDecimal totalPrice = BigDecimal.ZERO;
		List<WishType> wishTypeList = wishTypeService.getResources();
		for (WishType wishType: wishTypeList) {
			if (wishType.getLevel()<= level) {
			    typePrice = generateWishForType(wishType.getWishType(), wishlist);
			    totalPrice = totalPrice.add(typePrice);
			}
		}
		return totalPrice;
	}

	@SuppressWarnings("unchecked")
	private BigDecimal generateWishForType(String wishType, Wishlist wishList) {
		String querySQL = null;
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal totalOriginalPrice = BigDecimal.ZERO;
        querySQL = "select p from Wish p where 1=1 and generalCode = '%s' ";
        if (gender != null && gender.equals(1)) {
        	querySQL = querySQL + " and  (gender = 0 or gender = 1) ";
        }
        else if (gender != null && gender.equals(0)){
        	querySQL = querySQL + " and  (gender = 0 or gender = 2) ";
        }

		querySQL = String.format(querySQL, wishType);
		if (optionRuleMap.containsKey(wishType)) {
			querySQL = querySQL + optionRuleMap.get(wishType);
		}
		else {
			return BigDecimal.ZERO;
		}
		Random random = new Random();
		int randomIndex = 0;

			List<Wish> wishs = em.createQuery(querySQL).getResultList();
			Wish randomWish = null;

			if (wishs!=null && wishs.size() > 0) {
				WishlistDetail detail = new WishlistDetail();
				randomIndex = random.nextInt(wishs.size());
				randomWish = wishs.get(randomIndex);
				detail.setWishId(randomWish.getWishId());
				detail.setPrice(randomWish.getSellingPrice());
				detail.setOriginalPrice(randomWish.getXianenPrice());
				detail.setSourceId(1);
				detail.setCount(1);
				detail.setRecommend(1);
				detail.setWishlistId(wishList.getWishlistId());
				detail.setWishType(randomWish.getGeneralCode());
				detail.setWishType(wishType);
				detail.setCreateDate(new Date());
				detail.setUpdatedDate(new Date());
				wishlistDetailService.addResource(detail);
				totalPrice = totalPrice.add(detail.getPrice());
				totalOriginalPrice = totalOriginalPrice.add(randomWish.getXianenPrice());
		}
		wishList.setPrice(wishList.getPrice().add(totalPrice));
		wishList.setOriginalPirce(wishList.getOriginalPrice().add(totalOriginalPrice));
		return totalPrice;
	}

	private void generateOptionRule(Integer optionId, Integer level) {
		Option option = optionService.getResource(optionId.longValue());
		String rule = null;
		String combinedSql = "";
		if (level.equals(1)) {
			rule = option.getRule1();
		}
		else if (level.equals(2)) {
			rule = option.getRule2();
		}
		else if (level.equals(3)) {
			rule = option.getRule3();
		}
		else if (level.equals(4)) {
			rule = option.getRule4();
		}
		
		if (rule != null && rule.length() > 0) {
		String catagoryCode = rule.substring(0, rule.length() -1);
		String levelNumber = rule.substring(rule.length() -1, rule.length());
		combinedSql = " and wishLevel ='0"+levelNumber+"'";
		optionRuleMap.put(catagoryCode, combinedSql);
		if (wishTypeList!=null && wishTypeList.equals("")) {
		    wishTypeList = "'" + catagoryCode + "'";
		}
		else {
			wishTypeList = wishTypeList + "," +"'" + catagoryCode + "'";
		}
		}
	}
}
