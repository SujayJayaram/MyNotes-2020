package com.suj.spring.annotations2.withoutannotations;

/**
 * Created by sujayjayaram on 21/02/2016.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// See - http://www.mkyong.com/spring/spring-auto-scanning-components/
// for example without auto-scan
public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"spring/pre-annotations1.xml"});

        CustomerService cust = (CustomerService)context.getBean("customerService");
        System.out.println(cust);

    }
}