package com.suj.sorting;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleSortTest {

    @Test
    public void sort() {
        int[] arr = { 2, 8, 4, 99, 33, 1, 56, 32};
        BubbleSort.sort(arr);

        int[] expected =  { 1,2,4,8,32,33,56,99};
        assertArrayEquals(expected, arr);
    }
}