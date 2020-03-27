package com.suj.functiondefs;

import java.util.ArrayList;
import java.util.List;

public class MarketNewswire2 {
    private List<PriceListener> listeners = new ArrayList<>();

    private double priceThreshold;

    public MarketNewswire2(double priceThreshold){
        this.priceThreshold = priceThreshold;
    }

    public void addListener(PriceListener pl) {
        listeners.add(pl);
    }

    public void newMarketPrice(MarketType marketType, double price) {
        /*
        Delegate to the static FunctionDefs class
         */
        MarketNewswire2FuncDefs.newMarketPrice(marketType, price, priceThreshold, listeners);
    }
}


