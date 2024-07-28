package com.xische.storediscount.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xische.storediscount.controller.BillController;
import com.xische.storediscount.model.Bill;
import com.xische.storediscount.service.BillService;
import com.xische.storediscount.service.DiscountService;

@Service
public class BillServiceImpl implements BillService {
	
	private static final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);
	
	@Autowired
	DiscountServiceImpl discountService;

	@Override
	public double calculateBillAmount(Bill bill) {
		logger.info("calculateBillAmount started");
		if(bill == null) {
			return 0;
		}
		double discount = discountService.calculateDiscount(bill);
		double totalBillAmountAfterDiscount = bill.getAmount() - discount;

		logger.info("calculateBillAmount -> Bill Amount: {}, discount: {}", bill.getAmount() , discount );
		return totalBillAmountAfterDiscount;
	}

}
