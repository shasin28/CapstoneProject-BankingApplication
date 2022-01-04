package com.wiley.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.beans.Account;
import com.wiley.beans.User;
import com.wiley.dao.UserDao;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public User getUserBy(String userId) {
		User user=userDao.findById(userId).get();
		if(user==null)
		{
			//handle user doesen't exist
		}
		return user;
	}
	@Override
	public Account getAccountById(String userId) {
		Account account=null;//userDao.getAccountById(userId);
		if(account==null)
		{
			//handle no account associated
		}
		return account;
	}
	@Override
	public User checkLoginDetails(String userId, String password) {
		User user = userDao.checkLogin(userId, password);
		return user;
	}
	@Override
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users=(ArrayList<User>) userDao.findAll();
		return users;
	}
	@Override
	public Boolean insertUser(User user) {
		User u=userDao.save(user);
		if(u==null)
			return false;
		return true;
	}
	
	

}
