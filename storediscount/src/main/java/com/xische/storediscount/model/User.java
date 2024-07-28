package com.xische.storediscount.model;

import java.io.Serializable;
import java.util.Date;

public class  User implements Serializable  {
	protected int userId;
	protected String name;
	protected UserType userType;
	protected Date joinDate;
	

    public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


}

