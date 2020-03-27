package com.freddieb.springbootdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private ICityService cityService;

    // Need to explicitly state that json returned.
    @GetMapping(value = "/cities", produces = { "application/json" })
    public List<City> getCities() {

        List<City> cities = cityService.findAll();

        return cities;
    }

    // Need jackson-dataformat-xml in pom.xml
    @RequestMapping(value = "/citiesXml", method = RequestMethod.GET, produces = { "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE )
    @ResponseBody
    public List<City> getCitiesXml() {

        List<City> cities = cityService.findAll();

        return cities;
    }
}
