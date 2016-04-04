package com.funeral.kris.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.funeral.kris.dao.UserDAO;
import com.funeral.kris.model.User;

public class SignInServiceImpl implements SignInService {
	@Autowired
	private UserDAO userDAO;
	@Override
	public void createUser(User user) {
		
		userDAO.save(user);
	}

}
