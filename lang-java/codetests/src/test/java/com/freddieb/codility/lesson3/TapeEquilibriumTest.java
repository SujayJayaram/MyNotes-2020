package com.freddieb.codility.lesson3;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class TapeEquilibriumTest {
    @Test
    public void solution() throws Exception {
        int[] a = {3,1,2,4,3};
        assertThat(TapeEquilibrium.solution(a), equalTo(1));
    }

}