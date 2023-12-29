package com.spartan.ideal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@SpringBootApplication
public class IdealApplication {

	public static void main(String[] args) {
//		SpringApplication.run(IdealApplication.class, args); Temp disabled to display prototype
		start();


	}

	public static void start() {

		Scanner input = new Scanner(System.in);

		System.out.println("Enter product name: ");

		String itemName = input.nextLine();

		SiteConnector siteConnector = new SiteConnector();

		siteConnector.callAmazon(itemName);
		siteConnector.callEbay(itemName);
	}
}
