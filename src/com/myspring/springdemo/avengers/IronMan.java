package com.myspring.springdemo.avengers;

import com.myspring.annotation.Component;

@Component
public class IronMan implements Avenger {

	@Override
	public String message() {
		return "I love you 3,000.";
	}

}
