package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.constants.QUESTION;
import com.funeral.kris.dao.TFAnswerDAO;
import com.funeral.kris.model.TFAnswer;

@Service
@Transactional
public class FAnswerServiceImpl implements FAnswerService {

	@Autowired
	private TFAnswerDAO tFAnswerDAO;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addResource(TFAnswer tFAnswer) {
		tFAnswerDAO.save(tFAnswer);
	}

	@Override
	public void updateResource(TFAnswer tFAnswer) {
		tFAnswerDAO.save(tFAnswer);

	}

	@Override
	public TFAnswer getResource(Long id) {

		return tFAnswerDAO.findOne(id);
	}

	@Override
	public void deleteResource(Long id) {
		tFAnswerDAO.delete(id);
	}

	@Override
	public List<TFAnswer> getResources() {

		List<TFAnswer> tFAnswerList = new ArrayList<TFAnswer>();
		Iterable<TFAnswer> tFAnswerListIter = tFAnswerDAO.findAll();

		Iterator<TFAnswer> iter = tFAnswerListIter.iterator();
		while (iter.hasNext()) {
			TFAnswer tFAnswer = iter.next();
			tFAnswerList.add(tFAnswer);
		}
		return tFAnswerList;

	}

	@Override
	public List<TFAnswer> getResources(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAnswer(Long userId, Long questionId) {

		System.out.println(this.getClass() + "  deleteAnswer userId=" + userId + "  questionId=" + questionId);

		List<TFAnswer> answerList = getResources();
		Iterator answerListIterator = answerList.iterator();

		while (answerListIterator.hasNext()) {

			TFAnswer tFAnswer = (TFAnswer) answerListIterator.next();

			if (tFAnswer.getQuestionId() == questionId && tFAnswer.getUserId() == userId) {

				tFAnswerDAO.delete(tFAnswer.getAnswerId());

			}

		}

	}

	@Override
	public TFAnswer getAnswer(Long userId, Long questionId, Long optionId) {
		List<TFAnswer> list = getResources();

		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {

			TFAnswer tFAnswer = (TFAnswer) iterator.next();

			if (tFAnswer.getUserId() == userId && tFAnswer.getQuestionId() == questionId
					&& optionId == tFAnswer.getOptionId()) {

				return tFAnswer;
			}

		}

		return null;

	}

	@Override
	public TFAnswer getAnswerByQuestionId(Long questionId) {

		List<TFAnswer> list = getResources();

		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {

			TFAnswer tFAnswer = (TFAnswer) iterator.next();

			if (tFAnswer.getUserId() == QUESTION.ADMIN_ID && tFAnswer.getQuestionId() == questionId) {

				return tFAnswer;
			}

		}

		return null;

	
	}

}
