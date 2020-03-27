package com.bbva.fx.pricing;

import com.bbva.fx.common.CurrencyPair;
import com.bbva.fx.marketdata.MarketDepthListener;
import com.bbva.fx.marketdata.MarketDepthUpdate;

/**
 * Holds the bid and ask VWAP calculation for a given currency pair.
 * As this class listens for market depth updates, it will perform
 * recalculation of the bid and/or ask VWAP each time it receives a 
 * market depth update.
 */
public class TwowayVwap implements MarketDepthListener {
	
	private final CurrencyPair ccyPair;
	private final VwapCalc bidVwapCalc;
	private final VwapCalc askVwapCalc;
	
	public TwowayVwap(CurrencyPair ccyPair, VwapCalc bidVwapCalc, VwapCalc askVwapCalc) {
		this.ccyPair = ccyPair;
		this.bidVwapCalc = bidVwapCalc;
		this.askVwapCalc = askVwapCalc;
	}

	/**
	 * This callback function will be invoked when there are market depth updates.
	 */
	@Override
	public void onMarketDepthUpdate(MarketDepthUpdate marketDepthUpdate) {
		// Synchronizing the bid and ask separately yields better performance
		// when we have multiple market data source calling this function
		// concurrently.
		double bidVwap;		
		synchronized (this) {
			bidVwap = bidVwapCalc.calc(marketDepthUpdate.getBidDeltaUpdates());
		}
		
		double askVwap;
		synchronized (this) {
			askVwap = askVwapCalc.calc(marketDepthUpdate.getAskDeltaUpdates());
		}
		
		// Prints out the 2 way VWAP price reflecting the latest market depth delta changes.
		System.out.println(String.format("%s : bidVwap=%f, askVwap=%f", ccyPair, bidVwap, askVwap));
	}	
}
