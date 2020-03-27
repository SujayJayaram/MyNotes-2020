package com.freddieb.codility.lesson2;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class OddOccurencesInArray {
    public static int solution(int a[]) {
        Set<Integer> set = new HashSet<>();
        for(int i : a) {
            if ( set.contains(i) )
                set.remove(i);
            else
                set.add(i);
        }

        if (set.size() != 1 )
            return 0;

        return set.toArray(new Integer[0])[0].intValue();
    }
}
