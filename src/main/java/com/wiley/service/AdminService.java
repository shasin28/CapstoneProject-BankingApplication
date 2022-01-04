package com.wiley.service;

import com.wiley.beans.Admin;

public interface AdminService {

	public Admin getAdminById(String id);
	public Admin checkLoginDetails(String id,String password);
}
