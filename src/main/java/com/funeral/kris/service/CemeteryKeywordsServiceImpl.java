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

import com.funeral.kris.dao.TCemeteryKeywordsDAO;
import com.funeral.kris.model.TCemeteryKeywords;
import com.funeral.kris.model.Wish;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class CemeteryKeywordsServiceImpl implements CemeteryKeywordsService {
	@Autowired
	private TCemeteryKeywordsDAO cemeteryKeywordsDAO;
	@Autowired
	private EntityManager em;

	@Override
	public void addResource(TCemeteryKeywords tCemeteryKeywords) {
		cemeteryKeywordsDAO.save(tCemeteryKeywords);

	}

	@Override
	public void updateResource(TCemeteryKeywords tCemeteryKeywords) {
		cemeteryKeywordsDAO.save(tCemeteryKeywords);

	}

	@Override
	public TCemeteryKeywords getResource(int id) {

		return cemeteryKeywordsDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		cemeteryKeywordsDAO.delete(id);

	}

	@Override
	public List<TCemeteryKeywords> getResources(HttpServletRequest request) {
		String a = null;
		try {
			a = SqlHelper.getSqlFromRequest("TCemeteryKeywords", request);
		} catch (Exception e) {

		}
		Query query = em.createQuery(a);
		List<TCemeteryKeywords> keywordsList = query.getResultList();
		return keywordsList;

	}

}
