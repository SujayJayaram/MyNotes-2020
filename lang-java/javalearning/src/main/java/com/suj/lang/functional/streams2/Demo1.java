package com.suj.lang.functional.streams2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sujayjayaram on 28/01/2016.
 */
public class Demo1 {

    public void convertToListType() {
        List<String> list1 = Arrays.asList("1", "2", "3", "4", "5");

        List<Integer> integerList = list1.stream()
                                            .map(s -> Integer.valueOf(s))
                                            .collect(Collectors.toList());

    }

    public void convertToArrayType() {
        List<String> list1 = Arrays.asList("1", "2", "3", "4", "5");

        Integer[] integers = list1.stream()
                                    .map(s -> Integer.valueOf(s))
                                    .toArray(Integer[]::new);

    }

    public void forEach() {
        List<String> list1 = Arrays.asList("1", "2", "3", "4", "5");
        list1.forEach(i -> System.out.println(i));
        list1.forEach(this::forEachMethod);
    }

    private void forEachMethod(String s) {
        System.out.println(s);
    }
}
