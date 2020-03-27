package com.suj.functiondefs;

public class Main {
    public static void main(String[] args) {
        MarketNewswire2 newswire1 = new MarketNewswire2(11.9);
        newswire1.addListener(null);
        newswire1.newMarketPrice(MarketType.APPLES, 11);
    }
}
