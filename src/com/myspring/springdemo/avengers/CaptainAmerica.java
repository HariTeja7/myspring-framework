package com.myspring.springdemo.avengers;

import com.myspring.annotation.Component;

@Component
public class CaptainAmerica implements Avenger {

	@Override
	public String message() {
		return "I CAN DO THIS ALL DAY.";
	}

}
