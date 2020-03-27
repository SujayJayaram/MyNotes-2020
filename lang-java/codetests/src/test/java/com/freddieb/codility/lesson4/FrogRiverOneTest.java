package com.freddieb.codility.lesson4;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 25/12/2016.
 */
public class FrogRiverOneTest {
    @Test
    public void solution() throws Exception {
        int[] a = {1,3,1,4,2,3,5,4};
        assertThat(FrogRiverOne.solution(5, a), equalTo(6));
    }

}