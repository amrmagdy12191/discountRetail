package com.xische.storediscount.model;

import java.io.Serializable;
import java.util.Date;

public class Customer extends User implements Serializable  {
	private int id;
	private String customerSpecialAttributeString;
	
	public Customer() {
	}
	
	public Customer(String name, Date joinDate, String customerSpecialAttributeString) {
		this.name = name;
        this.userType = UserType.CUSTOMER;
        this.joinDate = joinDate;
        this.customerSpecialAttributeString = customerSpecialAttributeString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerSpecialAttributeString() {
		return customerSpecialAttributeString;
	}

	public void setCustomerSpecialAttributeString(String customerSpecialAttributeString) {
		this.customerSpecialAttributeString = customerSpecialAttributeString;
	}

}
