package com.spartan.ideal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;
@SpringBootApplication
public class IdealApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdealApplication.class, args);
	}

	public static void start() {

		Scanner input = new Scanner(System.in);

		System.out.println("Enter product name: ");

		String itemName = input.nextLine();
	}
}
