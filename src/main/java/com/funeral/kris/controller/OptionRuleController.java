package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.busModel.OptionRuleConfig;
import com.funeral.kris.model.Answer;
import com.funeral.kris.model.Option;
import com.funeral.kris.model.OptionRule;
import com.funeral.kris.model.Question;
import com.funeral.kris.service.OptionRuleService;
import com.funeral.kris.service.OptionService;
import com.funeral.kris.service.QuestionService;

@Controller
@RequestMapping(value="/optionRule")
public class OptionRuleController {
	
	@Autowired
	private OptionRuleService optionRuleService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private OptionService optionService;
	@Autowired
	private EntityManager em;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addOptionRulePage() {
		ModelAndView modelAndView = new ModelAndView("add-optionRule-form");
		modelAndView.addObject("optionRule", new OptionRule());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingOptionRule(@ModelAttribute OptionRule optionRule) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		optionRuleService.addResource(optionRule);
		
		String message = "OptionRule was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<OptionRule> listOfOptionRules() {
		ModelAndView modelAndView = new ModelAndView("list-of-optionRules");

		List<OptionRule> optionRules = optionRuleService.getResources();
		modelAndView.addObject("optionRules", optionRules);

		return optionRules;
	}

	@ResponseBody
	@RequestMapping(value="/listConfig",method=RequestMethod.GET, produces = "application/json")
	public List<OptionRuleConfig> listOfOptionRulesConfig() {
		List<OptionRule> optionRules = optionRuleService.getResources();
		Option option = null;
		Question question = null;
		List<OptionRuleConfig> optionRuleConfigs = new ArrayList<OptionRuleConfig>();

		for (OptionRule optionRule: optionRules) {
			OptionRuleConfig optionRuleConfig = new OptionRuleConfig();
            Integer optionId = Integer.valueOf(optionRule.getOptionId());
            option = optionService.getResource(Long.valueOf(optionId));
            question = questionService.getResource(Integer.valueOf(option.getQuestionId()));
            optionRuleConfig.setQuestionId(question.getQuestionId().toString());
            optionRuleConfig.setQuestionContent(question.getQuestionContent());
            optionRuleConfig.setOptionId(option.getOptionId().toString());
            optionRuleConfig.setOptionDesc(option.getOptionDesc());
            optionRuleConfig.setRule(optionRule.getRule());
            optionRuleConfig.setRuleType(optionRule.getRuleType());
            optionRuleConfig.setLevel(optionRule.getLevel().toString());
            optionRuleConfigs.add(optionRuleConfig);
		}
		return optionRuleConfigs;
	}
	
	@ResponseBody
	@RequestMapping(value="/addConfig",method=RequestMethod.POST, produces = "application/json")
	public List<OptionRule> addOptionRulesConfig(@RequestBody List<OptionRuleConfig> configs) {
		List<OptionRule> optionRules = new ArrayList<OptionRule>();
		List<OptionRuleConfig> optionRuleConfigs = new ArrayList<OptionRuleConfig>();
		String sql = "";

		for (OptionRuleConfig config: configs) {
			String questionContent = config.getQuestionContent();
			String optionDesc = config.getOptionDesc();
			String level = config.getLevel();
			String rule = config.getRule();
			String ruleType = config.getRuleType();

			sql = "select p from Option p where p.optionDesc ='"+ optionDesc +"'";
			Query query = em.createQuery(sql);
			List<Option> optionList = query.getResultList();
			OptionRule optionRule = new OptionRule();
			optionRule.setLevel(Integer.valueOf(level));
			optionRule.setRule(rule);
			optionRule.setRuleType(ruleType);
			optionRule.setOptionId(optionList.get(0).getOptionId().toString());
			optionRule.setSequence(0);
			optionRule.setCreateDate(new Date());
			optionRule.setUpdatedDate(new Date());
			optionRuleService.updateResource(optionRule);
			optionRules.add(optionRule);
		}
		return optionRules;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editOptionRulePage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-optionRule-form");
		OptionRule optionRule = optionRuleService.getResource(id);
		modelAndView.addObject("optionRule",optionRule);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingOptionRule(@ModelAttribute OptionRule optionRule, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		optionRuleService.updateResource(optionRule);
		
		String message = "OptionRule was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteOptionRule(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		optionRuleService.deleteResource(id);
		String message = "OptionRule was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
