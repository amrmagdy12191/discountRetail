package com.xische.storediscount.api.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.xische.storediscount.model.User;

public class BillRequest implements Serializable {
	private int id;
    private double amount;
    private Date date;
    private User user;
    private List<Item> items;
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public static class Item {
    	private int id;
    	private String name;
    	private ItemCategory itemCategory;
    	private double price;
    	
    	public Item(String name) {	
    		this.name = name;
    	}
    	
    	public Item(String name, ItemCategory itemCategory, double price) {
    		this.name = name;
    		this.itemCategory = itemCategory;
    		this.price = price;
    	}

    	public int getId() {
    		return id;
    	}

    	public void setId(int id) {
    		this.id = id;
    	}

    	public String getName() {
    		return name;
    	}

    	public void setName(String name) {
    		this.name = name;
    	}

    	public ItemCategory getItemCategory() {
    		return itemCategory;
    	}

    	public void setItemCategory(ItemCategory itemCategory) {
    		this.itemCategory = itemCategory;
    	}

    	public double getPrice() {
    		return price;
    	}

    	public void setPrice(double price) {
    		this.price = price;
    	}
    	
    }
    
    public static enum ItemCategory {
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

}
