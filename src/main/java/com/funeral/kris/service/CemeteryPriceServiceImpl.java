package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.CemeteryPriceDAO;
import com.funeral.kris.model.TCemeteryPrice;
@Service
@Transactional
public class CemeteryPriceServiceImpl implements CemeteryPriceService {
	@Autowired
	private CemeteryPriceDAO cemeteryPriceDAO;
	@Override
	public void addResource(TCemeteryPrice cemeteryPrice) {
		cemeteryPriceDAO.save(cemeteryPrice);
		
	}

	@Override
	public void updateResource(TCemeteryPrice cemeteryPrice) {
		cemeteryPriceDAO.save(cemeteryPrice);
		
	}

	@Override
	public TCemeteryPrice getResource(int id) {
				
		return cemeteryPriceDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		cemeteryPriceDAO.delete(id);
		
	}

	@Override
	public List<TCemeteryPrice> getResources() {
		List<TCemeteryPrice> cemeteryPriceList = new ArrayList<TCemeteryPrice>();
		Iterable<TCemeteryPrice> cemeteryIter = cemeteryPriceDAO.findAll();
		Iterator<TCemeteryPrice> iter = cemeteryIter.iterator();
		while(iter.hasNext()) {
			TCemeteryPrice cemeteryPrice = iter.next();
			cemeteryPriceList.add(cemeteryPrice);
		}
		return cemeteryPriceList;
	}

	@Override
	public List<TCemeteryPrice> getCemeteryPriceListByCemeteryId(int cemeteryId) {
	
		List<TCemeteryPrice> cemeteryPriceList= getResources();
		List<TCemeteryPrice> list=new ArrayList();
		Iterator  iterator=cemeteryPriceList.iterator();
		
		while(iterator.hasNext()){
			
			TCemeteryPrice cemeteryPrice =	(TCemeteryPrice) iterator.next();
			
			if(cemeteryPrice.getCemeteryId().intValue()==cemeteryId){
				list.add(cemeteryPrice);
			}
			
			
		}
		
		
		
		
		return list;
		
		
	}

}
