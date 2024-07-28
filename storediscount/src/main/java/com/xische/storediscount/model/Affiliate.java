package com.xische.storediscount.model;

import java.io.Serializable;
import java.util.Date;

public class Affiliate extends User implements Serializable {
	private int id;
	private String affiliateType;
	
	public Affiliate() {
	}
	
	public Affiliate(String name, Date joinDate, String affiliateType) {
		this.name = name;
		this.userType = UserType.AFFILIATE;
        this.joinDate = joinDate;
        this.affiliateType = affiliateType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAffiliateType() {
		return affiliateType;
	}

	public void setAffiliateType(String affiliateType) {
		this.affiliateType = affiliateType;
	}
	
	
	
	
}
