package com.ntl.ata.dao;

import com.ntl.ata.bean.PaymentBean;

public interface PaymentDao {
	
	public boolean findByCardNumber(String userID, String cardNumber);
	public String processPayment(PaymentBean paymentBean);


}
