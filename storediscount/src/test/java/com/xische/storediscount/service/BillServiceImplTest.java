package com.xische.storediscount.service;

import com.xische.storediscount.builder.UserFactory;
import com.xische.storediscount.model.*;
import com.xische.storediscount.service.impl.BillServiceImpl;
import com.xische.storediscount.service.impl.DiscountServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BillServiceImplTest {

    @InjectMocks
    private BillServiceImpl billService;

    @Mock
    private DiscountServiceImpl discountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateBillAmount_EmployeeDiscount() {
    	
        Bill bill = createBillWithUserType(UserType.EMPLOYEE);
        when(discountService.calculateDiscount(bill)).thenReturn(20.0);
        double totalAmount = billService.calculateBillAmount(bill);
        assertEquals(80, totalAmount);
    }

    @Test
    public void testCalculateBillAmount_AffiliateDiscount() {
        Bill bill = createBillWithUserType(UserType.AFFILIATE);
        when(discountService.calculateDiscount(bill)).thenReturn(10.0);
        double totalAmount = billService.calculateBillAmount(bill);
        assertEquals(90.0, totalAmount);
    }

    @Test
    public void testCalculateBillAmount_LoyalCustomerDiscount() {
        Bill bill = createBillWithLoyalCustomer();
        when(discountService.calculateDiscount(bill)).thenReturn(7.5);
        double totalAmount = billService.calculateBillAmount(bill);
        assertEquals(92.5, totalAmount);
    }

    @Test
    public void testCalculateBillAmount_WithCustomerDefaultDiscount() {
        Bill bill = createBillWithGeneralDiscount();
        when(discountService.calculateDiscount(bill)).thenReturn(5.0);
        double totalAmount = billService.calculateBillAmount(bill);
        assertEquals(95.0, totalAmount);
    }

    @Test
    public void testCalculateBillAmount_PercentageDiscountDoesNotApplyOnGroceries() {
        Bill bill = createBillWithGroceries();
        when(discountService.calculateDiscount(bill)).thenReturn(5.0);
        double totalAmount = billService.calculateBillAmount(bill);
        assertEquals(145.0, totalAmount);
    }
    
    @Test
    public void testCalculateBillAmount_NoDiscount() {
        Bill bill = createBillWithNoDiscount();
        when(discountService.calculateDiscount(bill)).thenReturn(0.0);
        double totalAmount = billService.calculateBillAmount(bill);
        assertEquals(95, totalAmount);
    }

    private Bill createBillWithUserType(UserType userType) {
        User user = UserFactory.createUser(userType);
        user.setUserType(userType);
        user.setJoinDate(new Date());
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setItems(Arrays.asList(
                new Item("item1", ItemCategory.OTHERS, 50.0),
                new Item("item2", ItemCategory.GROCERY, 50.0)
        ));
        bill.setAmount(100.0);
        return bill;
    }

    private Bill createBillWithLoyalCustomer() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -3); // Customer joined 3 years ago
        User user = UserFactory.createUser(UserType.CUSTOMER);
        user.setUserType(UserType.CUSTOMER);
        user.setJoinDate(calendar.getTime());
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setItems(Arrays.asList(
                new Item("item1", ItemCategory.OTHERS, 50.0),
                new Item("item2", ItemCategory.GROCERY, 50.0)
        ));
        bill.setAmount(100.0);
        return bill;
    }

    private Bill createBillWithGeneralDiscount() {
        User user = UserFactory.createUser(UserType.CUSTOMER);
        user.setJoinDate(new Date()); // no percentage discount
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setItems(Arrays.asList(
                new Item("item1", ItemCategory.OTHERS, 95.0),
                new Item("item2", ItemCategory.GROCERY, 5.0)
        ));
        bill.setAmount(100.0);
        return bill;
    }

    private Bill createBillWithGroceries() {
    	// no percentage discount
        User user = UserFactory.createUser(UserType.EMPLOYEE);
        user.setJoinDate(new Date());
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setItems(Arrays.asList(
                new Item("item1", ItemCategory.GROCERY, 50.0),
                new Item("item2", ItemCategory.GROCERY, 50.0),
                new Item("item3", ItemCategory.GROCERY, 50.0)
        ));
        bill.setAmount(150.0);
        return bill;
    }
    
    private Bill createBillWithNoDiscount() {
        User user = UserFactory.createUser(UserType.CUSTOMER);
        user.setJoinDate(new Date()); // no percentage discount
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setItems(Arrays.asList(
                new Item("item1", ItemCategory.OTHERS, 90.0),
                new Item("item2", ItemCategory.GROCERY, 5.0)
        ));
        bill.setAmount(95.0); // no fixed discount
        return bill;
    }
}


