package com.spartan.ideal.scraping;

import com.spartan.ideal.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductParser {

    public List<Product> parseProducts(WebDriver driver, String website, String searchQuery) {
        return switch (website) {
            case "amazon" -> parseAmazonProducts(driver, searchQuery);
            case "ebay" -> parseEbayProducts(driver, searchQuery);
            case "onbuy" -> parseOnBuyProducts(driver, searchQuery);
            default -> throw new IllegalArgumentException("Unsupported website: " + website);
        };
    }

    /*
        General operation of each product parser:
            - Products array created
            - A List of WebElements are found at the appropriate path on the page
            - Each item is iterated over with a new product object being instantiated
            - The image, product name, price and link are set to the object
            - Finally the product object is added to the products array and the products array is returned
     */

    private List<Product> parseAmazonProducts(WebDriver driver, String searchQuery) {
        List<Product> products = new ArrayList<>();
        List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class, 's-result-item s-asin')]"));


        for (WebElement item : items) {
            Product product = new Product();

            WebElement imgElement = item.findElement(By.xpath(".//img[contains(@class, 's-image')]"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(imgElement));

            product.setImgUrl(imgElement.getAttribute("src"));

            String productName = item.findElement(By.xpath(".//span[contains(@class, 'a-color-base a-text-normal')]")).getText();
            if (isValidTitle(productName, searchQuery)) {
                product.setProductName(productName);
            } else {
                continue;
            }

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
    private List<Product> parseEbayProducts (WebDriver driver, String searchQuery){
        List<Product> products = new ArrayList<>();
        List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class, 's-item__wrapper clearfix')]"));

        for (WebElement item : items) {
            Product product = new Product();

            product.setImgUrl(item.findElement(By.xpath(".//div[@class='s-item__image']/a/div/img")).getAttribute("src"));

            String productName = item.findElement(By.className("s-item__title")).getText();
            if (isValidTitle(productName, searchQuery)) {
                product.setProductName(productName);
            } else {
                continue;
            }

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

    private List<Product> parseOnBuyProducts (WebDriver driver, String searchQuery){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<Product> products = new ArrayList<>();
        /*
            The individual products are captured and saved to the list, Selenium waits until the expected element has loaded on the page and is visible for scraping.
            Additionally, sponsored items are filtered out from this site to reduce irrelevant items from being listed.
        */
        List<WebElement> items =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'product') and not(contains(@class, 'sponsored'))]")));

        for (WebElement item : items) {
            Product product = new Product();

            WebElement imgElement = item.findElement(By.xpath("//span[@class='image']/picture/img"));

            wait.until(ExpectedConditions.visibilityOf(imgElement));

            product.setImgUrl(imgElement.getAttribute("src"));

            String productName = item.findElement(By.xpath("//span[@class='name']")).getText();
            if (isValidTitle(productName, searchQuery)) {
                product.setProductName(productName);
            } else {
                continue;
            }

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

    /*
        Further filtering, item names are checked if the name does not match the search query in some way, or the name is empty, the product is skipped.
     */
    private boolean isValidTitle(String title, String itemName) {
        return title.contains(itemName) && !title.isEmpty();
    }
}
