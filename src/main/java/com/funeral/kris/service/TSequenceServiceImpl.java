package com.funeral.kris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TSequenceDAO;
import com.funeral.kris.model.TSequence;
@Service
@Transactional
public class TSequenceServiceImpl implements TSequenceService {
	@Autowired
	private TSequenceDAO sequenceDAO;
	@Override
	public Integer getSequence() {
		
		TSequence sequence=new TSequence ();
		
		sequenceDAO.save(sequence);
		
		System.out.println(this.getClass()+"-----getSequence--id= "+sequence.getId());
		
		return sequence.getId()+600;
	}

}
