package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.AnswerDAO;
import com.funeral.kris.model.Answer;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerDAO AnswerDAO;

	public void addResource(Answer answer) {
		AnswerDAO.save(answer);		
	}

	public void updateResource(Answer answer) {
		AnswerDAO.save(answer);
	}

	public Answer getResource(int id) {
		return AnswerDAO.findOne(id);
	}

	public void deleteResource(int id) {
		AnswerDAO.delete(id);
	}

	public List<Answer> getResources() {
		List<Answer> answerList = new ArrayList<Answer>();
		Iterable<Answer> answerIter = AnswerDAO.findAll();
		Iterator<Answer> iter = answerIter.iterator();
		while(iter.hasNext()) {
			Answer answer = iter.next();
			answerList.add(answer);
		}
		return answerList;
	}
}
