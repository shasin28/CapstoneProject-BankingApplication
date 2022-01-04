package com.wiley.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.beans.Admin;
import com.wiley.dao.AdminDao;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin getAdminById(String id) {
		Admin admin=adminDao.findById(id).get();
		if(admin==null)
		{
			//handle admin not present exception
		}
		return admin;
	}

	@Override
	public Admin checkLoginDetails(String id, String password) {
	
		Admin admin=adminDao.checkLogin(id, password);
		return admin;
	}
}
