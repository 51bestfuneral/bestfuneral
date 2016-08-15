package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TCemeteryGraveStyleDAO;
import com.funeral.kris.model.TCemeteryGraveStyle;
@Service
@Transactional
public class CemeteryGraveStyleServiceImpl implements CemeteryGraveStyleService {
	@Autowired
	private TCemeteryGraveStyleDAO cemeteryGraveStyleDAO;
	@Override
	public void addResource(TCemeteryGraveStyle tCemeteryGraveStyle) {
		cemeteryGraveStyleDAO.save(tCemeteryGraveStyle);
		
	}

	@Override
	public void updateResource(TCemeteryGraveStyle tCemeteryGraveStyle) {
		cemeteryGraveStyleDAO.save(tCemeteryGraveStyle);
		
	}

	@Override
	public TCemeteryGraveStyle getResource(int id) {
		
		return cemeteryGraveStyleDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		cemeteryGraveStyleDAO.delete(id);		
	}

	@Override
	public List<TCemeteryGraveStyle> getResources() {

		List<TCemeteryGraveStyle> styleList = new ArrayList<TCemeteryGraveStyle>();
		Iterable<TCemeteryGraveStyle> styleIter = cemeteryGraveStyleDAO.findAll();
		Iterator<TCemeteryGraveStyle> iter = styleIter.iterator();
		while(iter.hasNext()) {
			TCemeteryGraveStyle style = iter.next();
			styleList.add(style);
		}
		return styleList;
	
	}

	@Override
	public List<TCemeteryGraveStyle> getByCemeteryId(int id) {

		return cemeteryGraveStyleDAO.findByCemeteryId(id);
	}

}
