package com.bbva.fxprototype.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Stores the Volume Weighted Average Price
 */
public class VWAP {
    /* Can use a float here as fx rates are quoted out to only 5 decimals (or 3 decimals when JPY Is quote ccy) */
    final float rate;

    /* cumulative count of all open same-side orders in the order book */
    final long totalSize;

    /* stored as an optimisation */
    double rateSizeProduct;

    /* The ccy pair */
    final FXInstrument fxInstrument;

    /**
     * Standard Ctr
     * @param rate
     * @param totalSize
     * @param fxInstrument
     */
    public VWAP(float rate, long totalSize, FXInstrument fxInstrument) {
        this.rate = rate;
        this.totalSize = totalSize;
        this.rateSizeProduct = rate * totalSize; // worked out in anticipation
        this.fxInstrument = fxInstrument;
    }

    /**
     * Alternate ctr that avoids us having to discard a needed computation
     * @param rateSizeProduct
     * @param totalSize
     * @param fxInstrument
     */
    public VWAP(double rateSizeProduct, long totalSize, FXInstrument fxInstrument){
        DecimalFormat df = new DecimalFormat("#.#####");
        df.setRoundingMode(RoundingMode.HALF_UP);
        Double d = new Double(rateSizeProduct/totalSize);
        this.rate = (new Float(df.format(d))).floatValue();
        this.totalSize = totalSize;
        this.rateSizeProduct = rateSizeProduct;
        this.fxInstrument = fxInstrument;
    }

    public String getRateString()
    {
        DecimalFormat df = new DecimalFormat("#.#####");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(rate);
    }

    public float getRate() {return rate; }

    public long getTotalSize() {
        return totalSize;
    }

    public double getRateSizeProduct() { return rateSizeProduct; }

    public FXInstrument getFxInstrument() {
        return fxInstrument;
    }
}
