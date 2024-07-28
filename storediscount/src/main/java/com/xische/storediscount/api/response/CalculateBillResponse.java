package com.xische.storediscount.api.response;

public class CalculateBillResponse {
	double amount;
	
	public CalculateBillResponse(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
