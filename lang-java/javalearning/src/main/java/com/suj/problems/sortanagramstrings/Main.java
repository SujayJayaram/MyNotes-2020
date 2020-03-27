package com.suj.problems.sortanagramstrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by sujayjayaram on 28/02/2016.
 */
public class Main {
    public static void main(String... args){

//        List<AnagramItem> list = new ArrayList<AnagramItem>(
//                Arrays.asList(
//                        new AnagramItem("cat"),
//                        new AnagramItem("dog"),
//                        new AnagramItem("act"),
//                        new AnagramItem("slit"),
//                        new AnagramItem("god"),
//                        new AnagramItem("list"),
//                        new AnagramItem("live"),
//                        new AnagramItem("yacht"),
//                        new AnagramItem("fat"),
//                        new AnagramItem("evil"),
//                        new AnagramItem("greek"))
//                                            );

        List<AnagramItem> list = Arrays.asList(
                        new AnagramItem("cat"),
                        new AnagramItem("dog"),
                        new AnagramItem("act"),
                        new AnagramItem("slit"),
                        new AnagramItem("god"),
                        new AnagramItem("list"),
                        new AnagramItem("live"),
                        new AnagramItem("yacht"),
                        new AnagramItem("fat"),
                        new AnagramItem("evil"),
                        new AnagramItem("greek")
        );

        Collections.sort(list);
        list.forEach(i -> System.out.println(i));


        // Even better way!!!
        List<String> list2 = Arrays.asList("cat","dog","act","slit","god","list","live","yacht","fat","evil","greek");
        Collections.sort(list2, new AnagramComparator());
        list2.forEach(i -> System.out.println(i));
    }
}
