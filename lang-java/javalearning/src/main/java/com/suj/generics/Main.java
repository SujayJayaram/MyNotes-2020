package com.suj.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Apple> applesList = new ArrayList<>();
        applesList.add(new Apple());

        List<? super Fruit> exFruitList = new ArrayList<>();
        exFruitList.add(new Apple());


        //Fruit apple = exFruitList.get(0);

        List<String> l1 = Arrays.asList("1", "2", "3");
    }
}
