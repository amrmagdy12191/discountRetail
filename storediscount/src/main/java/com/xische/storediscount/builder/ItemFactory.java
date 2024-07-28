package com.xische.storediscount.builder;

import com.xische.storediscount.model.Item;
import com.xische.storediscount.model.ItemCategory;

public class ItemFactory {
	
	public static Item createItem(String name, ItemCategory itemCategory, double price) {
		Item item = new Item(name, itemCategory, price);
		return item;
	}
	
	public static Item createItem(String name) {
		Item item = new Item(name);
		return item;
	}

}
