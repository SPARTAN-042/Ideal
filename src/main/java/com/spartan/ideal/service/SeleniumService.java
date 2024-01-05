package com.spartan.ideal.service;

import com.spartan.ideal.SiteConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeleniumService {

    @Autowired
    private SiteConnector siteConnector;

    public void runSelenium(String itemName) {
        siteConnector.callAmazon(itemName);
        siteConnector.callEbay(itemName);
        siteConnector.callOnbuy(itemName);
    }
}
