package com.suj.functiondefs;

import java.util.ArrayList;
import java.util.List;

public class MarketNewswire2FuncDefs {

    static void newMarketPrice(MarketType marketType, double price,
                                      double priceThreshold, List<PriceListener> listeners) {
        if ( price < priceThreshold)
            return;

        for(PriceListener priceListener : listeners) {
            if ( priceListener.getMarketType() == marketType )
                priceListener.acceptPrice(price);
        }
    }

}




