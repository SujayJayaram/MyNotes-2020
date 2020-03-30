package com.suj.spring.bestdemoannotations;

/**
 * Created by sujayjayaram on 21/02/2016.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BestDemoApp
{
    public static void main( String[] args )
    {
        // Use this type of Application Context when using annotations
        ApplicationContext context = new AnnotationConfigApplicationContext(BestDemoConfig.class);
        BestDemoService instance = (BestDemoService)context.getBean("bestDemoServiceInstance");
        System.out.println(instance);
    }
}