package com.top30javainterviewquestions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {


    @Test
    void reverse() {
        //assertEquals("", Solution.reverse(null) );

        Solution.LinkedLint<Integer> list = new Solution.LinkedLint<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.get(0) );
        assertEquals(2, list.get(1) );
        assertEquals(3, list.get(2) );

        list.reverse();;

        assertEquals(3, list.get(0) );
        assertEquals(2, list.get(1) );
        assertEquals(1, list.get(2) );

    }

    @Test
    void reverse2() {

    }
}