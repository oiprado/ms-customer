package com.trinity.ms.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.trinity.commons.config", "com.trinity.ms.customer"})
@EntityScan({ "com.trinity.commons.model" })
public class CustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
}
