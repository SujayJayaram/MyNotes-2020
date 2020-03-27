package com.bbva.fxprototype.model;

import java.util.List;

/**
 * A market to which we can subscribe for market depth updates (level 2 market data).
 */
public interface Market {

    /**
     * Adds a listener for depth of market updates (i.e. new order requests)
     * @param subscribedInstruments - the instruments to which we are subscribing
     * @param orderRequestListener - the callback interface
     */
    void addOrderRequestListener(List<FXInstrument> subscribedInstruments, OrderRequestListener orderRequestListener);

    /**
     * @return - the name of this market
     */
    String getName();
}
