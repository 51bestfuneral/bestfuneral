package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.QuestionDAO;
import com.funeral.kris.model.Question;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO QuestionDAO;

	public void addResource(Question question) {
		QuestionDAO.save(question);		
	}

	public void updateResource(Question question) {
		QuestionDAO.save(question);
	}

	public Question getResource(int id) {
		return QuestionDAO.findOne(id);
	}

	public void deleteResource(int id) {
		QuestionDAO.delete(id);
	}

	public List<Question> getResources() {
		List<Question> questionList = new ArrayList<Question>();
		Iterable<Question> questionIter = QuestionDAO.findAll();
		Iterator<Question> iter = questionIter.iterator();
		while(iter.hasNext()) {
			Question question = iter.next();
			questionList.add(question);
		}
		return questionList;
	}
}
