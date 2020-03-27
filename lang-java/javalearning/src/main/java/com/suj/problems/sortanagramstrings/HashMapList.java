package com.suj.problems.sortanagramstrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sujayjayaram on 28/02/2016.
 */
public class HashMapList<K, E> extends HashMap<K, List<E>> {

    public static void main(String... args) {

        HashMapList<String, Integer> map = new HashMapList<>();

        List<Integer> list1 = new ArrayList(Arrays.asList(1,2,3));
        map.put("Fred", list1);
        List<Integer> list2 = map.get("Fred");


    }

}
