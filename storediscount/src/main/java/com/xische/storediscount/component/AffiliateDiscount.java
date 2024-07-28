package com.xische.storediscount.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xische.storediscount.model.User;

@Component
public class AffiliateDiscount extends DiscountTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(AffiliateDiscount.class);

    @Override
    protected double getPercentageDiscount(User user) {
    	logger.info("AffiliateDiscount");
        return 10; 
    }
}
