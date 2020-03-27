package com.suj.problems;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by sujayjayaram on 20/02/2016.
 */
public class Fibonnaci3 {

    private static int calc(int n) {
        int[] memo = new int[n+1];
        return calc(n, memo);
    }

    private static int calc(int n, int[] memo) {
        if ( n <= 1 )
            return n;

        if ( memo[n] > 0 )
            return memo[n];

        memo[n] = calc(n-1, memo) + calc(n-2, memo);
        return memo[n];
    }


    public static void main(String... args){

        long millisecsStart = System.currentTimeMillis();

        IntStream.range(0, 40).forEach(i -> {
            System.out.println( i + " -> " + calc(i));
        });

        long millisecsEnd = System.currentTimeMillis();
        System.out.println("Total Time Millis = " + (millisecsEnd - millisecsStart));
    }
}
