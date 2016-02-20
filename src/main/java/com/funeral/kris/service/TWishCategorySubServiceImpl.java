package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TWishCategorySubDAO;
import com.funeral.kris.model.TWishCategory;
import com.funeral.kris.model.TWishCategorySub;

@Service
@Transactional
public class TWishCategorySubServiceImpl implements TWishCategorySubService {

	@Autowired
	private TWishCategorySubDAO tWishCategorySubDAO;
	
	@Autowired
	private TWishCategoryService WishCategoryService;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addResource(TWishCategorySub tWishCategorySub) {
		tWishCategorySubDAO.save(tWishCategorySub);

	}

	@Override
	public void updateResource(TWishCategorySub tWishCategorySub) {
		tWishCategorySubDAO.save(tWishCategorySub);

	}

	@Override
	public TWishCategorySub getResource(int id) {
		return tWishCategorySubDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		tWishCategorySubDAO.delete(id);

	}

	@Override
	public List<TWishCategorySub> getResources() {

		List wishCategorySubList = new ArrayList();

		Iterator<TWishCategorySub> iterator =  tWishCategorySubDAO.findAll().iterator();
		while (iterator != null && iterator.hasNext()) {
			wishCategorySubList.add(iterator.next());

		}
		return wishCategorySubList;

	}

	@Override
	public List<TWishCategorySub> loadByCateId(int cateId) {

		List wishCategorySubList = getResources();
		
		
		List newWishCategorySubList = new ArrayList();

		Iterator  iterator = wishCategorySubList.iterator();

		while (iterator != null && iterator.hasNext()) {

			TWishCategorySub tWishCategorySub = (TWishCategorySub) iterator.next();

			if (tWishCategorySub.getCateId().intValue() == cateId)
				newWishCategorySubList.add(tWishCategorySub);

		}
		Collections.sort(newWishCategorySubList, new SortByAge());
		return newWishCategorySubList;
	}

	class SortByAge implements Comparator {
		public int compare(Object o1, Object o2) {
			TWishCategorySub s1 = (TWishCategorySub) o1;
			TWishCategorySub s2 = (TWishCategorySub) o2;
		    if (s1.getWishId() > s2.getWishId())
		       return 1;
		    return 0;
	    }
	}
}
