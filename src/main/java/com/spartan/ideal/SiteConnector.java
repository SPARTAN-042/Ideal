package com.spartan.ideal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SiteConnector {

    FirefoxDriver driver = new FirefoxDriver();

    public void callAmazon(String itemName)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.navigate().to("https://www.amazon.co.uk/");

        driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys(itemName);

        String searchButton = "//*[@id=\"nav-search-submit-button\"]";

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchButton)));

        driver.findElement(By.xpath(searchButton)).click();

        List<String> productName = new ArrayList<>();
        List<String> productImage = new ArrayList<>();
        List<String> productPrice = new ArrayList<>();
        List<String> productLink = new ArrayList<>();

        List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class, 's-result-item s-asin')]"));

        WebElement img;
        WebElement name;
        List<WebElement> wholePrice;
        List<WebElement> fractionPrice;
        String price;
        WebElement link;
        for (WebElement item : items) {

            img = item.findElement(By.xpath(".//img[@class = 's-image']"));
            productImage.add(img.getAttribute("src"));

            name = item.findElement(By.xpath(".//span[contains(@class, 'a-color-base a-text-normal')]"));
            productName.add(name.getText());

            wholePrice = item.findElements(By.xpath(".//span[@class='a-price-whole']"));
            fractionPrice = item.findElements(By.xpath(".//span[@class='a-price-fraction']"));
            if (!wholePrice.isEmpty() && !fractionPrice.isEmpty()) {
                price = String.join(".",wholePrice.get(0).getText(), fractionPrice.get(0).getText());
            } else {
                price = "0";

            }
            productPrice.add(price);


            link = item.findElement(By.xpath(".//a[@class = 'a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
            productLink.add(link.getAttribute("href"));
        }

        System.out.printf("Image link: %s\nProduct name: %s\nProduct price: %s\nProduct link: %s\n", productImage, productName, productPrice, productLink);
    }

    public void callEbay(String itemName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.navigate().to("https://www.newegg.com/global/uk-en/");

        driver.findElement(By.xpath("//*[@id=\"gh-ac\"]")).sendKeys(itemName);

        String searchButton = "//*[@id=\"gh-btn\"]";

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchButton)));


    }
}
