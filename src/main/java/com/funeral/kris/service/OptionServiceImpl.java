package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.OptionDAO;
import com.funeral.kris.model.Option;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class OptionServiceImpl implements OptionService {

	@Autowired
	private OptionDAO OptionDAO;
	@Autowired
	private EntityManager em;

	public void addResource(Option option) {

		System.out.println(this.getClass() + "count=  " + OptionDAO.count());

		OptionDAO.save(option);
	}

	public void updateResource(Option option) {
		OptionDAO.save(option);
	}

	public Option getResource(Long id) {

		return OptionDAO.findOne(id);
	}

	public void deleteResource(Long id) {
		OptionDAO.delete(id);
	}

	@Override
	public List<Option> getResources() {

		List<Option> optionList = new ArrayList<Option>();
		Iterable<Option> optionListIterable = OptionDAO.findAll();

		System.out.println("  getResources   " + optionList);

		Iterator<Option> iter = optionListIterable.iterator();
		while (iter.hasNext()) {
			Option option = iter.next();
			optionList.add(option);
		}
		return optionList;

	}

	public List<Option> getResources(HttpServletRequest request) {
		String a = null;
		try {
			a = SqlHelper.getSqlFromRequest("Option", request);
		} catch (Exception e) {

		}
		Query query = em.createQuery(a);
		List<Option> questionList = query.getResultList();
		return questionList;
	}

	@Override
	public List<Option> getOptionListByQuestionId(String questionId) {
		List<Option> options = getResources();

		if (options == null) {

			options = new ArrayList<Option>();
		}

		List<Option> list = new ArrayList<Option>();

		Iterator iterator = options.iterator();

		while (iterator.hasNext()) {

			Option option = (Option) iterator.next();

			if (option.getQuestionId().equals(questionId.toString())) {

				list.add(option);
			}

		}

		return list;
	}
}
