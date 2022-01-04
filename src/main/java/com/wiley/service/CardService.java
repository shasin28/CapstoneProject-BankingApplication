package com.wiley.service;

import com.wiley.beans.Card;

public interface CardService {

	public Card getCardByNumber(long cardnumber);
	public boolean insertCard(Card c);
	public Card getCardByDetails(long cardNumber,int cvv);
}
