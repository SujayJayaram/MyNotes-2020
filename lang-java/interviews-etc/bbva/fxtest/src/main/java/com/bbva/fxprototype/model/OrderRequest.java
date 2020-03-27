package com.bbva.fxprototype.model;

/**
 * Represents a new piece of level 2 market data denoting a new or or cancelled order.
 * I have called this class OrderRequest rather than just Order to emphasise the fact that
 * we are dealing with REQUESTED/OPEN orders here (and consequently its open VWAP that we are calculating
 * rather than traded VWAP).
 */
public class OrderRequest {

    private final OrderType orderType;
    private final BidAskType bidAskType;
    private final FXInstrument fxInstrument;
    /* Can use a float here as fx rates are quoted out to only 5 decimals (or 3 decimals when JPY Is quote ccy) */
    private final float rate;
    private final double size;

    /* ONLY USED FOR AMEND TYPE */
    private final float oldRate;
    private final double oldSize;

    private final Market market;

    /**
     * Use for NEW and CANCEL Order Types
     * @param orderType
     * @param bidAskType
     * @param fxInstrument
     * @param rate
     * @param size
     * @param market
     */
    public OrderRequest(OrderType orderType, BidAskType bidAskType, FXInstrument fxInstrument, float rate, double size, Market market) {
        this.orderType = orderType;
        this.bidAskType = bidAskType;
        this.fxInstrument = fxInstrument;
        this.rate = rate;
        this.size = size;
        this.market = market;

        // Not used
        this.oldRate = 0;
        this.oldSize = 0;
        if ( this.orderType == OrderType.AMEND )
            throw new RuntimeException("Please use alternative constructure for AMEND types");
    }

    /**
     * Use for AMEND Order Type only
     * @param bidAskType
     * @param fxInstrument
     * @param rate
     * @param size
     * @param oldRate
     * @param oldSize
     * @param market
     */
    public OrderRequest(BidAskType bidAskType, FXInstrument fxInstrument, float rate, double size, float oldRate, double oldSize, Market market) {
        this.orderType = OrderType.AMEND;
        this.bidAskType = bidAskType;
        this.fxInstrument = fxInstrument;
        this.rate = rate;
        this.size = size;
        this.oldRate = oldRate;
        this.oldSize = oldSize;
        this.market = market;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public BidAskType getBidAskType() {
        return bidAskType;
    }

    public FXInstrument getFxInstrument() {
        return fxInstrument;
    }

    public double getRate() {
        return rate;
    }

    public double getSize() {
        return size;
    }

    public float getOldRate() {
        return oldRate;
    }

    public double getOldSize() {
        return oldSize;
    }

    public Market getMarket() { return market; }
}
