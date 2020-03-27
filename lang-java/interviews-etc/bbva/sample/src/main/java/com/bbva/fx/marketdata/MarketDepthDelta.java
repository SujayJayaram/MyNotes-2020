package com.bbva.fx.marketdata;

import com.bbva.fx.common.Action;

/**
 * Holds the delta market depth update at a given rate.  
 */
public class MarketDepthDelta {

	/** indicate the delta action of this rate. */
	private final Action action;
	/** fx rate */
	private final double rate;
	/** volume available at the above rate. */
	private final double volume;
	/** used primary for action of UPDATE holding the previous volume */
	private final double oldVolume;
	
	public MarketDepthDelta(Action action, double rate, double volume) {
		this(action, rate, volume, 0);
	}
	
	public MarketDepthDelta(Action action, double rate, double volume, double oldVolume) {
		this.action = action;
		this.rate = rate;
		this.volume = volume;
		this.oldVolume = oldVolume;
	}

	public Action getAction() {
		return action;
	}

	public double getRate() {
		return rate;
	}

	public double getVolume() {
		return volume;
	}
	
	public double getOldVolume() {
		return oldVolume;
	}
}
