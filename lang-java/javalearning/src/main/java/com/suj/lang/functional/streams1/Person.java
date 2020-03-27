package com.suj.lang.functional.streams1;

/**
 * Created by sujayjayaram on 29/01/2016.
 */
public class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}

