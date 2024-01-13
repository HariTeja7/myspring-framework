package com.myspring.springdemo.avengers;

import com.myspring.annotation.Autowired;
import com.myspring.annotation.Component;

@Component
public class SpiderMan implements Avenger {

	@Autowired
	private IronMan ironMan;

	@Override
	public String message() {
		return ironMan.message() + "\n" + "I am friendly neighborhood Spider-Man.";
	}

}
