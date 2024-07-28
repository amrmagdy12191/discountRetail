package com.xische.storediscount.model;

import java.io.Serializable;

public class Item implements Serializable {
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
