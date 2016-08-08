package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.funeral.kris.model.Wish;
import com.funeral.kris.util.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.CemeteryDAO;
import com.funeral.kris.model.Cemetery;
@Service
@Transactional
public class CemeteryServiceImpl implements CemeteryService {

	@Autowired
	private CemeteryDAO CemeteryDAO;
	@Autowired
	private EntityManager em;
	
	public void addResource(Cemetery cemetery) {
		CemeteryDAO.save(cemetery);
	}

	public void updateResource(Cemetery cemetery) {
		System.out.println(" dao="+CemeteryDAO+" getDescImgUrl="+cemetery.getDescImgUrl());
	
		
		
		CemeteryDAO.save(cemetery);
	}

	public Cemetery getResource(int id) {
		
		System.out.println(" dao="+CemeteryDAO+" id="+id);
		return CemeteryDAO.findOne(id);
	}

	public void deleteResource(int id) {
		CemeteryDAO.delete(id);
	}

	public List<Cemetery> getResources(HttpServletRequest request) {
		String a = null;
		try {
			a = SqlHelper.getSqlFromRequest("Cemetery", request);
		} catch (Exception e) {

		}
		Query query = em.createQuery(a);
		List<Cemetery> cemeteryList = query.getResultList();
		return cemeteryList;
	}
}
