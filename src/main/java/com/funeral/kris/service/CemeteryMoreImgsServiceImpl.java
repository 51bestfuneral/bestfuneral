package com.funeral.kris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TCemeteryMoreImgsDAO;
import com.funeral.kris.model.TCemeteryMoreImgs;
@Service
@Transactional
public class CemeteryMoreImgsServiceImpl implements CemeteryMoreImgsService {
	@Autowired
	private TCemeteryMoreImgsDAO cemeteryMoreImgsDAO;
	@Override
	public void addResource(TCemeteryMoreImgs tCemeteryMoreImgs) {
		cemeteryMoreImgsDAO.save(tCemeteryMoreImgs);
	}

	@Override
	public void updateResource(TCemeteryMoreImgs tCemeteryMoreImgs) {
		cemeteryMoreImgsDAO.save(tCemeteryMoreImgs);
		
	}

	@Override
	public TCemeteryMoreImgs getResource(int id) {
		TCemeteryMoreImgs tCemeteryMoreImgs	=cemeteryMoreImgsDAO.findOne(id);
		return tCemeteryMoreImgs;
	}

	@Override
	public void deleteResource(int id) {
		cemeteryMoreImgsDAO.delete(id);		
	}

	@Override
	public List<TCemeteryMoreImgs> getResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TCemeteryMoreImgs> findByCemeteryId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
