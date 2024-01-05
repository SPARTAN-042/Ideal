package com.spartan.ideal;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfig {

    @Bean
    public FirefoxDriver webDriver() {
        return new FirefoxDriver();
    }
}
