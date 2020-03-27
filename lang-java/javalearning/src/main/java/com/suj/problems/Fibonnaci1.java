package com.suj.problems;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by sujayjayaram on 20/02/2016.
 */
public class Fibonnaci1 {

    private static long calc(long n) {
        if ((n == 0) || (n == 1))
            return n;

        return calc(n - 1) + calc(n - 2);
    }

    /*
    46 -> 1836311903
47 -> 2971215073
48 -> 4807526976
49 -> 7778742049
Total Time Millis = 111260
     */
    public static void main(String... args){

        long millisecsStart = System.currentTimeMillis();

        LongStream.range(0, 50).forEach(i -> {
            System.out.println( i + " -> " + calc(i));
        });

        long millisecsEnd = System.currentTimeMillis();

        System.out.println("Total Time Millis = " + (millisecsEnd - millisecsStart));
    }
}
