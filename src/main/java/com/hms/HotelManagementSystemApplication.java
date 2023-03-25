package com.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(
/*
 * exclude = { DataSourceAutoConfiguration.class,
 * HibernateJpaAutoConfiguration.class }
 */
						 )
@EnableJpaRepositories(basePackages= {"com.hms.repositories"})
@ComponentScan(basePackages = { "com.hms.controllers", "com.hms.services" ,"com.hms.config", "com.hms.entities"})
public class HotelManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementSystemApplication.class, args);
	}
	
	/*
	 * @Bean public PasswordEncoder BCryptEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */

}
