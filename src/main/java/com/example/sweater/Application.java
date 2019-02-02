package com.example.sweater;

import com.example.sweater.service.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean<Filter> loggingFilter(){
        FilterRegistrationBean<Filter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new Filter());
        registrationBean.addUrlPatterns("/greeting");
        return registrationBean;
    }
}