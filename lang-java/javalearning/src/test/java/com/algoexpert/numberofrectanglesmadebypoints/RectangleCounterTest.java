package com.algoexpert.numberofrectanglesmadebypoints;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RectangleCounterTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Point[] points1 = {new Point(1,1), new Point(1,6), new Point(6,1), new Point(6,6)    , new Point(3,1), new Point(3,6)};
        return Arrays.asList(new Object[][] {
                { points1, 1 }
        });
    }

    @Parameterized.Parameter(value = 0)
    public Point[] input;

    @Parameterized.Parameter(value = 1)
    public int result;

    @org.junit.Test
    public void countRectangles() {
        System.out.println("reverse() called");
        assertEquals(result, RectangleCounter.countRectangles(input));
    }
}
