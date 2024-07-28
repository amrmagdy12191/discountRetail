package com.xische.storediscount.model;

import java.io.Serializable;

public enum ItemCategory implements Serializable {
	GROCERY(1, "Grocery"), OTHERS(2, "Others");
	
	private int code;
	private String description;
	
	private ItemCategory(int code, String description) {
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
