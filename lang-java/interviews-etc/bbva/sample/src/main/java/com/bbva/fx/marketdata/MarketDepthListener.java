package com.bbva.fx.marketdata;

/**
 * Listener handle that will be called when a market depth update
 * is available.
 */
public interface MarketDepthListener {
	/**
	 * This function will be called when market depth
	 * updates are available.
	 * 
	 * @param marketDepthUpdate contains the bid/ask market depth deltas.
	 */
	void onMarketDepthUpdate(MarketDepthUpdate marketDepthUpdate);
}
