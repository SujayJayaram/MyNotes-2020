package com.top30javainterviewquestions.verifyispoweroftwo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SolutionTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {16, true },
                {23, false },
                {48, true },
                {11, false },
                {256, true },
        });
    }

    @Parameterized.Parameter(value = 0)
    public int input;

    @Parameterized.Parameter(value = 1)
    public boolean result;

    @Test
    public void reverse() {
        System.out.println("reverse() called");
        assertEquals(Solution.isPowerOfTwo(input), result);
    }
}
