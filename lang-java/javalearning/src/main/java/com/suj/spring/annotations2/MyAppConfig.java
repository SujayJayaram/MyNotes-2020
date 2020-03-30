package com.suj.spring.annotations2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.suj.spring.annotations2")
public class MyAppConfig {

    @Autowired
    @Qualifier("customerServiceAutowired")
    private CustomerService customerService;

    @Bean(name="customerService2")
    public CustomerService getCustomerService(){
        System.out.println("MyAppConfig.getCustomerService() called");
        return customerService;
    }

}
