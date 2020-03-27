package com.freddieb.codility.lesson3;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class TapeEquilibrium {
    public static int solution(int[] a) {
        int sum = 0;
        for(int i : a)
            sum += i;

        int lastLhs = a[0];
        int lastRhs = sum - lastLhs;
        int lowestDiff = Math.abs(lastLhs-lastRhs);

        for(int j = 1; j < a.length-1; j++) {
            lastLhs += a[j];
            lastRhs -= a[j];
            int thisDiff = Math.abs(lastLhs-lastRhs);
            lowestDiff = (lowestDiff < thisDiff)? lowestDiff : thisDiff;
        }

        return lowestDiff;
    }
}
