package com.xische.storediscount.builder;

import java.sql.Date;

import com.xische.storediscount.model.Affiliate;
import com.xische.storediscount.model.Customer;
import com.xische.storediscount.model.Employee;
import com.xische.storediscount.model.User;
import com.xische.storediscount.model.UserType;

public class UserFactory {
	
	public static Customer createCustomer(String name, Date joinDate, String customerSpecialAttributeString) {
		Customer customer = new Customer(name, joinDate, customerSpecialAttributeString);
		return customer;
	}
	
	public static Employee createEmployee(String name, Date joinDate, double salary) {
		Employee employee = new Employee(name, joinDate, salary);
		return employee;
	}
	
	public static Affiliate createAffiliate(String name, Date joinDate, String affiliateType) {
		Affiliate affiliate = new Affiliate(name, joinDate, affiliateType);
		return affiliate;
	}
	
	public static User createUser(UserType userType) {
		User user = switch(userType) {
						case CUSTOMER -> (User)new Customer();
						case AFFILIATE -> (User)new Affiliate();
						case EMPLOYEE -> (User)new Employee();
						default -> null;
					};
		return user;
	}

}
