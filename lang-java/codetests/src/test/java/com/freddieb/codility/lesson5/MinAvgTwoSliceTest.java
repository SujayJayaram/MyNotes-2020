package com.freddieb.codility.lesson5;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 26/12/2016.
 */
public class MinAvgTwoSliceTest {
    @Test
    public void solution() throws Exception {

        int[] a = {4,2,2,5,1,5,8};
        System.out.println("Min average = " + MinAvgTwoSlice.solution(a));
    }

}