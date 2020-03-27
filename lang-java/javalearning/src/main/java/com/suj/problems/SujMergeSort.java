package com.suj.problems;

/**
 * Created by sujayjayaram on 20/02/2016.
 */
public class SujMergeSort {

    public static void main(String... args) {
        int[] array = {1,5,56,3,203,6,2,88,45,7,35,1,87,34,5,4,8};
        sort(array, 0, array.length-1);

        for(int i : array)
            System.out.println("Result2 = " + i);
    }

    private static void sort(int[] array, int startIndex, int endIndex) {

        if ( startIndex < endIndex ) {

            // This logic only works because the Java VM will round DOWN
            // in the case where startIndex and endIndex differ by one.
            int midIndex = (startIndex + endIndex)/2;

            sort(array, startIndex, midIndex);
            sort(array, midIndex+1, endIndex);

            // Left hand and right hand sides are assumed to be sorted by now so now sort this superset
            mergeParts(array, startIndex, midIndex, midIndex+1, endIndex);
        }
    }

    private static void mergeParts(int[] array, int lhsStartIndex, int lhsEndIndex, int rhsStartIndex, int rhsEndIndex) {
        int tmpArrayLength = rhsEndIndex - lhsStartIndex + 1;
        int[] tmpArray = new int[tmpArrayLength];

        int iLhsNext = lhsStartIndex;
        int iRhsNext = rhsStartIndex;

        for(int i = 0; i < tmpArrayLength; i++){
            if( (iLhsNext <= lhsEndIndex) && (iRhsNext <= rhsEndIndex) ) {
                int iLeft = array[iLhsNext];
                int iRight = array[iRhsNext];

                if ( iLeft <= iRight ) {
                    tmpArray[i] = iLeft;
                    iLhsNext++;
                }
                else {
                    tmpArray[i] = iRight;
                    iRhsNext++;
                }
            }
            else if (iLhsNext <= lhsEndIndex) {
                tmpArray[i] = array[iLhsNext];;
                iLhsNext++;
            }
            else if (iRhsNext <= rhsEndIndex) {
                tmpArray[i] = array[iRhsNext];
                iRhsNext++;
            }
            else
                throw new RuntimeException("Should never get here");
        }


        for(int i = lhsStartIndex; i <= rhsEndIndex; i++){
            array[i] = tmpArray[i - lhsStartIndex];
        }
    }
}
