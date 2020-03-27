package com.bbva.fx.marketdata;

import com.bbva.fx.common.CurrencyPair;

/**
 * Interface to represents a market data source one can subscribe
 * for market depth updates.
 */
public interface MarketDataSource {
	/**
	 * This function provides ability to subscribe for market depth updates.
	 * 
	 * PLEASE NOTE: It is assumed that upon subscribing to market depth interest
	 * by calling this method, one would initially receive a callback on the 
	 * listener handle containing the initial full market depth. Subsequent
	 * callbacks will only contain deltas to the initial image.
	 * 
	 * It is also assumed implementation of MarketDataSource will be able to 
	 * provide the behaviour described above.
	 * 
	 * @param ccyPair currency pair to subscribe for market depth updates.
	 * @param listener callback that will be invoked when market depth updates are available.
	 */
	void subscribeForMarketDepthUpdates(CurrencyPair ccyPair, MarketDepthListener listener);
}
