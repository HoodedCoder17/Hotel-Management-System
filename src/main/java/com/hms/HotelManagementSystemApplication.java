package com.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableJpaRepositories(basePackages= {"com.hms.repositories"})
@ComponentScan(basePackages = { "com.hms.controllers", "com.hms.services" ,"com.hms.config", "com.hms.entities"})
@EnableScheduling
public class HotelManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementSystemApplication.class, args);
	}
	
}
