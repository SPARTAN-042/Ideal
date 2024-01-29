package com.spartan.ideal.service;

import com.spartan.ideal.SiteConnector;
import com.spartan.ideal.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeleniumService {


    private final SiteConnector siteConnector;

    @Autowired
    public SeleniumService(SiteConnector siteConnector) {
        this.siteConnector = siteConnector;
    }

   public List<Product> runSelenium(String itemName) {

       List<Product> products = new ArrayList<>();
       products.addAll(siteConnector.callAmazon(itemName));
       products.addAll(siteConnector.callEbay(itemName));
       products.addAll(siteConnector.callOnbuy(itemName));

       return products;
   }
}
