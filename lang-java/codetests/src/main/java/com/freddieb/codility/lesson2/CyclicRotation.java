package com.freddieb.codility.lesson2;

/**
 * Created by sujayjayaram on 24/12/2016.
 * See https://codility.com/programmers/lessons/2-arrays/cyclic_rotation/
 * Also http://www.programcreek.com/2015/03/rotate-array-in-java/
 */
public class CyclicRotation {
    public static int[] solution(int[] a, int k) {
        if ( a.length == 0 )
            return a;

        k %= a.length;
        if ( k == 0 )
            return a;

        int[] rv = new int[a.length];
        for(int i = 0; i < a.length; i++) {
            rv[ (i+k)%a.length ] = a[i];
        }

        return rv;
    }
}
