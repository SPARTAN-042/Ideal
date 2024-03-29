package com.spartan.ideal.scraping;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class SearchLogic {

    public void performSearch(WebDriver driver, String url, String searchQuery, String searchBox, String searchButton) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.navigate().to(url);
        driver.findElement(By.xpath(searchBox)).sendKeys(searchQuery);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchButton))).click();
    }
}
