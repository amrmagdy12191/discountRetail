package com.xische.storediscount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xische.storediscount.component.AffiliateDiscount;
import com.xische.storediscount.component.CustomerDiscount;
import com.xische.storediscount.component.DefaultDiscount;
import com.xische.storediscount.component.DiscountTemplate;
import com.xische.storediscount.component.EmployeeDiscount;
import com.xische.storediscount.model.Bill;
import com.xische.storediscount.model.User;
import com.xische.storediscount.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {
	
	@Autowired
	CustomerDiscount customerDiscount;
	
	@Autowired
	AffiliateDiscount affiliateDiscount;
	
	@Autowired
	EmployeeDiscount employeeDiscount;
	
	@Autowired
	DefaultDiscount defaultDiscount;

    @Override
    public double calculateDiscount(Bill bill) {
        DiscountTemplate discountTemplate = getDiscountTemplate(bill.getUser());
        return discountTemplate.calculateDiscount(bill);
    }

    private DiscountTemplate getDiscountTemplate(User user) {
    	return switch(user.getUserType()) {
    		case EMPLOYEE -> employeeDiscount;
    		case CUSTOMER -> customerDiscount;
    		case AFFILIATE -> affiliateDiscount;
    		default -> defaultDiscount;
    	};
    }
}
