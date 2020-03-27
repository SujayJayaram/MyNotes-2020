package com.bbva.fx.common;

/**
 * Class to represent a FX currency pair. 
 */
public class CurrencyPair {

	private final String baseCcy;
	private final String termsCcy;
	private final String ccyPairStr;
	
	public CurrencyPair(String baseCcy, String termsCcy) {
		this.baseCcy = baseCcy;
		this.termsCcy = termsCcy;
		ccyPairStr = baseCcy + "/" + termsCcy;
	}

	public String getBaseCcy() {
		return baseCcy;
	}

	public String getTermsCcy() {
		return termsCcy;
	}
	
	public String toString() {
		return ccyPairStr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseCcy == null) ? 0 : baseCcy.hashCode());
		result = prime * result
				+ ((termsCcy == null) ? 0 : termsCcy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyPair other = (CurrencyPair) obj;
		if (baseCcy == null) {
			if (other.baseCcy != null)
				return false;
		} else if (!baseCcy.equals(other.baseCcy))
			return false;
		if (termsCcy == null) {
			if (other.termsCcy != null)
				return false;
		} else if (!termsCcy.equals(other.termsCcy))
			return false;
		return true;
	}
}
