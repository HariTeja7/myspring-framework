package com.myspring.springdemo.avengers;

import com.myspring.annotation.Component;

@Component
public class HulkBuster extends IronMan {

	@Override
	public String message() {
		return "I'm Iron Man! Let's get this show on the road";
	}

}
