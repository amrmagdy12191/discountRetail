package com.xische.storediscount.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Bill implements Serializable  {
	private int id;
    private double amount;
    private Date date;
    private User user;
    private List<Item> items;

    public Bill() {
    }

    public double getAmount() {
        return amount;
    }

    public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}
