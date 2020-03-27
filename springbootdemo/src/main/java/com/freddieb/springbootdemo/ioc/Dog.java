package com.freddieb.springbootdemo.ioc;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {
    @Override
    public String sayHello() {
        return "Woof";
    }
}
