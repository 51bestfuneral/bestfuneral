package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TServiceCategoryClassDAO;
import com.funeral.kris.model.TServiceCategoryClass;

@Service
@Transactional
public class ServiceCategoryClassServiceImpl implements ServiceCategoryClassService {

	@Autowired
	private TServiceCategoryClassDAO tServiceCategoryClassDAO;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addResource(TServiceCategoryClass serviceCateClass) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateResource(TServiceCategoryClass serviceCateClass) {
		// TODO Auto-generated method stub

	}

	@Override
	public TServiceCategoryClass getResource(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteResource(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public TServiceCategoryClass getServiceCategoryClassByCateIdAndClassId(Integer cateId, int classId) {

		TServiceCategoryClass serviceCategoryClass = new TServiceCategoryClass();
		Iterable<TServiceCategoryClass> serviceCategoryIter = tServiceCategoryClassDAO.findAll();
		Iterator<TServiceCategoryClass> iter = serviceCategoryIter.iterator();
		while (iter.hasNext()) {
			serviceCategoryClass = iter.next();

			if (serviceCategoryClass.getCateId().equals(cateId.toString())
					&& serviceCategoryClass.getClassId() == classId) {
				return serviceCategoryClass;

			}

		}

		return serviceCategoryClass;
	}

	@Override
	public List<TServiceCategoryClass> getResources() {

		List<TServiceCategoryClass> serviceCategoryClassList = new ArrayList<TServiceCategoryClass>();
		Iterable<TServiceCategoryClass> serviceCategoryIter = tServiceCategoryClassDAO.findAll();
		Iterator<TServiceCategoryClass> iter = serviceCategoryIter.iterator();
		while (iter.hasNext()) {
			TServiceCategoryClass serviceCategoryClass = iter.next();
			serviceCategoryClassList.add(serviceCategoryClass);
		}
		return serviceCategoryClassList;

	}

}
