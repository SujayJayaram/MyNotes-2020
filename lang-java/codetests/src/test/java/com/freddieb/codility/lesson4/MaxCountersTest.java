package com.freddieb.codility.lesson4;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 25/12/2016.
 */
public class MaxCountersTest {
    @Test
    public void solution() throws Exception {

        int[] a = {3,4,4,6,1,4,4};
        a = MaxCounters.solution(5, a);
        int[] b = {3,2,2,4,2};
        assert(Arrays.equals(a, b));
    }

}