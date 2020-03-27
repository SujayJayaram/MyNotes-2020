package com.suj.problems.mostfrequentiteminarray;

import java.util.*;

/**
 * Created by sujayjayaram on 02/12/2016.
 */
public class Main {

    public static void main(String[] args){

        int[] a = {1,2,3,4,5,6,7,7,7,7};

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : a) {
            Integer count = map.get(i);
            map.put(i, count != null ? count+1 : 1);
        }

        Integer popular = Collections.max(map.entrySet(),
                new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                }).getKey();

        System.out.println(popular + " shown " + map.get(popular));

    }
}
