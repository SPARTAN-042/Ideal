package com.spartan.ideal.controller;

import com.spartan.ideal.model.Product;
import com.spartan.ideal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/search")
    public String search(@RequestParam String itemName, Model model) {
        List<Product> products = productService.runSelenium(itemName);
        model.addAttribute("products", products);
        return "search-results";
    }
}
