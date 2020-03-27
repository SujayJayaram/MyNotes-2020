package com.suj.util;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 20/01/2016.
 */
public class MyConfigTest {

    @Test
    public void testGetInt() throws Exception {
        int ageCutOff = MyConfig.INSTANCE.getInt("age.cutoff");
        assertThat(ageCutOff, equalTo(3));
    }

    @Test
    public void testGetDouble() throws Exception {
        double discountRate = MyConfig.INSTANCE.getDouble("discount.rate");
        assertThat(discountRate, equalTo(0.9));
    }
}