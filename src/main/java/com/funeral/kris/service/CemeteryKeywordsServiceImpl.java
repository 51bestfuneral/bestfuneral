package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TCemeteryKeywordsDAO;
import com.funeral.kris.model.TCemeteryKeywords;

@Service
@Transactional
public class CemeteryKeywordsServiceImpl implements CemeteryKeywordsService {
	@Autowired
	private TCemeteryKeywordsDAO cemeteryKeywordsDAO;

	@Override
	public void addResource(TCemeteryKeywords tCemeteryKeywords) {
		cemeteryKeywordsDAO.save(tCemeteryKeywords);

	}

	@Override
	public void updateResource(TCemeteryKeywords tCemeteryKeywords) {
		cemeteryKeywordsDAO.save(tCemeteryKeywords);

	}

	@Override
	public TCemeteryKeywords getResource(int id) {

		return cemeteryKeywordsDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		cemeteryKeywordsDAO.delete(id);

	}

	@Override
	public List<TCemeteryKeywords> getResources() {

		List<TCemeteryKeywords> keywordsList = new ArrayList<TCemeteryKeywords>();
		Iterable<TCemeteryKeywords> keywordsIter = cemeteryKeywordsDAO.findAll();
		Iterator<TCemeteryKeywords> iter = keywordsIter.iterator();
		while (iter.hasNext()) {
			TCemeteryKeywords keyword = iter.next();
			keywordsList.add(keyword);
		}
		return keywordsList;

	}

	@Override
	public List<TCemeteryKeywords> findByCemeteryId(int id) {

		List<TCemeteryKeywords> list = new ArrayList<TCemeteryKeywords>();
		List<TCemeteryKeywords> keywordsList = this.getResources();

		
		Iterator<TCemeteryKeywords> iter = keywordsList.iterator();
		while (iter.hasNext()) {
			TCemeteryKeywords keyword = iter.next();
			System.out.println("============= keywordsList.size() ="+keywordsList.size()+" keyword="+keyword+" id="+id+" getCemeteryId ="+keyword.getCemeteryId());

			
			if (keyword.getCemeteryId().intValue() == id) {
				list.add(keyword);
			}
		}

		return list;
	}

}
