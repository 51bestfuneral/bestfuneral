package com.funeral.kris.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.QuestionDAO;
import com.funeral.kris.model.Question;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO QuestionDAO;
	@Autowired
	private EntityManager em;

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

	public List<Question> getResources(HttpServletRequest request) {
		String a = null;
		try {
		    a = SqlHelper.getSqlFromRequest("Question", request);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<Question> questionList = query.getResultList();
		return questionList;
	}

	public List<Question> findNextResource(HttpServletRequest request) {
		String a = null;
		String lastSequence = request.getParameter("sequence");
		String optionId = request.getParameter("optionId");
		try {
		    a = "select q from Question q where sequence > "+lastSequence;
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		query.setMaxResults(1);
		List<Question> questionList = query.getResultList();
		return questionList;
	}

}
