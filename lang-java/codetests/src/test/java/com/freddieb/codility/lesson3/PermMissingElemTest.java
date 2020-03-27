package com.freddieb.codility.lesson3;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class PermMissingElemTest {
    @Test
    public void solution() throws Exception {

        int[] a = {2,3,1,5};
        assertThat(PermMissingElem.solution(a), equalTo(4));
    }

}