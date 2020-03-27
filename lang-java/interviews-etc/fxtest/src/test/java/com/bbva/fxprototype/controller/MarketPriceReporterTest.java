package com.bbva.fxprototype.controller;

import com.bbva.fxprototype.model.FXInstrument;
import com.bbva.fxprototype.model.Market;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Main class for this prototype which creates a test environment.
 */
public class MarketPriceReporterTest {

    public static void main(String[] args) throws Exception {

        MarketPriceReporter reporter = new MarketPriceReporter();
        Thread t = new Thread(reporter);
        t.start();

        List<FXInstrument> subscribedInstruments = new ArrayList<>();
        subscribedInstruments.add(new FXInstrument("USD", "GBP"));
        subscribedInstruments.add(new FXInstrument("USD", "JPY"));
        subscribedInstruments.add(new FXInstrument("USD", "CHF"));
        subscribedInstruments.add(new FXInstrument("USD", "EUR"));

        {
            TestMarket market = new TestMarket("XXX");
            market.addOrderRequestListener(subscribedInstruments, reporter);
            Thread tMarket = new Thread(market);
            tMarket.start();
        }

        {
            TestMarket market = new TestMarket("YYY");
            market.addOrderRequestListener(subscribedInstruments, reporter);
            Thread tMarket = new Thread(market);
            tMarket.start();
        }

        // Hang around for the output...
        Thread.currentThread().sleep(30*60*1000);
    }
}