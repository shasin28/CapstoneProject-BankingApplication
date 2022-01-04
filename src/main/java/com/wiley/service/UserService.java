package com.wiley.service;

import java.util.ArrayList;

import com.wiley.beans.Account;
import com.wiley.beans.User;

public interface UserService {

	public User getUserBy(String userId);
	public Account getAccountById(String userId);
	public User checkLoginDetails(String userId, String password);
	public ArrayList<User> getAllUsers();
	public Boolean insertUser(User user);
}
