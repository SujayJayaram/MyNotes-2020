package com.suj.functiondefs;

public interface PriceListener {
    // Notify this listener that this
    // is the new price for this asset
    void acceptPrice(double midPrice);

    MarketType getMarketType();
}



