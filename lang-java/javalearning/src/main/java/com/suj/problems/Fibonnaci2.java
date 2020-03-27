package com.suj.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

/**
 * Created by sujayjayaram on 20/02/2016.
 */
public class Fibonnaci2 {

    private static Map<Long, Long> map = new HashMap<>();

    private static long calc(long n) {
        if ( map.containsKey(n) )
            return map.get(n);

        if ((n == 0) || (n == 1)) {
            map.put(n,n);
            return n;
        }

        long rv = calc(n - 1) + calc(n - 2);
        map.put(n, rv);
        return rv;
    }

    /*
    34 -> 5702887
35 -> 9227465
36 -> 14930352
37 -> 24157817
38 -> 39088169
39 -> 63245986
40 -> 102334155
41 -> 165580141
42 -> 267914296
43 -> 433494437
44 -> 701408733
45 -> 1134903170
46 -> 1836311903
47 -> 2971215073
48 -> 4807526976
49 -> 7778742049
Total Time Millis = 111260

     */

    // Use dynamic programming and memoization (remembering the result for a previously
    // computed input)
    public static void main(String... args){

        long millisecsStart = System.currentTimeMillis();

        LongStream.range(0, 50).forEach(i -> {
            System.out.println( i + " -> " + calc(i));
        });

        long millisecsEnd = System.currentTimeMillis();

        System.out.println("Total Time Millis = " + (millisecsEnd - millisecsStart));
    }
}
