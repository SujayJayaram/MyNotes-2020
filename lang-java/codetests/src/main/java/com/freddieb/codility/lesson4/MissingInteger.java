package com.freddieb.codility.lesson4;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class MissingInteger {
    public static int solution(int[] a) {

        Set<Integer> set = new TreeSet<>();
        for(int i : a)
            if ( i > 0 )
                set.add(i);

        Iterator<Integer> iter = set.iterator();
        int expected = 1;
        if ( iter.hasNext() ) {
            while( iter.next() == expected )
                expected++;

            return expected;
        }

        return 0;
    }
}
