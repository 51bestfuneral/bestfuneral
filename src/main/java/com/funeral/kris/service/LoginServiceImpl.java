package com.funeral.kris.service;

public class LoginServiceImpl implements  LoginService{	
	
	@Override
	public Integer Login(String inputAccount, String inputPwd) {
		
		UserService userService=new UserServiceImpl();
		//return userService.checkLogin(inputAccount, inputPwd);
		return 1;
	}

}
