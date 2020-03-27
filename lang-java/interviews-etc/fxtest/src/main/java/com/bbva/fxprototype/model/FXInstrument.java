package com.bbva.fxprototype.model;

/**
 * Represents a traded ccy pair. We will subscribe for changes on this
 * pair in specific markets.
 */
public class FXInstrument {
    private final String baseCcy;
    private final String quoteCcy;

    /**
     * Ctr
     * @param baseCcy
     * @param quoteCcy
     */
    public FXInstrument(String baseCcy, String quoteCcy) {
        this.baseCcy = baseCcy;
        this.quoteCcy = quoteCcy;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public String getQuoteCcy() {
        return quoteCcy;
    }

    public String getCcyPair() {
        return baseCcy + quoteCcy;
    }

    @Override
    public String toString() {
        return "FXInstrument{" +
                "baseCcy='" + baseCcy + '\'' +
                ", quoteCcy='" + quoteCcy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FXInstrument that = (FXInstrument) o;

        if (baseCcy != null ? !baseCcy.equals(that.baseCcy) : that.baseCcy != null) return false;
        return quoteCcy != null ? quoteCcy.equals(that.quoteCcy) : that.quoteCcy == null;

    }

    @Override
    public int hashCode() {
        int result = baseCcy != null ? baseCcy.hashCode() : 0;
        result = 31 * result + (quoteCcy != null ? quoteCcy.hashCode() : 0);
        return result;
    }
}
