package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TWishLevelDAO;
import com.funeral.kris.model.TWishLevel;

@Service
@Transactional
public class TWishLevelServiceImpl implements TWishLevelService {
	@Autowired
	private TWishLevelDAO tWishLevelDAO;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addResource(TWishLevel tWishLevel) {
		tWishLevelDAO.save(tWishLevel);
	}

	@Override
	public void updateResource(TWishLevel tWishLevel) {
		tWishLevelDAO.save(tWishLevel);

	}

	@Override
	public TWishLevel getResource(int id) {
		return tWishLevelDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		tWishLevelDAO.delete(id);
	}

	@Override
	public List<TWishLevel> getResources() {

		List WishLevelList = new ArrayList();

		Iterable<TWishLevel> iterator = tWishLevelDAO.findAll();

		while (iterator != null && iterator.iterator().hasNext()) {
			WishLevelList.add(iterator.iterator().next());

		}
		return WishLevelList;
	}

}
