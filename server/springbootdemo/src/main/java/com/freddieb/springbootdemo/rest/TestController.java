package com.freddieb.springbootdemo.rest;


import com.freddieb.springbootdemo.ioc.Animal;
import com.freddieb.springbootdemo.ioc.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/myroot")
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

    @GetMapping("/foos/{id}")
    @ResponseBody
    public String getFooById(@PathVariable String id) {
        return "ID: " + id;
    }

    // http://localhost:8081/myroot/foos/1/2
    @GetMapping("/foos/{id}/{id2}")
    @ResponseBody
    public String getFooById(@PathVariable String id, @PathVariable String id2) {
        return "ID: " + id + " ID2: " + id2;
    }


    // http://localhost:8081/myroot/foos?id3=test
    @GetMapping("/foos")
    @ResponseBody
    public String getFooByIdUsingQueryParam(@RequestParam String id3) {
        return "ID3: " + id3;
    }
}