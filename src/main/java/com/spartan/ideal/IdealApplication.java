package com.spartan.ideal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.spartan.ideal")
public class IdealApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdealApplication.class, args);
	}
}
