package com.xische.storediscount.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.xische.storediscount.model.User;
import com.xische.storediscount.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public int calculateUserJoiningYears(User user) {
		LocalDate currentDate = LocalDate.now();		
		LocalDate userJoiningDate = user.getJoinDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return (int) ChronoUnit.YEARS.between(userJoiningDate, currentDate);
	}

}
