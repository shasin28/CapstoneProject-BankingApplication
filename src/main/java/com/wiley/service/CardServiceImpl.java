package com.wiley.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.beans.Card;
import com.wiley.dao.CardDao;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDao cardDao;
	@Override
	public Card getCardByNumber(long cardnumber) {
		
		return cardDao.getCardById(cardnumber);
	}
	@Override
	public boolean insertCard(Card c) {
		Card card=cardDao.save(c);
		if(card!=null)
			return true;
		return false;
	}
	@Override
	public Card getCardByDetails(long cardNumber, int cvv) {
		
		return cardDao.getCardByDetails(cardNumber, cvv);
	}

}
