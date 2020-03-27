package com.freddieb.codility.lesson4;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 25/12/2016.
 */
public class MissingIntegerTest {
    @Test
    public void solution() throws Exception {
        int[] a = {1,3,6,4,1,2};
        assertThat(MissingInteger.solution(a), equalTo(5));
    }

}