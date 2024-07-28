package com.xische.storediscount.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xische.storediscount.model.User;
import com.xische.storediscount.service.UserService;

@Component
public class CustomerDiscount extends DiscountTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDiscount.class);

	@Autowired
	UserService userService;
	
    @Override
    protected double getPercentageDiscount(User user) {
    	double discount = 0;
    	if(userService.calculateUserJoiningYears(user) >= 2)
    		discount = 5; // Long-term customer discount
    	logger.info("CustomerDiscount : " + discount);
    	return discount;
    }
}
