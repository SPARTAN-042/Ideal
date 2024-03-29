package com.spartan.ideal.service;

import com.spartan.ideal.model.Product;
import com.spartan.ideal.repository.ProductRepository;
import com.spartan.ideal.scraping.ProductScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    private final ProductScraper productScraper;

    @Autowired
    public ProductService(ProductScraper productScraper) {
        this.productScraper = productScraper;
    }

    public List<Product> runSelenium(String itemName) {
        try {
            List<Product> amazonProducts = productScraper.scrapeAmazon(itemName);
            List<Product> ebayProducts = productScraper.scrapeEbay(itemName);
            List<Product> onbuyProducts = productScraper.scrapeOnBuy(itemName);

            List<Product> allProducts = new ArrayList<>();
            allProducts.addAll(amazonProducts);
            allProducts.addAll(ebayProducts);
            allProducts.addAll(onbuyProducts);
            return allProducts;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
