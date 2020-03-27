package com.suj.problems;

/**
 * Created by sujayjayaram on 18/03/2017.
 */
public class SujMergeSortAttempt2 {

    public static void main(String... args) {
        int[] array = {1,5,56,3,203,6,2,88,45,7,35,1,87,34,5,4,8};
        //int[] array = {9,2};

        sort(array, 0, array.length-1);

        for(int i : array)
            System.out.println("Sorted Result = " + i);
    }

    public static void sort(int[] array, int startIndex, int endIndex)
    {
        // Do nothing for a (sub-)array of length 0 or 1 - its already sorted.
        if ( startIndex >= endIndex )
            return;

        int midIndex = (startIndex + endIndex)/2;
        sort(array, startIndex, midIndex);
        sort(array, midIndex+1, endIndex);

        merge(array, startIndex, midIndex, endIndex);
    }

    private static void merge(int[] array, int startIndex, int midIndex, int endIndex) {

        int lhsCounter = startIndex;
        int rhsCounter = midIndex + 1;
        int[] tmpArray = new int[endIndex-startIndex+1]; // when endIndex == startIndex, we need size=1
        int tmpArrayIdx = 0;

        while( ((midIndex - lhsCounter) >= 0) ||
                ((endIndex - rhsCounter) >= 0) )
        {
            if ( (midIndex - lhsCounter) < 0 )
                tmpArray[tmpArrayIdx++] = array[rhsCounter++]; // Left hand list is exhausted
            else if ( (endIndex - rhsCounter) < 0 )
                tmpArray[tmpArrayIdx++] = array[lhsCounter++]; // Right hand list is exhausted
            else {
                // Both lists have elements;
                if ( array[lhsCounter] < array[rhsCounter] )
                    tmpArray[tmpArrayIdx++] = array[lhsCounter++];
                else
                    tmpArray[tmpArrayIdx++] = array[rhsCounter++];
            }
        }

        for(int i = startIndex; i <= endIndex; i++)
            array[i] = tmpArray[i-startIndex];
    }
}
