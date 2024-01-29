package com.spartan.ideal.controller;

import com.spartan.ideal.model.Product;
import com.spartan.ideal.service.SeleniumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class SeleniumController {

    private final SeleniumService seleniumService;

    @Autowired
    public SeleniumController(SeleniumService seleniumService) {
        this.seleniumService = seleniumService;
    }

    @PostMapping("/run-selenium")
    public String search(@RequestParam String itemName, Model model) {
        List<Product> products = seleniumService.runSelenium(itemName);
        model.addAttribute("products", products);
        return "search-results";
    }
}
