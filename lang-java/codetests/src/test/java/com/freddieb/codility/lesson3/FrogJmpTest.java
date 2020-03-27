package com.freddieb.codility.lesson3;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class FrogJmpTest {
    @Test
    public void solution() throws Exception {

        assertThat(FrogJmp.solution(10, 40, 5), equalTo(7));
    }

}