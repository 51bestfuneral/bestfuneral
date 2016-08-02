package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

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
	@Resource
	private JdbcTemplate jdbcTemplate;
	
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
	
	
	public List<String> getAllCss(){
		
		List<Cemetery> cemeteryList=getResources();
		
		int size=cemeteryList.size();
		
		
		List<String> css=new ArrayList<String>();
		
		css.add("doubleLeft cemeteryDiv");
		css.add("singleRight cemeteryDiv");
		css.add("singleLeft cemeteryDiv");
		css.add("singleMiddle cemeteryDiv");
		css.add("singleRight cemeteryDiv");
		css.add("singleLeft cemeteryDiv");
		css.add("doubleRight cemeteryDiv");
		
		int i=size/7;
		int j=0;
		
		while(j<i){
			
			css.add("doubleLeft cemeteryDiv");
			css.add("singleRight cemeteryDiv");
			css.add("singleLeft cemeteryDiv");
			css.add("singleMiddle cemeteryDiv");
			css.add("singleRight cemeteryDiv");
			css.add("singleLeft cemeteryDiv");
			css.add("doubleRight cemeteryDiv");
			
			j++;
			
		}
		return css;
		
		
		
	}
	

	public List<Cemetery> getResources() {
		List<Cemetery> cemeteryList = new ArrayList<Cemetery>();
		Iterable<Cemetery> cemeteryIter = CemeteryDAO.findAll();
		Iterator<Cemetery> iter = cemeteryIter.iterator();
		while(iter.hasNext()) {
			Cemetery cemetery = iter.next();
			cemeteryList.add(cemetery);
		}
		return cemeteryList;
	}
}
