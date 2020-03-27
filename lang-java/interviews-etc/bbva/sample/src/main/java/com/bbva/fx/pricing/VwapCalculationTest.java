package com.bbva.fx.pricing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbva.fx.common.CurrencyPair;
import com.bbva.fx.marketdata.MarketDataSource;
import com.bbva.fx.marketdata.SimulatedVenueMarketDataSource;

/**
 * Test class to kick start the VWAP calculation.
 */
public class VwapCalculationTest {
		
	public static void main(String[] args) {
		// PLEASE NOTE: The following has been manually wired up.
		// This can be dynamically wired up via Spring and/or
		// dynamic contents from DB/DistributedCache.
		
		// Wire up the list of sample currency pairs.
		List<CurrencyPair> ccyPairs = new ArrayList<CurrencyPair>();
		ccyPairs.add(new CurrencyPair("EUR", "USD"));
		ccyPairs.add(new CurrencyPair("GBP", "USD"));
		
		// Wire up the list of sample market data sources.
		List<MarketDataSource> marketDataSources = new ArrayList<MarketDataSource>();
		Map<CurrencyPair,Double> ccyPairSampleRateMap = new HashMap<CurrencyPair,Double>();
		ccyPairSampleRateMap.put(new CurrencyPair("EUR", "USD"), 1.29);
		ccyPairSampleRateMap.put(new CurrencyPair("GBP", "USD"), 1.52);
		SimulatedVenueMarketDataSource simulatedVenueMarketDataSource = new SimulatedVenueMarketDataSource(ccyPairSampleRateMap);
		marketDataSources.add(simulatedVenueMarketDataSource);
		
		for (CurrencyPair ccyPair : ccyPairs) {
			TwowayVwap twowayVwap = new TwowayVwap(
				ccyPair,
				new DefaultVwapCalc(),
				new DefaultVwapCalc());
			
			// Subscribe to all known market data sources for each currency pair.
			// The listener handle will be the twowapVwap created for the currency
			// pair. It will receive all market depth updates and will perform
			// the Vwap calculation on each market depth update.
			for (MarketDataSource marketDataSource : marketDataSources) {
				marketDataSource.subscribeForMarketDepthUpdates(ccyPair, twowayVwap);
			}
		}
		
		// Called to start the internal rate generation in this simulated implementation
		// after all subscribeForMarketDepthUpdates(...) is called (as it currently doesn't 
		// support sending initial snapshot on first callback).
		simulatedVenueMarketDataSource.start();
	}	
	
}
