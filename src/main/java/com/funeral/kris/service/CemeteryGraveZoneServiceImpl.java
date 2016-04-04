package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TCemeteryGraveZoneDAO;
import com.funeral.kris.model.TCemeteryGraveZone;
@Service
@Transactional
public class CemeteryGraveZoneServiceImpl implements CemeteryGraveZoneService{
	@Autowired
	private TCemeteryGraveZoneDAO cemeteryGraveZoneDAO;
	@Override
	public void addResource(TCemeteryGraveZone tCemeteryGraveZone) {
		cemeteryGraveZoneDAO.save(tCemeteryGraveZone)		;
	}

	@Override
	public void updateResource(TCemeteryGraveZone tCemeteryGraveZone) {
		cemeteryGraveZoneDAO.save(tCemeteryGraveZone)		;
		
	}

	@Override
	public TCemeteryGraveZone getResource(int id) {
		return cemeteryGraveZoneDAO.findOne(id);
	}

	@Override
	public void deleteResource(int id) {
		cemeteryGraveZoneDAO.delete(id);
		
	}

	@Override
	public List<TCemeteryGraveZone> getResources() {

		List<TCemeteryGraveZone> zoneList = new ArrayList<TCemeteryGraveZone>();
		Iterable<TCemeteryGraveZone> zoneIter = cemeteryGraveZoneDAO.findAll();
		Iterator<TCemeteryGraveZone> iter = zoneIter.iterator();
		while(iter.hasNext()) {
			TCemeteryGraveZone style = iter.next();
			zoneList.add(style);
		}
		return zoneList;
	
	}

	@Override
	public List<TCemeteryGraveZone> findByCemeteryId(int id) {
		
		List<TCemeteryGraveZone> zoneList = getResources();
		List<TCemeteryGraveZone> list = new ArrayList<TCemeteryGraveZone>();

		Iterator<TCemeteryGraveZone> iter = zoneList.iterator();
		while(iter.hasNext()) {
			TCemeteryGraveZone style = iter.next();
			if(style.getCemeteryId().intValue()==id){
				list.add(style);
			}
		}
		return list;
		
		
	}

}
