package com.bbva.fxprototype.controller;

import com.bbva.fxprototype.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue based price reporter. Async updates from the Market object just cause a work
 * object to be placed on the queue, which is then serviced from a single thread.
 * This reduces the likelyhood of concurrency bugs in the subsequent processing but also, as designs
 * such as the LMAX Disruptor show, doing all the main work in a single thread can be very efficient.
 *
 * This class will maintain VWAP prices for a givcen ccy pair
 * a) by Market
 * b) consolidated across all markets
 */
public class MarketPriceReporter implements OrderRequestListener, Runnable {
    static final String ALL_MARKETS_NAME = "All Markets";

    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

    /* Maps from compound key name to VWAP Processor */
    private Map<String, VWAPProcessor> vwapProcessorMap = new ConcurrentHashMap<>();

    @Override
    public void run() {
        try {
            do {
                workQueue.take().run(); // does the work in a  single thread
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void init(Market market, List<VWAP> initialBuyPrices, List<VWAP> initialSellPrices) {
        // Put these items on a work queue and return as quickly as possible.
        // Subsequent Queue processing will be single threaded and concurrency safe.
        workQueue.add(() -> initFromQueue(market, initialBuyPrices, initialSellPrices));
    }

    private void initFromQueue(Market market, List<VWAP> initialBuyPrices, List<VWAP> initialSellPrices) {
        // Create new VWAPProcessor objects for this market with no checkc required.
        // Then create a VWAPProcessor for 'All Markets' - this needs to be done with a putIfAbsent() check
        // as a previous market may have already created it.
        initialBuyPrices.forEach( vwap -> {
            String key = getCompoundKeyName(market.getName(), vwap.getFxInstrument().getCcyPair(), BidAskType.BID);
            vwapProcessorMap.put(key, new VWAPProcessor(vwap));

            String keyAllMarkets = getCompoundKeyName(ALL_MARKETS_NAME, vwap.getFxInstrument().getCcyPair(), BidAskType.BID);
            vwapProcessorMap.putIfAbsent(keyAllMarkets, new VWAPProcessor(vwap)); // the 'new VWAPProcessor(vwap)' overhead is small so no problem if we need to discard it.
        });

        initialSellPrices.forEach( vwap -> {
            String key = getCompoundKeyName(market.getName(), vwap.getFxInstrument().getCcyPair(), BidAskType.ASK);
            vwapProcessorMap.put(key, new VWAPProcessor(vwap));

            String keyAllMarkets = getCompoundKeyName(ALL_MARKETS_NAME, vwap.getFxInstrument().getCcyPair(), BidAskType.ASK);
            vwapProcessorMap.putIfAbsent(keyAllMarkets, new VWAPProcessor(vwap));
        });
    }

    @Override
    public void handleOrderRequests(Market market, List<OrderRequest> buyRequests, List<OrderRequest> sellRequests) {
        // Put these items on a work queue and return as quickly as possible.
        // Subsequent Queue processing will be single threaded and concurrency safe.
        workQueue.add(() -> handleOrderRequestsFromQueue(market, buyRequests, sellRequests));
    }

    private void handleOrderRequestsFromQueue(Market market, List<OrderRequest> buyRequests, List<OrderRequest> sellRequests) {
        // Create a set of all the changed pairs
        Set<String> changedPairs = new HashSet<>();

        buyRequests.forEach(req -> {
                    String key = getCompoundKeyName(market.getName(), req.getFxInstrument().getCcyPair(), BidAskType.BID);
                    updateVWAPProcessor(key, req);

                    String keyAllMarkets = getCompoundKeyName(ALL_MARKETS_NAME, req.getFxInstrument().getCcyPair(), BidAskType.BID);
                    updateVWAPProcessor(keyAllMarkets, req);

                    changedPairs.add(req.getFxInstrument().getCcyPair());
                });

        sellRequests.forEach(req -> {
            String key = getCompoundKeyName(market.getName(), req.getFxInstrument().getCcyPair(), BidAskType.ASK);
            updateVWAPProcessor(key, req);

            String keyAllMarkets = getCompoundKeyName(ALL_MARKETS_NAME, req.getFxInstrument().getCcyPair(), BidAskType.ASK);
            updateVWAPProcessor(keyAllMarkets, req);

            changedPairs.add(req.getFxInstrument().getCcyPair());
        });

        // Print out the latest two way prices for each of the changed pairs.
        changedPairs.forEach( ccyPair -> {
            String bidKey = getCompoundKeyName(market.getName(), ccyPair, BidAskType.BID);
            VWAPProcessor bidProcessor = vwapProcessorMap.get(bidKey);

            String askKey = getCompoundKeyName(market.getName(), ccyPair, BidAskType.ASK);
            VWAPProcessor askProcessor = vwapProcessorMap.get(askKey);

            System.out.println("********************************************************************************************");

            System.out.println(market.getName() + ": Latest Rates for " + ccyPair + ": Bid => " + bidProcessor.getCurrentRateString()
                + " Ask => " + askProcessor.getCurrentRateString());

            String bidKeyAll = getCompoundKeyName(ALL_MARKETS_NAME, ccyPair, BidAskType.BID);
            VWAPProcessor bidProcessorAll = vwapProcessorMap.get(bidKeyAll);

            String askKeyAll = getCompoundKeyName(ALL_MARKETS_NAME, ccyPair, BidAskType.ASK);
            VWAPProcessor askProcessorAll = vwapProcessorMap.get(askKeyAll);

            System.out.println(ALL_MARKETS_NAME + ": Latest Rates for " + ccyPair + ": Bid => " + bidProcessorAll.getCurrentRateString()
                    + " Ask => " + askProcessorAll.getCurrentRateString());

            System.out.println("********************************************************************************************");

        });

    }

    private void updateVWAPProcessor(String key, OrderRequest req) {
        VWAPProcessor processor = vwapProcessorMap.get(key);
        if ( processor == null )
            System.out.println("ERROR: Could not get ASK price for " + req.getFxInstrument().getCcyPair());
        else
            processor.update(req);
    }

    // I could have created a compound class to put into the map as a key (overriding hashcode() and
    // equals()) however there would be no other use for this class so I am just creating this method
    // instead.
    private String getCompoundKeyName(String marketName, String ccyPair, BidAskType bidAskType) {
        // I believe String interning will stop there being an explosion of String objects here.
        return marketName + ":" + ccyPair + ":" + bidAskType;
    }
}
