package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.UserDAO;
import com.funeral.kris.model.User;
import com.funeral.kris.rowMapper.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
    @Resource  
    private JdbcTemplate jdbcTemplate;

	public void addResource(User user) {
		userDAO.save(user);		
	}

	public void updateResource(User user) {
		userDAO.save(user);
	}

	public User getResource(int id) {
		return userDAO.findOne(id);
	}

	public void deleteResource(int id) {
		userDAO.delete(id);
	}

	public List<User> getResources() {
		List<User> userList = new ArrayList<User>();
		Iterable<User> userIter = userDAO.findAll();
		Iterator<User> iter = userIter.iterator();
		while(iter.hasNext()) {
			User user = iter.next();
			userList.add(user);
		}
		return userList;
	}

	public List<User> verifyUser(String userName, String pwd, String userType) {
		List<User> usrlist = new ArrayList<User>();
		String queryCommand = String.format(verifySql, userName, pwd, userType);
		usrlist = jdbcTemplate.query(queryCommand, new UserMapper());
		System.out.println(queryCommand);
		return usrlist;
	}

	private static final String verifySql =
			"select *"
		  + "  from users"
		  + " where user_name = '%s'"
		  + "   and pwd = '%s'"
		  + "   and user_type = '%s'";
}
