package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.FuneralTemplateDAO;
import com.funeral.kris.model.FuneralTemplate;

@Service
@Transactional
public class FuneralTemplateServiceImpl implements FuneralTemplateService {

	@Autowired
	private FuneralTemplateDAO FuneralTemplateDAO;

	public void addResource(FuneralTemplate funeralTemplate) {
		FuneralTemplateDAO.save(funeralTemplate);		
	}

	public void updateResource(FuneralTemplate funeralTemplate) {
		FuneralTemplateDAO.save(funeralTemplate);
	}

	public FuneralTemplate getResource(int id) {
		return FuneralTemplateDAO.findOne(id);
	}

	public void deleteResource(int id) {
		FuneralTemplateDAO.delete(id);
	}

	public List<FuneralTemplate> getResources() {
		List<FuneralTemplate> funeralTemplateList = new ArrayList<FuneralTemplate>();
		Iterable<FuneralTemplate> funeralTemplateIter = FuneralTemplateDAO.findAll();
		Iterator<FuneralTemplate> iter = funeralTemplateIter.iterator();
		while(iter.hasNext()) {
			FuneralTemplate funeralTemplate = iter.next();
			funeralTemplateList.add(funeralTemplate);
		}
		return funeralTemplateList;
	}
}
