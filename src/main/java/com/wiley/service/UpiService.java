package com.wiley.service;

import com.wiley.beans.Upi;

public interface UpiService {

	public Upi getUpiById(String upiId);
	public boolean createUpi(Upi u);
	public Upi getUpiByDetails(String upiId,int pin);
}
