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
import com.funeral.kris.model.Question;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class OptionServiceImpl implements OptionService {

	@Autowired
	private OptionDAO OptionDAO;
	@Autowired
	private EntityManager em;

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

	public List<Option> getResources(HttpServletRequest request) {
		String a = null;
		try {
		    a = SqlHelper.getSqlFromRequest("Option", request);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<Option> questionList = query.getResultList();
		return questionList;
	}
}
