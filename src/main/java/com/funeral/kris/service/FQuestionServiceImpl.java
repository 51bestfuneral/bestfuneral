package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.TFQuestionDAO;
import com.funeral.kris.model.TFQuestion;

@Service
@Transactional
public class FQuestionServiceImpl implements FQuestionService {

	@Autowired
	private TFQuestionDAO tFQuestionDAO;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addResource(TFQuestion fQuestiion) {
		fQuestiion = this.verify(fQuestiion);

		tFQuestionDAO.save(fQuestiion);

	}

	@Override
	public void updateResource(TFQuestion fQuestiion) {
		fQuestiion = this.verify(fQuestiion);
		tFQuestionDAO.save(fQuestiion);

	}

	@Override
	public TFQuestion getResource(long questionId) {
		TFQuestion tFQuestion = tFQuestionDAO.findOne(questionId);
		return tFQuestion;
	}

	@Override
	public void deleteResource(long questionId) {
		tFQuestionDAO.delete(questionId);
	}

	@Override
	public List<TFQuestion> getResources() {

		List<TFQuestion> tFQuestionList = new ArrayList<TFQuestion>();
		Iterable<TFQuestion> tFQuestionListIter = tFQuestionDAO.findAll();

		System.out.println("  getResources   " + tFQuestionListIter);

		Iterator<TFQuestion> iter = tFQuestionListIter.iterator();
		while (iter.hasNext()) {
			TFQuestion tFQuestion = iter.next();
			tFQuestionList.add(tFQuestion);
		}
		return tFQuestionList;

	}

	private TFQuestion verify(TFQuestion tFQuestion) {

		if (tFQuestion.getCreatorId() == 0) {

			tFQuestion.setCreatorId(1);
		}
		if (tFQuestion.getOperatorId() == 0) {

			tFQuestion.setOperatorId(1);
		}
		if (tFQuestion.getUpdatorId() == 0) {

			tFQuestion.setUpdatorId(1);
		}

		return tFQuestion;

	}

}
