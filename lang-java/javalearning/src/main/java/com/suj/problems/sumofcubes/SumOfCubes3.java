package com.suj.problems.sumofcubes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by sujayjayaram on 02/12/2016.
 * Cracking The Code Interview p68.
 * Find all positive integer combinations (up to 1000) of:
 * a^3 + b^3 = c^3 + d^3
 */
public class SumOfCubes3 {


    public static void main(String[] args) {

        Map<Double, Set<Result3> > map = new HashMap<>();

        for (int i = 0; i <= 1000; i++) {
            for(int j = 0; j <= 1000; j++) {
                double sumOfCubes = Math.pow(i, 3) + Math.pow(j, 3);
                Set<Result3> set = map.get(sumOfCubes);
                if ( set != null )
                    set.add(new Result3(i, j, sumOfCubes));
                else {
                    set = new HashSet<>();
                    set.add(new Result3(i, j, sumOfCubes));
                    map.put(sumOfCubes, set);
                }
            }
            System.out.println(".");
        }

        for(Set<Result3> set : map.values()) {
            if ( set.size() > 1 ) {
                System.out.println("*********");
                set.forEach( r -> System.out.println(r) );
            }
        }
    }
}
