package com.bbva.fxprototype.controller;

import com.bbva.fxprototype.model.OrderRequest;
import com.bbva.fxprototype.model.VWAP;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Stores and recalculates VWAP according to changes in the order book which are communicated
 * as OrderRequest objects.
 * I have illustrated a non-blocking synchronisation approach here for efficiency reasons.
 *
 * PLEASE NOTE in the current design this class is guarded by the MarketPriceReporter class
 * so it doesn't actually need to be thread safe in this context.
 */
public class VWAPProcessor {
    /* Allows us to avoid costly synchronisation if used in a highly concurrent environment */
    AtomicReference<VWAP> vwapRef;

    public VWAPProcessor(VWAP vwap) {
        this.vwapRef = new AtomicReference<VWAP>(vwap);
    }

    /**
     * Updates the running calc of VWAP with the details of a new OrderRequest.
     * @param orderRequest
     */
    void update(OrderRequest orderRequest) {

        boolean success = false;
        while(!success) {
            VWAP vwap = vwapRef.get();

            double rateSizeProductSum = vwap.getRateSizeProduct();
            long sizeSum = vwap.getTotalSize();

            switch (orderRequest.getOrderType()) {
                case NEW:
                    rateSizeProductSum += orderRequest.getRate() * orderRequest.getSize();
                    sizeSum += orderRequest.getSize();
                    break;

                case AMEND:
                    // This is just a CANCEL then a NEW
                    rateSizeProductSum -= orderRequest.getOldRate() * orderRequest.getOldSize();
                    rateSizeProductSum += orderRequest.getRate() * orderRequest.getSize();

                    sizeSum -= orderRequest.getOldSize();
                    sizeSum += orderRequest.getSize();

                    break;

                case CANCEL:
                    rateSizeProductSum -= orderRequest.getRate() * orderRequest.getSize();
                    sizeSum -= orderRequest.getSize();
                    break;

                default:
                    throw new RuntimeException("Should never get here!");
            }

            // Use CAS semantics instead of synchronising at a larger level.
            VWAP vwapNew = new VWAP(rateSizeProductSum, sizeSum, vwap.getFxInstrument());
            success = vwapRef.compareAndSet(vwap, vwapNew);
        }
    }

    String getCurrentRateString() {
        return vwapRef.get().getRateString();
    }

    float getCurrentRate() {
        return vwapRef.get().getRate();
    }
}
