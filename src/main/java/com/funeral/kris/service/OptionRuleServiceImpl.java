package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.OptionRuleDAO;
import com.funeral.kris.model.OptionRule;

@Service
@Transactional
public class OptionRuleServiceImpl implements OptionRuleService {

	@Autowired
	private OptionRuleDAO OptionRuleDAO;

	public void addResource(OptionRule optionRule) {
		OptionRuleDAO.save(optionRule);		
	}

	public void updateResource(OptionRule optionRule) {
		OptionRuleDAO.save(optionRule);
	}

	public OptionRule getResource(int id) {
		return OptionRuleDAO.findOne(id);
	}

	public void deleteResource(int id) {
		OptionRuleDAO.delete(id);
	}

	public List<OptionRule> getResources() {
		List<OptionRule> optionRuleList = new ArrayList<OptionRule>();
		Iterable<OptionRule> optionRuleIter = OptionRuleDAO.findAll();
		Iterator<OptionRule> iter = optionRuleIter.iterator();
		while(iter.hasNext()) {
			OptionRule optionRule = iter.next();
			optionRuleList.add(optionRule);
		}
		return optionRuleList;
	}
}
