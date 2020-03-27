package com.suj.problems;

/**
 * Created by sujayjayaram on 05/03/2016.
 */
public class MergeSortedArrays {
    // A and B are sorted arrays and A has enough space at the end to exactly contain B.
    // Merge them together.
    // Approach - start at the end of A and cooy over the largest elements working backwards

    public static void main(String... args){
        int[] arrA = new int[6];
        arrA[0]=1;
        arrA[1]=7;
        arrA[2]=11;

        int[] arrB = {2,6,19};

        mergeArrays(arrA, arrB);

        System.out.println(arrA);
    }

    private static void mergeArrays(int[] arrA, int[] arrB) {
        int idxLastA = arrA.length -1;

        int idxB = arrB.length-1;
        int idxA = (arrA.length - arrB.length) - 1;

        while(idxLastA >= 0) {
            if ( (idxA >= 0) && (idxB >= 0) ) {
                if ( arrA[idxA] > arrB[idxB] )
                    arrA[idxLastA--] = arrA[idxA--];
                else
                    arrA[idxLastA--] = arrB[idxB--];
            }
            else {
                if ( idxA >= 0 )
                    arrA[idxLastA--] = arrA[idxA--];
                else
                    arrA[idxLastA--] = arrB[idxB--];
            }

        }
    }
}
