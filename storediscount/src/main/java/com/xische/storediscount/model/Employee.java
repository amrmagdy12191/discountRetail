package com.xische.storediscount.model;

import java.io.Serializable;
import java.util.Date;

public class Employee extends User implements Serializable {
	private int id;
	private double salary;
	private int discountsNumber;
	
	public Employee() {
	}
	
	public Employee(String name, Date joinDate, double salary) {
		this.name = name;
		this.userType = UserType.EMPLOYEE;
        this.joinDate = joinDate;
        this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getDiscountsNumber() {
		return discountsNumber;
	}

	public void setDiscountsNumber(int discountsNumber) {
		this.discountsNumber = discountsNumber;
	}

}
