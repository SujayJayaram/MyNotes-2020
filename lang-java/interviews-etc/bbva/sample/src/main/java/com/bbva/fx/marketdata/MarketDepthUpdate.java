package com.bbva.fx.marketdata;

import java.util.List;

/**
 * Container class that holds the bid/ask deltas for a given update.
 */
public class MarketDepthUpdate {

	private final List<MarketDepthDelta> bidDeltaUpdates;
	private final List<MarketDepthDelta> askDeltaUpdates;
	
	public MarketDepthUpdate(List<MarketDepthDelta> bidDeltaUpdates, List<MarketDepthDelta> askDeltaUpdates) {
		this.bidDeltaUpdates = bidDeltaUpdates;
		this.askDeltaUpdates = askDeltaUpdates;
	}

	public List<MarketDepthDelta> getBidDeltaUpdates() {
		return bidDeltaUpdates;
	}

	public List<MarketDepthDelta> getAskDeltaUpdates() {
		return askDeltaUpdates;
	}
}
