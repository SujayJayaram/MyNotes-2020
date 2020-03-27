package com.bbva.fx.pricing;

import java.util.List;

import com.bbva.fx.common.Action;
import com.bbva.fx.marketdata.MarketDepthDelta;

/**
 * Default implementation of VwapCalc that will use the current
 * running accumulated values and then applying the market depth
 * deltas to work out the latest VWAP. Doing it this way avoids the 
 * need to recalculate on the complete market depth.
 * 
 * It is assume we want to work out the VWAP on all of open market
 * depth for the given list of market data venues one have subscribed.
 */
public class DefaultVwapCalc implements VwapCalc {

	private double sumVolumeRate;
	private double sumVolume;
	
	/**
	 * This function will apply the market depth deltas against the 
	 * accumulated values to work out the latest VWAP.
	 */
	@Override
	public double calc(List<MarketDepthDelta> marketDepthDeltas) {
		if (marketDepthDeltas != null) {
			for (MarketDepthDelta marketDepthDelta : marketDepthDeltas) {
				// The market depth delta update can be of 3 types:
				//
				// - ADD    : new market depth has been added.
				// - UPDATE : existing market depth has been updated.
				//            (i.e. same rate but volume has been updated)
				//            It is assumed that the market data source
				//            is able to supply the old volume (i.e. previous
				//            volume before the new volume). This avoid the
				//            need to keep track here and makes calc simple.
				// - DELETE : existing market depth has been deleted.
				Action action = marketDepthDelta.getAction();
				if (action == Action.ADD) {
					// If the market depth delta is an ADD, then we will add
					// to the accumulated total.
					sumVolumeRate += marketDepthDelta.getRate() * marketDepthDelta.getVolume();
					sumVolume += marketDepthDelta.getVolume();
				} else if (action == Action.UPDATE) {
					// If the market depth delta is an UPDATE, we will remove
					// old volume and it's associated rateVolume from the accumulated
					// amount, and add in the new volume and it's associated rateVolume 
					// into the accumulated amount.
					sumVolumeRate -= marketDepthDelta.getRate() * marketDepthDelta.getOldVolume();
					sumVolume -= marketDepthDelta.getOldVolume();
					sumVolumeRate += marketDepthDelta.getRate() * marketDepthDelta.getVolume();
					sumVolume += marketDepthDelta.getVolume();				
				} else if (action == Action.DELETE) {
					// If the market depth delta is a DELETE, then we will remove
					// from the accumulated total. It currently assumes a DELETE
					// action will contain the volume and rate that has been deleted.
					sumVolumeRate -= marketDepthDelta.getRate() * marketDepthDelta.getVolume();
					sumVolume -= marketDepthDelta.getVolume();
				}
			}
		}
		
		double vwap = 0;
		if (sumVolume > 0) {
			vwap = sumVolumeRate / sumVolume;
		}
		
		return vwap;
	}

}
