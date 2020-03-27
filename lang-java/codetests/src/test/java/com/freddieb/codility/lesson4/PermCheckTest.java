package com.freddieb.codility.lesson4;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class PermCheckTest {

    @Test
    public void solution() throws Exception {
        int[] a = {4,1,3,2};
        assertThat(PermCheck.solution(a), equalTo(1));
    }

    @Test
    public void solution2() throws Exception {
        int[] a = {4,1,3};
        assertThat(PermCheck.solution(a), equalTo(0));
    }

}