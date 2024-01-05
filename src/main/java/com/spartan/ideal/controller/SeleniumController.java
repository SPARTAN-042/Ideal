package com.spartan.ideal.controller;

import com.spartan.ideal.service.SeleniumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeleniumController {

    @Autowired
    private SeleniumService seleniumService;

    @PostMapping("/run-selenium")
    public ResponseEntity<String> runSelenium(@RequestParam String itemName) {
        seleniumService.runSelenium(itemName);
        return ResponseEntity.ok("Selenium run successful!");
    }
}
