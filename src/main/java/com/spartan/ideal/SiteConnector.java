package com.spartan.ideal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class SiteConnector {

    private final FirefoxDriver driver;

    @Autowired
    @Lazy
    public SiteConnector(FirefoxDriver driver) {
        this.driver = driver;
    }

    private void performSearch(String url, String searchBoxXpath, String searchButtonXpath, String itemName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.navigate().to(url);

        driver.findElement(By.xpath(searchBoxXpath)).sendKeys(itemName);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchButtonXpath)));

        driver.findElement(By.xpath(searchButtonXpath)).click();
    }

    public void callAmazon(String itemName)  {
        String ebayUrl = "https://www.amazon.co.uk/";
        String searchBoxXpath = "//*[@id=\"twotabsearchtextbox\"]";
        String searchButtonXpath = "//*[@id=\"nav-search-submit-button\"]";

        performSearch(ebayUrl, searchBoxXpath, searchButtonXpath, itemName);

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
        String ebayUrl = "https://www.ebay.co.uk/";
        String searchBoxXpath = "//*[@id=\"gh-ac\"]";
        String searchButtonXpath = "//*[@id=\"gh-btn\"]";

        performSearch(ebayUrl, searchBoxXpath, searchButtonXpath, itemName);

        List<String> productName = new ArrayList<>();
        List<String> productImage = new ArrayList<>();
        List<String> productPrice = new ArrayList<>();
        List<String> productLink = new ArrayList<>();

        List<WebElement> items = driver.findElements(By.xpath(""));

        WebElement img;
        WebElement name;
        List<WebElement> wholePrice;
        List<WebElement> fractionPrice;
        String price;
        WebElement link;

        for (WebElement item : items) {

            img = item.findElement(By.xpath(""));
            productImage.add(img.getAttribute(""));

            name = item.findElement(By.xpath(""));
            productName.add(name.getText());

            wholePrice = item.findElements(By.xpath(""));
            fractionPrice = item.findElements(By.xpath(""));

        }

        System.out.printf("eBay - Image link: %s\nProduct name: %s\nProduct price: %s\nProduct link: %s\n", productImage, productName, productPrice, productLink);
    }

    public void callOnbuy(String itemName) {
        String onBuyUrl = "https://www.onbuy.com/gb/";
        String searchBoxXpath = "";
        String searchButtonXpath = "";

        performSearch(onBuyUrl, searchBoxXpath, searchButtonXpath, itemName);

        List<String> productName = new ArrayList<>();
        List<String> productImage = new ArrayList<>();
        List<String> productPrice = new ArrayList<>();
        List<String> productLink = new ArrayList<>();

        List<WebElement> items = driver.findElements(By.xpath(""));

        WebElement img;
        WebElement name;
        List<WebElement> wholePrice;
        List<WebElement> fractionPrice;
        String price;
        WebElement link;

        for (WebElement item : items) {

            img = item.findElement(By.xpath(""));
            productImage.add(img.getAttribute(""));

            name = item.findElement(By.xpath(""));
            productName.add(name.getText());

            wholePrice = item.findElements(By.xpath(""));
            fractionPrice = item.findElements(By.xpath(""));

            if (!wholePrice.isEmpty() && !fractionPrice.isEmpty()) {
                price = String.join(".",wholePrice.get(0).getText(), fractionPrice.get(0).getText());
            } else {
                price = "0";

            }
            productPrice.add(price);


            link = item.findElement(By.xpath(".//a[@class = 'a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
            productLink.add(link.getAttribute("href"));

        }

        System.out.printf("OnBuy - Image link: %s\nProduct name: %s\nProduct price: %s\nProduct link: %s\n", productImage, productName, productPrice, productLink);
    }
}
