package com.top30javainterviewquestions.reversetext;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringUtilsReverseTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "fred", "derf", "Test 1" },
                { "tom", "mot", "Test 2" }
        });
    }

    @Parameterized.Parameter(value = 0)
    public String expected;

    @Parameterized.Parameter(value = 1)
    public String input;

    @Parameterized.Parameter(value = 2)
    public String message;

    @Test
    public void reverse() {
        System.out.println("reverse() called: " + message);
        assertEquals(expected, StringUtils.reverse(input));
    }
}
