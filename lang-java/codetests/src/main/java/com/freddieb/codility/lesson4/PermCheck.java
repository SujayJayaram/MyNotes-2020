package com.freddieb.codility.lesson4;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class PermCheck {
    public static int solution(int[] a) {

        // If array 'a' is a permutation, then each element 'n' (from 1 to a.length) will only appear once
        // and we represent it by element 'n-1' in the counter array.
        int[] counter = new int[a.length];
        for(int i = 0; i < a.length; i++) {
            int val = a[i];
            if ( (val> counter.length) || (counter[val-1] == 1) )
                return 0; // already exists

            counter[val-1]++;
        }

        return 1;
    }
}
