package com.freddieb.springbootdemo.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // Alternatively use the name attribute here and in @Autowired annotaion.
public class Cat implements Animal {
    @Value("${miaow.text}") // See props file
    private String miaowName;

    @Override
    public String sayHello() {
        return miaowName;
    }
}
