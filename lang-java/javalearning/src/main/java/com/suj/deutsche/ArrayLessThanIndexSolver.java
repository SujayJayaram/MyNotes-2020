package com.suj.deutsche;

public class ArrayLessThanIndexSolver {

    public static int getLessThanIndex(int[] array, int lessThanVal) {
        int currentIdx = (array.length+1)/2;
        return getLessThan(array, lessThanVal, currentIdx, array.length, 0);
    }

    private static int getLessThan(int[] array, int lessThanVal, int currentIdx, int upperBound, int lowerBound) {
        // Start edge conditions
        if ( currentIdx == 0 )
            return 0;

        if ( (array[currentIdx-1] <= lessThanVal) &&  (currentIdx == array.length) )
            return -1;
        // Ends edge conditions

        if ( (array[currentIdx-1] <= lessThanVal) &&  (array[currentIdx] > lessThanVal) )
            return currentIdx;

        if ( array[currentIdx-1] > lessThanVal ) {
            // Move towards the left, halving the array under consideration
            int newCurrentId = (lowerBound + currentIdx)/2;
            return getLessThan(array, lessThanVal, newCurrentId, currentIdx, lowerBound);
        }

        // Move towards the right, halving the array under consideration
        int newCurrentId = (upperBound + currentIdx + 1)/2;
        return getLessThan(array, lessThanVal, newCurrentId, upperBound, currentIdx);
    }
}
