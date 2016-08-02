package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TCemeteryEpigraphStyleDAO;
import com.funeral.kris.model.TCemeteryEpigraphStyle;

@Service
@Transactional
public class CemeteryEpigraphStyleServiceImpl implements CemeteryEpigraphStyleService {

	@Autowired
	private TCemeteryEpigraphStyleDAO cemeteryEpigraphStyleDAO;

	@Override
	public void addResource(TCemeteryEpigraphStyle tCemeteryEpigraphStyle) {
		cemeteryEpigraphStyleDAO.save(tCemeteryEpigraphStyle);

	}

	@Override
	public void updateResource(TCemeteryEpigraphStyle tCemeteryEpigraphStyle) {
		cemeteryEpigraphStyleDAO.save(tCemeteryEpigraphStyle);

	}

	@Override
	public TCemeteryEpigraphStyle getResource(int id) {

		return cemeteryEpigraphStyleDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		cemeteryEpigraphStyleDAO.delete(id);

	}

	@Override
	public List<TCemeteryEpigraphStyle> getResources() {

		List<TCemeteryEpigraphStyle> styleList = new ArrayList<TCemeteryEpigraphStyle>();
		Iterable<TCemeteryEpigraphStyle> styleIter = cemeteryEpigraphStyleDAO.findAll();
		Iterator<TCemeteryEpigraphStyle> iter = styleIter.iterator();
		while (iter.hasNext()) {
			TCemeteryEpigraphStyle style = iter.next();
			styleList.add(style);
		}
		return styleList;

	}

	@Override
	public List<TCemeteryEpigraphStyle> findByCemeteryId(int id) {

		List<TCemeteryEpigraphStyle> styleList = getResources();
		List<TCemeteryEpigraphStyle> list = new ArrayList<TCemeteryEpigraphStyle>() ;

		Iterator iterator = styleList.iterator();

		while (iterator.hasNext()) {

			TCemeteryEpigraphStyle style = (TCemeteryEpigraphStyle) iterator.next();

			if (style.getCemeteryId().intValue() == id) {

				list.add(style);
			}

		}

		return list;

	}

}
