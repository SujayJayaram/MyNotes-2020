package com.freddieb.springbootdemo.rest;


import com.freddieb.springbootdemo.ioc.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private Animal animal;

    @RequestMapping("${url.home}")
    public String home() {
        return animal.sayHello();
    }
}