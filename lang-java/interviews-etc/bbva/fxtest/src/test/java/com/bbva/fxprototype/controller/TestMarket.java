package com.bbva.fxprototype.controller;

import com.bbva.fxprototype.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Test Market to be used by MarketPriceReporterTest.
 */
public class TestMarket implements Market, Runnable {
    private long TEN_SECONDS = 10000;

    private final String name;
    private List<OrderRequestListener> listeners = new ArrayList<>();
    private List<FXInstrument> subscribedInstruments = new ArrayList<>();
    private Random random = new Random();

    public TestMarket(String name){
        this.name = name;
    }

    @Override
    public void addOrderRequestListener(List<FXInstrument> list, OrderRequestListener orderRequestListener) {
        subscribedInstruments = new ArrayList<>(list);
        listeners.add(orderRequestListener);

        List<VWAP> initialBuyPrices = new ArrayList<>();
        List<VWAP> initialSellPrices = new ArrayList<>();

        list.forEach( instrument -> {
            float rate1 = randomFloat(0.6f, 2.0f);
            long size1 = randomInt(300, 20000);
            VWAP vwap1 = new VWAP(rate1, size1, instrument);
            initialBuyPrices.add(vwap1);

            float rate2 = randomFloat(0.6f, 2.0f);
            long size2 = randomInt(300, 20000);
            VWAP vwap2 = new VWAP(rate2, size2, instrument);
            initialSellPrices.add(vwap2);
        });

        orderRequestListener.init(this, initialBuyPrices, initialSellPrices);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {

        boolean bRun = true;
        while (bRun) {
            try {
                if ( (listeners.size() > 0) && (subscribedInstruments.size() > 0) ) {
                    listeners.forEach( l -> l.handleOrderRequests(this, getBuyOrderRequests(), getSellOrderRequests()) );
                }

                // sleep for a while
                try {
                    Thread.currentThread().sleep((long)(Math.random() * TEN_SECONDS));
                } catch (InterruptedException e) { }
            } catch (Exception e) {
                e.printStackTrace();
                bRun = false;
            }
        }
    }

    private List<OrderRequest> getBuyOrderRequests() {
        return getRequests(BidAskType.BID);
    }

    private List<OrderRequest> getSellOrderRequests() {
        return getRequests(BidAskType.ASK);
    }

    private List<OrderRequest> getRequests(BidAskType side) {

        List<OrderRequest> rv = new ArrayList<>();
        subscribedInstruments.forEach( inst -> {

            OrderType orderType = OrderType.NEW;
            if ( randomFloat(0.0f, 10.0f) > 5.0f )
                orderType = OrderType.CANCEL;

            OrderRequest req = new OrderRequest(orderType, side, inst, randomFloat(0.5f, 2.4f), randomInt(500, 20000), this);
            rv.add(req);
        });

        return rv;
    }

    // Used to create data.
    private float randomFloat(float min, float max) {
        return (random.nextFloat() * (max - min)) + min;
    }

    private double randomDouble(double min, double max) {
        return (random.nextDouble() * (max - min)) + min;
    }

    private int randomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
