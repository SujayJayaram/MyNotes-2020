package com.freddieb.springbootdemo.rest;


import com.freddieb.springbootdemo.ioc.Animal;
import com.freddieb.springbootdemo.ioc.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private Animal animal;

    // Autowire directly
    @Autowired
    private Dog dog;

    @RequestMapping("${url.home}")
    public String home() {
        return animal.sayHello();
    }

    @RequestMapping("/${url.part1}/${url.part2}")
    public String home2() {
        return dog.sayHello();
    }
}