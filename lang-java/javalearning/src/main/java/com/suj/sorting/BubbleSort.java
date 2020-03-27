package com.suj.sorting;

public class BubbleSort {

    // ****** HAS JUNIT TEST
    // e.g. int[] arr = { 2, 8, 4, 99, 33, 1, 56, 32};
    public static void sort(int[] arr) {

        System.out.println("Pre-Sorted Array = ");
        for(int i : arr) {
            System.out.println(i);
        }

        for(int j = 0; j < arr.length; j++) {
            for (int i = 0; i < (arr.length - 1); i++) {
                if (arr[i] > arr[i + 1]) {
                    // Swap them
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
        }

        System.out.println("Post-Sorted Array = ");
        for(int i : arr) {
            System.out.println(i);
        }
    }

}
