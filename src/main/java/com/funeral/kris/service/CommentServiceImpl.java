package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.CommentDAO;
import com.funeral.kris.model.Comment;
import com.funeral.kris.model.Wish;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO CommentDAO;
	@Autowired
	private EntityManager em;

	public void addResource(Comment comment) {
		CommentDAO.save(comment);		
	}

	public void updateResource(Comment comment) {
		CommentDAO.save(comment);
	}

	public Comment getResource(int id) {
		return CommentDAO.findOne(id);
	}

	public void deleteResource(int id) {
		CommentDAO.delete(id);
	}

	public List<Comment> getResources(HttpServletRequest request) {
		String a = null;
		try {
		    a = SqlHelper.getSqlFromRequest("Comment", request);
		}
		catch (Exception e) {
			
		}
		Query query = em.createQuery(a);
		List<Comment> comments = query.getResultList();
		return comments;
	}
}
