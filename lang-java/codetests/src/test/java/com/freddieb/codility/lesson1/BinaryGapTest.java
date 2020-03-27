package com.freddieb.codility.lesson1;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 24/12/2016.
 */
public class BinaryGapTest {
    @Test
    public void solution2() throws Exception {

        assertThat(BinaryGap.solution2(0b0000_0111_1110_0000), equalTo(0));
        assertThat(BinaryGap.solution2(0b1000_0111_1110_0000), equalTo(4));
    }

    @Test
    public void solution1() throws Exception {

        assertThat(BinaryGap.solution1(0b0000_0111_1110_0000), equalTo(0));
        assertThat(BinaryGap.solution1(0b1000_0111_1110_0000), equalTo(4));
    }

}