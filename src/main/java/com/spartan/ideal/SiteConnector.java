package com.spartan.ideal;

import com.spartan.ideal.model.Product;
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
import java.util.Collection;
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

    public List<Product> callAmazon(String itemName)  {
        String amazonUrl = "https://www.amazon.co.uk/";
        String searchBoxXpath = "//*[@id=\"twotabsearchtextbox\"]";
        String searchButtonXpath = "//*[@id=\"nav-search-submit-button\"]";

        performSearch(amazonUrl, searchBoxXpath, searchButtonXpath, itemName);

        List<Product> products = new ArrayList<>();

        List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class, 's-result-item s-asin')]"));


        for (WebElement item : items) {
            Product product = new Product();

            product.setImgUrl(item.findElement(By.xpath(".//img[@class = 's-image']")).getAttribute("src"));

            product.setProductName(item.findElement(By.xpath(".//span[contains(@class, 'a-color-base a-text-normal')]")).getText());

            List<WebElement> wholePrices = item.findElements(By.xpath(".//span[@class='a-price-whole']"));
            List<WebElement> fractionPrices = item.findElements(By.xpath(".//span[@class='a-price-fraction']"));

            if (!wholePrices.isEmpty() && !fractionPrices.isEmpty()) {
                String tempPrice = String.join(".", wholePrices.get(0).getText(), fractionPrices.get(0).getText());
                String priceWithoutSymbol = tempPrice.startsWith(".") ? "0" + tempPrice : tempPrice;
                product.setPrice(priceWithoutSymbol);
            } else {
                product.setPrice("0");
            }

            product.setProductLink(item.findElement(By.xpath(".//a[@class = 'a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']")).getAttribute("href"));

            products.add(product);
        }

        return products;
    }

    public List<Product> callEbay(String itemName) {
        String ebayUrl = "https://www.ebay.co.uk/";
        String searchBoxXpath = "//*[@id=\"gh-ac\"]";
        String searchButtonXpath = "//*[@id=\"gh-btn\"]";

        performSearch(ebayUrl, searchBoxXpath, searchButtonXpath, itemName);

        List<Product> products = new ArrayList<>();

        List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class, 's-item__wrapper clearfix')]"));

        for (WebElement item : items) {
            Product product = new Product();

            product.setImgUrl(item.findElement(By.xpath(".//div[@class='s-item__image']/a/div/img")).getAttribute("src"));

            product.setProductName(item.findElement(By.className("s-item__title")).getText());

            List<WebElement> prices = item.findElements(By.cssSelector(".s-item__price"));

            if (!prices.isEmpty()) {
                String tempPrice = prices.get(0).getText();
                if (tempPrice.length() > 1) {
                    String priceWithoutSymbol = tempPrice.substring(1);
                    product.setPrice(priceWithoutSymbol);
                } else {
                    product.setPrice("0");
                }
            } else {
                product.setPrice("0");
            }

            product.setProductLink(item.findElement(By.xpath(".//a[@class='s-item__link']")).getAttribute("href"));

            products.add(product);
        }

        return products;
    }

    public List<Product> callOnbuy(String itemName) {
        String onBuyUrl = "https://www.onbuy.com/gb/";
        String searchBoxXpath = "//*[@id=\"search\"]/fieldset/input";
        String searchButtonXpath = "//*[@id=\"search\"]/fieldset/button";

        performSearch(onBuyUrl, searchBoxXpath, searchButtonXpath, itemName);

        List<Product> products = new ArrayList<>();

        List<WebElement> items = driver.findElements(By.className("product"));

        for (WebElement item : items) {
            Product product = new Product();

            product.setImgUrl(item.findElement(By.xpath("//span[contains(@class, 'image')]/picture/img")).getAttribute("src"));

            product.setProductName(item.findElement(By.className("name")).getText());

            List<WebElement> prices = item.findElements(By.className("value"));

            if (!prices.isEmpty()) {
                String priceWithoutSymbol = prices.get(0).getText().substring(1);
                product.setPrice(priceWithoutSymbol);
            } else {
                product.setPrice("0");
            }

            product.setProductLink(item.findElement(By.xpath("//div[contains(@class, 'product cb')]/a")).getAttribute("href"));

            products.add(product);
        }
        return products;
    }
}
