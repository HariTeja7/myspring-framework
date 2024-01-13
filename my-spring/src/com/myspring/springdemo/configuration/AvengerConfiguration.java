package com.myspring.springdemo.configuration;

import com.myspring.annotation.Bean;
import com.myspring.annotation.Configuration;
import com.myspring.springdemo.avengers.Avenger;

@Configuration
public class AvengerConfiguration {

	@Bean
	public Avenger hulk() {
		return () -> "Grrrrrrrrrrrrrrrrr grrrrrrrrrrrrrrrrrrrrr";
	}

}
