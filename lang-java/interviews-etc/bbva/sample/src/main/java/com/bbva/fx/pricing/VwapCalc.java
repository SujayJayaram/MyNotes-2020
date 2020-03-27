package com.bbva.fx.pricing;

import java.util.List;

import com.bbva.fx.marketdata.MarketDepthDelta;

/**
 * Interface to perform VWAP calculation.
 */
public interface VwapCalc {

	/**
	 * This function should calculate the VWAP by applying the 
	 * latest set of market depth deltas against what it's already
	 * accumulated.
	 * 
	 * @param marketDepthDeltas latest set of market depth deltas.
	 * @return the calculated VWAP.
	 */
	double calc(List<MarketDepthDelta> marketDepthDeltas);
}
