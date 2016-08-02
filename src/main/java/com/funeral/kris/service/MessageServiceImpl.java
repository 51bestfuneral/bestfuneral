package com.funeral.kris.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.MessageDAO;
import com.funeral.kris.model.Message;
import com.funeral.kris.model.MessageUser;
import com.funeral.kris.model.Wish;
import com.funeral.kris.rowMapper.MessageUserMapper;
import com.funeral.kris.util.SqlHelper;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;
    @Resource  
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityManager em;

	public void addResource(Message message) {
		if (message.getCreateDate() == null) {
			message.setCreateDate(new Date());
			message.setUpdatedDate(new Date());
		}
		messageDAO.save(message);		
	}

	public void updateResource(Message message) {
		message.setUpdatedDate(new Date());
		messageDAO.save(message);
	}

	public Message getResource(int id) {
		return messageDAO.findOne(id);
	}

	public void deleteResource(int id) {
		messageDAO.delete(id);
	}

	public List<MessageUser> getResources() {
		String a = null;
	    a = "select u.user_name,m.*"
		      + "  from users u, messages m"
		      + " where u.user_id = m.user_id";
		List<MessageUser> messageUsers = jdbcTemplate.query(a, new MessageUserMapper());
		return messageUsers;
	}
	
	public List<Message> getResources(HttpServletRequest request) {
		String a = null;
		try {
			a = SqlHelper.getSqlFromRequest("Message", request);
		} catch (Exception e) {

		}
		Query query = em.createQuery(a);
		List<Message> wishList = query.getResultList();
		return wishList;
	}
}
