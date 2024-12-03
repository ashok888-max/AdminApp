package com.app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminAppApplication {

	/*
	 * @Bean
	 * 
	 * @Primary public RestTemplate getTemplate() {
	 * 
	 * return new RestTemplate(); }
	 */

	public static void main(String[] args) {
		SpringApplication.run(AdminAppApplication.class, args);
	}

}
