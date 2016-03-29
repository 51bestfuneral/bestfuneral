package com.funeral.kris.service;

import java.util.List;

import com.funeral.kris.model.User;

public interface UserService {
	
	public void addResource(User user);
	public void updateResource(User user);
	public User getResource(int id);
	public void deleteResource(int id);
	public List<User> getResources();
	public List<User> verifyUser(String userName, String pwd, String userType);
	public Integer checkLogin(String account,String pwd);
	public User getByPhone(String phoneNumber);
	public User getByEmail(String email) ;
	public User getByAccount(String account);
}
