package com.freddieb.codility.lesson6;

import java.util.Arrays;

/**
 * Created by sujayjayaram on 27/12/2016.
 */
public class Sort {
    public static int[] selectionSort(int[] a){
        for(int i = 0; i < a.length; i++) {
            int minIdx = i;
            for (int j = i; j < a.length; j++) {
                if ( a[j] < a[minIdx] )
                    minIdx = j;
            }

            if ( minIdx != i ) {
                int tmp = a[i];
                a[i] = a[minIdx];
                a[minIdx] = tmp;
            }
        }

        return a;
    }

    public static int[] bubbleSort(int[] a){
        for(int i = 0; i < a.length; i++) {
            for (int j = 0; j < (a.length - 1); j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
        return a;
    }

    public static int[] mergeSort(int[] a) {
        return mergeSort(a, 0, a.length-1);
    }

    public static int[] mergeSort(int[] a, int startIdx, int endIdx) {
        if ( (endIdx - startIdx) < 1 )
            return a;

        int mid = (endIdx - startIdx)/2;
        mergeSort(a, startIdx, startIdx + mid);
        mergeSort(a, startIdx+mid+1, endIdx);

        int[] lhs = Arrays.copyOfRange(a, startIdx, startIdx + mid+1);
        int[] rhs = Arrays.copyOfRange(a, startIdx+mid+1, endIdx+1);

        int lhsIdx = 0;
        int rhsIdx = 0;
        int count = 0;
        while( count <= (endIdx - startIdx) ) {

            if ( (lhsIdx < lhs.length) && (rhsIdx < rhs.length) ) {
                if (lhs[lhsIdx] <= rhs[rhsIdx])
                    a[startIdx + count++] = lhs[lhsIdx++];
                else
                    a[startIdx + count++] = rhs[rhsIdx++];
            }
            else if ( lhsIdx < lhs.length ) {
                a[startIdx + count++] = lhs[lhsIdx++];
            }
            else {
                a[startIdx + count++] = rhs[rhsIdx++];
            }
        }

        return a;
    }
}
