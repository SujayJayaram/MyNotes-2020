package com.freddieb.codility.lesson6;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 27/12/2016.
 */
public class SortTest {
    @Test
    public void selectionSort() throws Exception {
        int[] a = {34,65,2,89,3,7};
        a = Sort.selectionSort(a);
        int[] b = {2,3,7,34,65,89};
        assert(Arrays.equals(a, b));
    }

    @Test
    public void bubbleSort() throws Exception {
        int[] a = {34,65,2,89,3,7};
        a = Sort.bubbleSort(a);
        int[] b = {2,3,7,34,65,89};
        assert(Arrays.equals(a, b));
    }

    @Test
    public void mergeSort() throws Exception {
        int[] a = {34, 65, 2, 89, 3, 7};
        a = Sort.mergeSort(a);
        int[] b = {2,3,7,34,65,89};
        assert(Arrays.equals(a, b));
    }
}