package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TServiceCategoryDAO;
import com.funeral.kris.model.TServiceCategory;
@Service
@Transactional
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

	@Autowired
	private TServiceCategoryDAO tServiceCategoryDAO;
    @Resource  
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public void addResource(TServiceCategory serviceCate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateResource(TServiceCategory serviceCate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TServiceCategory getResource(int cateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteResource(int cateId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TServiceCategory> getResources() {
		List<TServiceCategory> serviceCategoryList = new ArrayList<TServiceCategory>();
		Iterable<TServiceCategory> serviceCategoryIter = tServiceCategoryDAO.findAll();
		Iterator<TServiceCategory> iter = serviceCategoryIter.iterator();
		while(iter.hasNext()) {
			TServiceCategory serviceCategory = iter.next();
			serviceCategoryList.add(serviceCategory);
		}
		return serviceCategoryList;
	}

}
