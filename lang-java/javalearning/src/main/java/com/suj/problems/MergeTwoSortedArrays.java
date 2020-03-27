package com.suj.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sujayjayaram on 28/02/2016.
 */
public class MergeTwoSortedArrays {
    private static List<Integer> arrayB;
    // Problem: We are given two sorted arrays arrA and arrB and arrA has enough capacity in it to hold arrB.
    // Merge the arrays.

    public static void main(String... args) {
        List<Integer> arrA = getArrayA();
        List<Integer> arrB = getArrayB();
        int idxA = 0;
        int idxB = 0;

        while( (idxB < arrB.size()) && (idxA < arrA.size()) ) {
            if ( arrB.get(idxB) < arrA.get(idxA) ) {
                arrA.add(idxA++, arrB.get(idxB++));
            }
            else {
                idxA++;
            }
        }

        while (idxB < arrB.size()) {
            arrA.add(arrB.get(idxB++));
        }

        arrA.forEach(i -> System.out.println(i));
    }

    public static List<Integer> getArrayB() {

        return new ArrayList(Arrays.asList(2,4,7,8,50,90));
    }

    public static List<Integer> getArrayA() {
        return new ArrayList(Arrays.asList(1,5,10,25,51,89));
    }
}
