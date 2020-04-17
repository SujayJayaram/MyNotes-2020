package com.hired.assessment1.fibonnaci;

import java.util.*;

class Solution {
    public static long[] solution(long n) {
        List<Long> primeFibsList = new ArrayList<>();
        for(long l = 1; l <= n; l++) {
            long fib = getFib(l);
            if ( isPrime(fib) )
                primeFibsList.add(fib);
        }

        long[] rv = new long[primeFibsList.size()];
        for(int i = 0; i < primeFibsList.size(); i++)
            rv[i] = primeFibsList.get(i);

        return rv;
    }

    private static boolean isPrime(long number) {
        if ( number < 2)
            return false;

        if ( number == 2 )
            return true;

        for(long div = number/2+1; div > 1; div--) {
            if ( number % div == 0 )
                return false;
        }

        return true;
    }

    static Map<Long, Long> mapFibNumToResult = new HashMap<>();

    static long getFib(long n) {
        if ( n < 1 )
            return 0;

        if ( (n == 1) || (n == 2) )
            return 1;

        Long cached = mapFibNumToResult.get(n);
        if ( cached != null )
            return cached;

        long rv = getFib(n-1) + + getFib(n-2);
        //System.out.println("Fibonaci number " + n + "=" + rv);
        mapFibNumToResult.put(n, rv);
        return rv;
    }


    public static void mainz(String[] args) {
        getFib(6);
    }
}
