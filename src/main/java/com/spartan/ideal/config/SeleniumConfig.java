package com.spartan.ideal.config;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class SeleniumConfig {

    @Bean
    @Lazy
    public FirefoxDriver webDriver() {
        return new FirefoxDriver();
    }
}
