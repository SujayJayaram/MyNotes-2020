package com.freddieb.codility.lesson4;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by sujayjayaram on 25/12/2016.
 */
public class FrogRiverOne {
    public static int solution(int x, int[] a) {
        Set<Integer> set = new HashSet<>();
        IntStream.range(1,x+1).forEach(set::add);

        for(int i = 0; i < a.length; i++) {
            set.remove(a[i]);
            if ( set.size() == 0 )
                return i;
        }

        return -1;
    }
}
