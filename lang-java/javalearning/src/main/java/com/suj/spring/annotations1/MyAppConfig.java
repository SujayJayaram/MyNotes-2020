package com.suj.spring.annotations1;

/**
 * Created by sujayjayaram on 21/02/2016.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {

    @Bean(name="myColorBean")
    public MyColor getMyColors(){
        return new RedColor();
    }

    @Bean
    public MyColor getAllMyColors(){
        // With no name param for @Bean it will create a bean called 'getAllMyColors'!
        return new RedColor();
    }
}
