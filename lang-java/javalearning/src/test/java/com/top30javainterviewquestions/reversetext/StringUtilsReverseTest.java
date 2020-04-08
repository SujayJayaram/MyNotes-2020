package com.top30javainterviewquestions.reversetext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StringUtilsReverseTest {
    @Parameterized.Parameters(name = "({index}): reverse({1})={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "fred", "derf" },
                { "tom", "mot" }
        });
    }

    @Parameterized.Parameter(value = 0)
    public String expected;

    @Parameterized.Parameter(value = 1)
    public String input;

    @Test
    public void reverse() {
        System.out.println("reverse() called");
    }
}
