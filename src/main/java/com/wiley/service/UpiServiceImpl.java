package com.wiley.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.beans.Upi;
import com.wiley.dao.UpiDao;
@Service
public class UpiServiceImpl implements UpiService {

	@Autowired
	private UpiDao upiDao;
	@Override
	public Upi getUpiById(String upiId) {
		Upi upi=upiDao.getUpiByid(upiId);
		if(upi==null)
		{
			return null;
		}
		return upi;
	}
	@Override
	public boolean createUpi(Upi u) {
		Upi upi=upiDao.save(u);
		if(upi!=null)
			return true; 
		return false;
	}
	@Override
	public Upi getUpiByDetails(String upiId, int pin) {
		// TODO Auto-generated method stub
		return upiDao.getUpiByDetails(upiId, pin);
	}

}
