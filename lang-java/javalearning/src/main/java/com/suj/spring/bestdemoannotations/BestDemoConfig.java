package com.suj.spring.bestdemoannotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.suj.spring.bestdemoannotations")
public class BestDemoConfig {

    @Autowired
    @Qualifier("bestDemoServiceComponent")
    private BestDemoService bestDemoServiceInstance;

    @Bean(name="bestDemoServiceInstance")
    public BestDemoService getBestDemoService(){
        System.out.println("BestDemoConfig.getBestDemoService() called");
        return bestDemoServiceInstance;
    }

}
