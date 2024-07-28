package com.xische.storediscount.model;

import java.io.Serializable;

public enum UserType implements Serializable {
	CUSTOMER(1, "Customer"), EMPLOYEE(2, "Employee"), AFFILIATE(3, "Affiliate");
	
	private int code;
	private String description;
	
	private UserType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
