package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TWishLevelRelatonDAO;
import com.funeral.kris.model.TWishLevel;
import com.funeral.kris.model.TWishLevelRelation;

@Service
@Transactional
public class TWishLevelRelationServiceImpl implements TWishLevelRelationService {
	@Autowired
	private TWishLevelRelatonDAO tWishLevelRelationDAO;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addResource(TWishLevelRelation tWishLevelRelation) {
		tWishLevelRelationDAO.save(tWishLevelRelation);
	}

	@Override
	public void updateResource(TWishLevelRelation tWishLevelRelation) {
		tWishLevelRelationDAO.save(tWishLevelRelation);

	}

	@Override
	public TWishLevelRelation getResource(int id) {
		return tWishLevelRelationDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		tWishLevelRelationDAO.delete(id);
	}

	@Override
	public List<TWishLevelRelation> getResources() {

		List wishLevelRelationList = new ArrayList();

		Iterable<TWishLevelRelation> iterator = tWishLevelRelationDAO.findAll();

		while (iterator != null && iterator.iterator().hasNext()) {
			wishLevelRelationList.add(iterator.iterator().next());

		}
		return wishLevelRelationList;

	}

}
