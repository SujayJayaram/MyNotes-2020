package com.suj.spring.annotations2;

/**
 * Created by sujayjayaram on 21/02/2016.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// See - http://www.mkyong.com/spring/spring-auto-scanning-components/
// for example without auto-scan
public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyAppConfig.class);

        // By default, Spring will lower case the first character of the component –
        // from ‘CustomerService’ to ‘customerService’. And you can retrieve this component with name ‘customerService’.
        CustomerService cust = (CustomerService)context.getBean("customerService2");
        System.out.println(cust);

    }
}