package com.suj.sorting;

public class SelectionSort {

    // ****** HAS JUNIT TEST
    // e.g. int[] arr = { 2, 8, 4, 99, 33, 1, 56, 32};
    public static void sort(int[] arr) {

        System.out.println("Pre-Sorted Array = ");
        for(int i : arr) {
            System.out.println(i);
        }

        for(int iStart = 0; iStart < arr.length; iStart++) {
            int min = arr[iStart];
            int minIdx = iStart;
            for (int i = iStart + 1; i < arr.length; i++) {
                if (arr[i] < min) {
                    minIdx = i;
                    min = arr[i];
                }
            }

            if (minIdx != iStart) {
                int tmp = arr[iStart];
                arr[iStart] = min;
                arr[minIdx] = tmp;
            }
        }

        System.out.println("Post-Sorted Array = ");
        for(int i : arr) {
            System.out.println(i);
        }
    }

}
