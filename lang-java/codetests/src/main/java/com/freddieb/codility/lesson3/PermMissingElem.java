package com.freddieb.codility.lesson3;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class PermMissingElem {
    public static int solution(int[] a) {
        int sum = 0;
        for(int i : a)
            sum += i;

        // The sum of the series from 1 to n is n*(n+1)/2
        int missing = (a.length+1)*(a.length+2)/2 -sum;
        return missing;
    }

    public static void main(String[] args){
        int a = 5;
        int b = 2;
        int c = a/b;
        double d = a/b;
        double e = a/(double)b;

        int j = 0;
    }
}
