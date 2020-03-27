package com.freddieb.codility.lesson4;

/**
 * Created by sujayjayaram on 25/12/2016.
 */
public class MaxCounters {
    public static int[] solution(int n, int[] a) {
        int[] rv = new int[n];
        int currMax = 0;
        for(int i : a) {
            if ( i > n ) {
                for(int j = 0; j < rv.length; j++) {
                    rv[j] = currMax;
                }
            }
            else {
                rv[i-1]++;
                currMax = (currMax > rv[i-1])? currMax : rv[i-1];
            }
        }

        return rv;
    }
}
