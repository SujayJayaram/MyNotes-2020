package com.bbva.fx.marketdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.bbva.fx.common.Action;
import com.bbva.fx.common.CurrencyPair;

/**
 * Test implementation of MarketDataSource that simulate market data
 * to notify interested subscribers.
 * 
 * This simple implementation of MarketDataSource doesn't support
 * returning the initial market depth snapshot in the first callback
 * on method onMarketDepthUpdate(..). Hence, user of this need to be
 * aware all subscriber handles must be all registered before the 
 * start() method is called.
 * 
 * The proper implementation of MarketDataSource should implement the 
 * snapshot update on first callback.
 */
public class SimulatedVenueMarketDataSource implements MarketDataSource {

	private class RateGeneratorThread extends Thread {
		private final List<MarketDepthListener> listeners;
		private final double sampleRateToUse;
		private List<MarketDepthDelta> prevBidMarketDepthAdds;
		private List<MarketDepthDelta> prevAskMarketDepthAdds;
		
		public RateGeneratorThread(List<MarketDepthListener> listeners, double sampleRateToUse) {
			this.listeners = listeners;
			this.sampleRateToUse = sampleRateToUse;
		}
		
		public void run() {
			while (true) {
				// Very basic simulator that will generate 5 new market depth for bid/ask and 
				// will remove all previous market depths. With real market data, it can either
				// be n number of:
				//  - ADD : when there are new market data values.
				//  - DELETE : when an existing market depth is no longer valid.
				//  - UPDATE : when an existing volume have change but same rate.
				List<MarketDepthDelta> bidMarketDepthAdds = generateMarketDepthAdd(5, sampleRateToUse, sampleRateToUse + 0.01, 1000000, 10000000);
				List<MarketDepthDelta> askMarketDepthAdds = generateMarketDepthAdd(5, sampleRateToUse + 0.02, sampleRateToUse + 0.03, 1000000, 10000000);
				
				List<MarketDepthDelta> bidMarketDepthUpdates = generateMarketDepthDelete(prevBidMarketDepthAdds);
				bidMarketDepthUpdates.addAll(bidMarketDepthAdds);
				List<MarketDepthDelta> askMarketDepthUpdates = generateMarketDepthDelete(prevAskMarketDepthAdds);
				askMarketDepthUpdates.addAll(askMarketDepthAdds);
				
				try {
					Thread.sleep(random.nextInt(1000));
					
					for (MarketDepthListener listener : listeners) {
						listener.onMarketDepthUpdate(new MarketDepthUpdate(bidMarketDepthUpdates, askMarketDepthUpdates));
					}
				} catch (Exception ex) {
					System.err.println("Error performing market depth update: " + ex);
				} finally {
					prevBidMarketDepthAdds = bidMarketDepthAdds;
					prevAskMarketDepthAdds = askMarketDepthAdds;
				}
			}
		}
		
		private List<MarketDepthDelta> generateMarketDepthAdd(
				double numToGenerate, double rateMinRange,
				double rateMaxRange, double volMinRange, double volMaxRange) {
			
			List<MarketDepthDelta> marketDepthUpdates = new ArrayList<MarketDepthDelta>();
			for (int i=0; i<numToGenerate; i++) {
				marketDepthUpdates.add(new MarketDepthDelta(
					Action.ADD, 
					generateRandomInRange(rateMinRange, rateMaxRange),
					generateRandomInRange(volMinRange, volMaxRange)));
			}
			
			return marketDepthUpdates;
		}
		
		private List<MarketDepthDelta> generateMarketDepthDelete(List<MarketDepthDelta> marketDepthUpdates) {
			List<MarketDepthDelta> marketDepthDeletes = new ArrayList<MarketDepthDelta>();
			
			if (marketDepthUpdates != null) {
				for (MarketDepthDelta marketDepthDelta : marketDepthUpdates) {
					marketDepthDeletes.add(new MarketDepthDelta(Action.DELETE, marketDepthDelta.getRate(), marketDepthDelta.getVolume()));
				}
			}
			
			return marketDepthDeletes;
		}
		
		private double generateRandomInRange(double min, double max) {
			return (random.nextDouble() * (max - min)) + min;
		}
	}
	
	private Random random = new Random();
	private ConcurrentMap<CurrencyPair, List<MarketDepthListener>> ccyPairListenerMap;
	private final Map<CurrencyPair,Double> ccyPairSampleRateMap;
	
	public SimulatedVenueMarketDataSource(Map<CurrencyPair,Double> ccyPairSampleRateMap) {
		ccyPairListenerMap = new ConcurrentHashMap<CurrencyPair, List<MarketDepthListener>>();
		this.ccyPairSampleRateMap = ccyPairSampleRateMap;
	}
	
	@Override
	public void subscribeForMarketDepthUpdates(CurrencyPair ccyPair, MarketDepthListener listener) {
		List<MarketDepthListener> newListeners = new CopyOnWriteArrayList<MarketDepthListener>();
		List<MarketDepthListener> listeners = ccyPairListenerMap.putIfAbsent(ccyPair, newListeners);
		
		if (listeners == null) {
			listeners = newListeners;
		}
		
		listeners.add(listener);
	}
	
	public void start() {
		for (Map.Entry<CurrencyPair,List<MarketDepthListener>> entry : ccyPairListenerMap.entrySet()) {	
			new RateGeneratorThread(entry.getValue(), ccyPairSampleRateMap.get(entry.getKey())).start();
		}
	}
}