package com.suj.spring.annotations1;

/**
 * Created by sujayjayaram on 21/02/2016.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


// See http://www.java2novice.com/spring/spring-java-based-configuration/
public class SpringDemo {

    // Uses the MyAppConfig class (@Configuration) for config instead of a file.
    // Needed to upgrade to Spring 4.0.5.RELEASE for this to work.
    public static void main(String a[]){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyAppConfig.class);
        // MyColor color = (MyColor) context.getBean("myColorBean");
        MyColor color = (MyColor) context.getBean("getAllMyColors"); // This works too!
        color.printColor();
    }
}