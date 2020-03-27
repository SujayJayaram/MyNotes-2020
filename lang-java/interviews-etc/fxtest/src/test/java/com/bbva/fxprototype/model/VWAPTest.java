package com.bbva.fxprototype.model;

import org.junit.Test;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram
 */
public class VWAPTest {

    @Test
    // Test the second (less intuitive) ctr.
    public void testGetRate() throws Exception {
        // 1.54323 * 1000
        double rateSizeProduct = 1543.23;
        long totalSize = 1000;
        FXInstrument fxInstrument = new FXInstrument("USD", "GBP");
        VWAP vwap = new VWAP(rateSizeProduct, totalSize, fxInstrument);
        assertThat(vwap.getRate(), equalTo(1.54323f));
    }
}