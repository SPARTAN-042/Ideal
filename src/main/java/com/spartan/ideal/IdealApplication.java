package com.spartan.ideal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/* The @SpringBootApplication annotation is condensed full of other annotations for configuration
	These include:
	@Configuration
	@EnableAutoConfiguration
	@ComponentScan
 */
@SpringBootApplication
@EntityScan(basePackages = "com.spartan.ideal")
public class IdealApplication {

	//The entry point for the program
	public static void main(String[] args) {
		SpringApplication.run(IdealApplication.class, args);
	}
}
