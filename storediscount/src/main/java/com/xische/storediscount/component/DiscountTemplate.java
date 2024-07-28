package com.xische.storediscount.component;

import java.util.Optional;

import com.xische.storediscount.model.Bill;
import com.xische.storediscount.model.Item;
import com.xische.storediscount.model.ItemCategory;
import com.xische.storediscount.model.User;

public abstract class DiscountTemplate {

    protected abstract double getPercentageDiscount(User user);
    
    public double calculateDiscount(Bill bill) {
        double percentage = getPercentageDiscount(bill.getUser());
        
        double applicableItemsAmountForDiscount = calculateFixedDiscountedAmount(bill);
        
        double fixedDiscountAmount = calculateBillAmountDiscount(bill);;
       
        
        double discountAmount = applicableItemsAmountForDiscount * percentage / 100;       
        
        
        double totalDiscountAmount = discountAmount + fixedDiscountAmount;
        
        return totalDiscountAmount;     
    }
    
    private double calculateFixedDiscountedAmount(Bill bill){
    	return Optional.ofNullable(bill.getItems()).map(items -> items.stream()
	    			.filter(item -> !ItemCategory.GROCERY.equals(item.getItemCategory()))
	    			.mapToDouble(Item::getPrice)
	    			.sum())
	    			.orElse(0.0);
    }

    private int calculateBillAmountDiscount(Bill bill) {
        return ((int)bill.getAmount() / 100) * 5;
    }
}