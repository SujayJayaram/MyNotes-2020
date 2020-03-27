package com.suj.lang.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sujayjayaram on 31/01/2016.
 */
public class CollectionsDemo {

    public static void main (String[] args){
        {
            String array[] = new String[] {
                    "Toyota", "Mercedes", "BMW", "Volkswagen", "Skoda" };

            Integer array2[] = { 1, 2, 3, 4, 5 };

            List<String> list1 = Arrays.asList(array);
            String[] strings = list1.toArray(new String[0]);

            List<Integer> list2 = Arrays.asList(array2);
            Integer[] integers = list2.toArray(new Integer[0]);
        }

        {
            List<Integer> list = Arrays.asList(new Integer(1), new Integer(5), new Integer(6), new Integer(3),
                    new Integer(8), new Integer(2), new Integer(4), new Integer(7));

            // Stream<String> stringStream = list.stream().map(i -> Integer.toString(i));
            Stream<String> stringStream = list.stream().map(i -> Integer.toString(i));

            List<String> collect = stringStream.collect(Collectors.toList());

            int debugMe = 0;

            list.forEach(i -> System.out.println("Element 1: " + i));

            Collections.sort(list);
            list.forEach(i -> System.out.println("Element 2: " + i));

            Integer min = Collections.min(list);
            System.out.println("Min: " + min);

            Integer max = Collections.max(list);
            System.out.println("Max: " + max);

            List<Integer> list2 = Collections.unmodifiableList(list); //Exception in thread "main" java.lang.UnsupportedOperationException
            list2.add(new Integer(10));
            System.out.println("Done");
        }
    }
}
