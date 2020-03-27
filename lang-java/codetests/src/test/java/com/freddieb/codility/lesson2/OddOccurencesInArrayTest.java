package com.freddieb.codility.lesson2;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class OddOccurencesInArrayTest {
    @Test
    public void solution() throws Exception {

        int[] a = {9,3,9,3,9,7,9};
        assertThat(OddOccurencesInArray.solution(a), equalTo(7));
    }

}