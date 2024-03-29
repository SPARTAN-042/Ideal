package com.spartan.ideal.scraping;

import jakarta.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class WebDriverManager {
    private final WebDriver driver;

    @Autowired
    public WebDriverManager() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("javascriptEnabled", true);
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @PreDestroy
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
