package com.xische.storediscount.service;

import com.xische.storediscount.model.Bill;

public interface BillService {
	public double calculateBillAmount(Bill bill);
}
