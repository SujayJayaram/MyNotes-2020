package com.bbva.fxprototype.model;

import java.util.List;

/**
 * Notified by a Market when there are changes in it's order book (i.e. changes in the depth of market)
 */
public interface OrderRequestListener {
    /**
     * Allows the listener to set itself up, before receiving and subsequent handleOrderRequests() calls.
     * This method is guaranteed to be called (and completes) before any handleOrderRequests() calls.
     * @param market
     * @param initialBuyPrices - a list of the current bid VWAP rates for the instruments to which we have subscribed.
     * @param initialSellPrices - a list of the current ask VWAP rates for the instruments to which we have subscribed.
     */
    void init(Market market, List<VWAP> initialBuyPrices, List<VWAP> initialSellPrices);

    /**
     * Called to notify changes in a market
     * @param market
     * @param buyRequests - affects the bid VWAP rates for various instruments to which we have subscribed.
     * @param sellRequests - affects the ask VWAP rate for various instruments to which we have subscribed.
     */
    void handleOrderRequests(Market market, List<OrderRequest> buyRequests, List<OrderRequest> sellRequests);
}
