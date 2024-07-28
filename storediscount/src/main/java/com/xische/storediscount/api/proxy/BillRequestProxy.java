package com.xische.storediscount.api.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.xische.storediscount.api.request.BillRequest;
import com.xische.storediscount.model.Bill;
import com.xische.storediscount.model.Item;
import com.xische.storediscount.model.ItemCategory;

public class BillRequestProxy {
	
	public static Bill convertBillRequestToBill(BillRequest billRequest) {
	    return Optional.ofNullable(billRequest)
	            .map(request -> {
	                Bill bill = new Bill();
	                bill.setUser(request.getUser());
	                bill.setAmount(request.getAmount());
	                bill.setDate(request.getDate());
	                
	                // adding items
	                List<Item> items = new ArrayList<Item>();
	                billRequest.getItems().forEach(requestItem ->{  	
	                	Item billItem = new Item(requestItem.getName());       	
	                	billItem.setItemCategory(ItemCategory.valueOf(requestItem.getItemCategory().name()));
	                	billItem.setPrice(requestItem.getPrice());
	                	items.add(billItem);
	                });
	                bill.setItems(items);
	                
	                
	                return bill;
	            })
	            .orElse(null);
	}

}
