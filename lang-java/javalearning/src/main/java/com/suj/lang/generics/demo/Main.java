package com.suj.lang.generics.demo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // PECS
        // Producer extends
        // Consumer super
        {
            // This add() method is a consume operation so the 'consumer super' rule applies
            List<? super Fruit> exFruitList = new ArrayList<>();
            exFruitList.add(new Apple());

            // This next line gives a compilation error - we can only use Object type
            //Fruit apple = exFruitList.get(0);
            Object apple = exFruitList.get(0);

            // This next line gives a compilation error
            // exFruitList.add(new Plant());

        }

        {
            // The get() method is a produce operation so the 'producer extends' rule applies
            List<? extends Fruit> exFruitList = new ArrayList<>();

            // These next lines give a compilation error
            // exFruitList.add(new Apple());
            // exFruitList.add(new Plant());

            Fruit fruit = exFruitList.get(0);
        }

        // ******* But for both the above, we need to ask why we would do this - only uses bounded wildcards when passing through to methods
        {
            List<Fruit> fruitsList = new ArrayList<>();
            fruitsList.add(new Apple());
            acceptList(fruitsList);
            acceptList2(fruitsList);
        }

        {
            List<Plant> plantsList = new ArrayList<>();
            plantsList.add(new Apple());
            // Will not compile
            // acceptList(plantsList);
            acceptList2(plantsList);
        }

        {
            List<Apple> applesList = new ArrayList<>();
            applesList.add(new Apple());
            acceptList(applesList);
            // acceptList2(applesList);
        }
    }


    // Accepts List<Apple> and List<Fruit> but NOT List<Plant>
    private static void acceptList(List<? extends Fruit> list) {
        Fruit fruit = list.get(0);

        // Will not compile
        // list.add(new Apple());
        // list.add(new Fruit());
        // list.add(new Plant());
    }

    private static void acceptList2(List<? super Fruit> list) {
        list.add(new Apple());
        list.add(new Fruit());

        // Will not compile
        // list.add(new Plant());
    }
}
