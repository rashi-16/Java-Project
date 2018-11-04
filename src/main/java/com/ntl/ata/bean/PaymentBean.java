package com.ntl.ata.bean;

public class PaymentBean {
	
	private String cardNumber;
	private String validFrom;
	private String validTo;
	private double CreditBalance;
	
	public PaymentBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentBean(String cardNumber, String validFrom, String validTo, double creditBalance) {
		super();
		this.cardNumber = cardNumber;
		this.validFrom = validFrom;
		this.validTo = validTo;
		CreditBalance = creditBalance;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public double getCreditBalance() {
		return CreditBalance;
	}

	public void setCreditBalance(double creditBalance) {
		CreditBalance = creditBalance;
	}
	
	
	
	
}
