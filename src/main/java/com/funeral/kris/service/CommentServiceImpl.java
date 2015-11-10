package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.CommentDAO;
import com.funeral.kris.model.Comment;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO CommentDAO;

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

	public List<Comment> getResources() {
		List<Comment> commentList = new ArrayList<Comment>();
		Iterable<Comment> commentIter = CommentDAO.findAll();
		Iterator<Comment> iter = commentIter.iterator();
		while(iter.hasNext()) {
			Comment comment = iter.next();
			commentList.add(comment);
		}
		return commentList;
	}
}
