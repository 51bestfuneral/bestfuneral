package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.OptionDAO;
import com.funeral.kris.model.Option;

@Service
@Transactional
public class OptionServiceImpl implements OptionService {

	@Autowired
	private OptionDAO OptionDAO;

	public void addResource(Option option) {
		OptionDAO.save(option);		
	}

	public void updateResource(Option option) {
		OptionDAO.save(option);
	}

	public Option getResource(int id) {
		return OptionDAO.findOne(id);
	}

	public void deleteResource(int id) {
		OptionDAO.delete(id);
	}

	public List<Option> getResources() {
		List<Option> optionList = new ArrayList<Option>();
		Iterable<Option> optionIter = OptionDAO.findAll();
		Iterator<Option> iter = optionIter.iterator();
		while(iter.hasNext()) {
			Option option = iter.next();
			optionList.add(option);
		}
		return optionList;
	}
}
