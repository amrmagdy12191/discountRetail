package com.xische.storediscount.service;

import com.xische.storediscount.model.Bill;

public interface DiscountService {
    double calculateDiscount(Bill bill);
}
