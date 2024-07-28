package com.xische.storediscount.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xische.storediscount.model.User;

@Component
public class EmployeeDiscount extends DiscountTemplate {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDiscount.class);
	
    @Override
    protected double getPercentageDiscount(User user) {
    	logger.info("EmployeeDiscount");
        return 30; // Employee discount
    }
}
