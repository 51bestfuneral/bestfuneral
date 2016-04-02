package com.funeral.kris.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.dao.UserDAO;
import com.funeral.kris.init.constants.LoginConstants;
import com.funeral.kris.model.User;

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
		while (iter.hasNext()) {
			User user = iter.next();
			userList.add(user);
		}
		return userList;
	}

	public User getByPhone(String phoneNumber) {

		List<User> userList = getResources();

		Iterator iter = userList.iterator();

		while (iter.hasNext()) {

			User user = (User) iter.next();

			if (phoneNumber.equals(user.getPhone())) {

				return user;
			}

		}

		return null;

	}

	public User getByEmail(String email) {

		List<User> userList = getResources();

		Iterator iter = userList.iterator();

		while (iter.hasNext()) {

			User user = (User) iter.next();

			if (email.equals(user.getEmail())) {

				return user;
			}

		}

		return null;

	}

	public List<User> verifyUser(String userName, String pwd, String userType) {
		// List<User> usrlist = new ArrayList<User>();
		// String queryCommand = String.format(verifySql, userName, pwd,
		// userType);
		// usrlist = jdbcTemplate.query(queryCommand, new UserMapper());
		// System.out.println(queryCommand);
		return null;
	}

	private static final String verifySql = "select *" + "  from users" + " where user_name = '%s'"
			+ "   and pwd = '%s'" + "   and user_type = '%s'";

	@Override
	public Integer checkLogin(String account, String pwd, HttpServletRequest request) {
        int resultCode = 0;
        User user = null;
        Map<Integer, User> userMap = new HashMap<Integer, User>();
		if (account.contains("@")) {

			user = this.getByEmail(account);

			if (user == null) {
				resultCode = LoginConstants.userNotExist;
			} else {
				user.setLastLoginTime(new Date());
				if (user.getStatus().intValue() == LoginConstants.accountLocked) {
					long lockedTime = new Date().getTime() - user.getLastLoginTime().getTime();
					if (lockedTime/ (1000 * 60) > 30 && pwd.toString().equals(user.getPassword())) {
						user.setInvalidLoginTimes(0);
						user.setStatus(LoginConstants.validatePass);
					}
					else {
					    resultCode = LoginConstants.accountLocked;
					}
				} else if (pwd.toString().equals(user.getPassword())) {

					resultCode = LoginConstants.validatePass;
				}
				else {
					lockCurrentUser(user);
					resultCode = LoginConstants.pwdIncorrect;
				}
				userDAO.save(user);
			}

		} else {
			user = this.getByPhone(account);

			if (user == null) {
				resultCode = LoginConstants.userNotExist;
			} else {
				user.setLastLoginTime(new Date());
				if (user.getStatus().intValue() == LoginConstants.accountLocked) {
					long lockedTime = new Date().getTime() - user.getLastLoginTime().getTime();
					if (lockedTime/ (1000 * 60) > 30 && pwd.toString().equals(user.getPassword())) {
						user.setInvalidLoginTimes(0);
						user.setStatus(LoginConstants.validatePass);
					}
					else {
					    resultCode = LoginConstants.accountLocked;
					}
				} else if (pwd.toString().equals(user.getPassword())) {

					resultCode = LoginConstants.validatePass;
				}
				else {
					lockCurrentUser(user);
					resultCode = LoginConstants.pwdIncorrect;
				}
				userDAO.save(user);
			}

		}
        HttpSession  session=request.getSession();
		session.setAttribute("user", user);
		Random random = new Random();
		int number=random.nextInt(1000000);

		session.setAttribute("sessionId", System.currentTimeMillis()+"_"+number);

		if(resultCode==LoginConstants.validatePass){
			session.setAttribute(LoginConstants.LoginStatus, LoginConstants.login);
		}else{
			session.setAttribute(LoginConstants.LoginStatus, LoginConstants.OffLine);	
		}
		return resultCode;
	}

	private void lockCurrentUser(User user) {
		int invalidTime = user.getInvalidLoginTimes();
		invalidTime = invalidTime + 1;
		user.setInvalidLoginTimes(invalidTime);
		if (invalidTime >= 5) {
			user.setStatus(LoginConstants.accountLocked);
		}
	}

	@Override
	public User getByAccount(String account) {

		User user = this.getByEmail(account);
		if (user != null) {
			return user;
		} else if (this.getByPhone(account) != null) {

			return this.getByPhone(account);

		}
		return null;
	}
}
