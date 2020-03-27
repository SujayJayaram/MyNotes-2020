package com.bbva.fxprototype.controller;

import com.bbva.fxprototype.model.*;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Tests out VWAP calcs.
 */
public class VWAPProcessorTest {

    @Test
    public void testNewOrder() throws Exception {
        VWAPProcessor processor = new VWAPProcessor(getInitialVWAP());

        float rate = 1.55555f;
        long size = 2000;
        OrderRequest request = new OrderRequest(OrderType.NEW, BidAskType.BID, getFXInstrument(), rate, size, getTestMarket());

        processor.update(request);

        // (1.54323 * 1000) + (1.55555 * 2000) / 3000
        // => (1543.23 + 3111.1)/3000 => 1.55144
        assertThat(processor.getCurrentRate(), equalTo(1.55144f));
    }

    @Test
    public void testCancelOrder() throws Exception {
        VWAPProcessor processor = new VWAPProcessor(getInitialVWAP());

        float rate = 1.44444f;
        long size = 500;
        OrderRequest request = new OrderRequest(OrderType.CANCEL, BidAskType.BID, getFXInstrument(), rate, size, getTestMarket());

        processor.update(request);

        // (1.54323 * 1000) - (1.44444 * 500) / 500
        // => (1543.23 - 722.22)/500 => 1.64202
        assertThat(processor.getCurrentRate(), equalTo(1.64202f));
    }

    @Test
    public void testAmendOrder() throws Exception {
        VWAPProcessor processor = new VWAPProcessor(getInitialVWAP());

        float rate = 1.55555f;
        long size = 2000;
        float rateOld = 1.44444f;
        long sizeOld = 500;
        OrderRequest request = new OrderRequest(BidAskType.BID, getFXInstrument(), rate, size, rateOld, sizeOld, getTestMarket());
        processor.update(request);

        assertThat(processor.getCurrentRate(), equalTo(1.57284f));
    }

    @Test
    public void testCompoundOrdersNewFirst() throws Exception {
        // Results from a new an cancel should be the same as the amend case.
        VWAPProcessor processor = new VWAPProcessor(getInitialVWAP());
        {
            float rate = 1.55555f;
            long size = 2000;
            OrderRequest request = new OrderRequest(OrderType.NEW, BidAskType.BID, getFXInstrument(), rate, size, getTestMarket());
            processor.update(request);
        }
        {
            float rate = 1.44444f;
            long size = 500;
            OrderRequest request = new OrderRequest(OrderType.CANCEL, BidAskType.BID, getFXInstrument(), rate, size, getTestMarket());
            processor.update(request);
        }

        assertThat(processor.getCurrentRate(), equalTo(1.57284f));
    }

    @Test
    public void testCompoundOrdersCancelFirst() throws Exception {
        // Results from a new an cancel should be the same as the amend case.
        VWAPProcessor processor = new VWAPProcessor(getInitialVWAP());
        {
            float rate = 1.44444f;
            long size = 500;
            OrderRequest request = new OrderRequest(OrderType.CANCEL, BidAskType.BID, getFXInstrument(), rate, size, getTestMarket());
            processor.update(request);
        }
        {
            float rate = 1.55555f;
            long size = 2000;
            OrderRequest request = new OrderRequest(OrderType.NEW, BidAskType.BID, getFXInstrument(), rate, size, getTestMarket());
            processor.update(request);
        }

        assertThat(processor.getCurrentRate(), equalTo(1.57284f));
    }

    private VWAP getInitialVWAP() {
        // 1.54323 * 1000
        float rate = 1.54323f;
        long size = 1000;
        return new VWAP(rate, size, getFXInstrument());
    }

    private Market getTestMarket() {
        return null; // Don't actually need a market implementation here.
    }

    private FXInstrument getFXInstrument() {
        return new FXInstrument("USD", "GBP");
    }

}