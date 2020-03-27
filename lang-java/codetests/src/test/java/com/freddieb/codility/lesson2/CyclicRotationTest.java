package com.freddieb.codility.lesson2;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class CyclicRotationTest {

    @Test
    public void solution() throws Exception {
        int[] a = {3, 8, 9, 7, 6};
        a = CyclicRotation.solution(a, 1);
        int[] b = {6, 3, 8, 9, 7};
        assert(Arrays.equals(a, b));
    }

    @Test
    public void solution2() throws Exception {
        int[] a = {3, 8, 9, 7, 6};
        a = CyclicRotation.solution(a, 3);
        int[] b = {9, 7, 6, 3, 8};
        assert(Arrays.equals(a, b));
    }

}