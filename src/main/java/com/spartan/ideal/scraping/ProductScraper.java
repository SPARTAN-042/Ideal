package com.spartan.ideal.scraping;

import com.spartan.ideal.model.Product;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductScraper {

    private final WebDriverManager webDriverManager;
    private final SearchLogic searchLogic;
    private final ProductParser productParser;

    @Autowired
    public ProductScraper(WebDriverManager webDriverManager, SearchLogic searchLogic, ProductParser productParser) {
        this.webDriverManager = webDriverManager;
        this.searchLogic = searchLogic;
        this.productParser = productParser;
    }

    public List<Product> scrapeAmazon(String itemName) {
        String amazonUrl = "https://www.amazon.co.uk/";
        String searchBoxXpath = "//*[@id=\"twotabsearchtextbox\"]";
        String searchButtonXpath = "//*[@id=\"nav-search-submit-button\"]";
        WebDriver driver = webDriverManager.getDriver();
        try {
            searchLogic.performSearch(driver, amazonUrl, itemName, searchBoxXpath, searchButtonXpath);
            return productParser.parseProducts(driver, "amazon", itemName);
        } finally {
        }
    }

    public List<Product> scrapeEbay(String itemName) {
        String ebayUrl = "https://www.ebay.co.uk/";
        String searchBoxXpath = "//*[@id=\"gh-ac\"]";
        String searchButtonXpath = "//*[@id=\"gh-btn\"]";
        WebDriver driver = webDriverManager.getDriver();
        try {
            searchLogic.performSearch(driver, ebayUrl, itemName, searchBoxXpath, searchButtonXpath);
            return productParser.parseProducts(driver, "ebay", itemName);
        } finally {
        }
    }

    public List<Product> scrapeOnBuy(String itemName) {
        String onBuyUrl = "https://www.onbuy.com/gb/";
        String searchBoxXpath = "//*[@id=\"search\"]/fieldset/input";
        String searchButtonXpath = "//*[@id=\"search\"]/fieldset/button";
        WebDriver driver = webDriverManager.getDriver();
        try {
            searchLogic.performSearch(driver, onBuyUrl, itemName, searchBoxXpath, searchButtonXpath);
            return productParser.parseProducts(driver, "onbuy", itemName);
        } finally {
        }
    }
}
