package com.suj.spring.helloworld;

/**
 * Created by sujayjayaram on 21/02/2016.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {

    // Simplest case hello world program
    public static void main(String a[]){
        String confFile = "spring/helloworld.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(confFile);
        SpringFirstTest sft = (SpringFirstTest) context.getBean("springTest");
        sft.testMe();
    }
}

