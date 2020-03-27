package com.suj.functiondefs;

import java.util.ArrayList;
import java.util.List;

public class MarketNewswire1 {
    private List<PriceListener> listeners = new ArrayList<>();

    private double priceThreshold;

    public  MarketNewswire1(double priceThreshold){
        this.priceThreshold = priceThreshold;
    }

    public void addListener(PriceListener pl) {
        listeners.add(pl);
    }

    public void newMarketPrice(MarketType marketType, double price) {
        if ( price < priceThreshold)
            return;

        for(PriceListener priceListener : listeners) {
            if ( priceListener.getMarketType() == marketType )
                priceListener.acceptPrice(price);
        }
    }

}



