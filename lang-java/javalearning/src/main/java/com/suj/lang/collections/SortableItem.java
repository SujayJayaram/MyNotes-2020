package com.suj.lang.collections;

/**
 * Created by sujayjayaram on 27/01/2016.
 */
public class SortableItem implements Comparable<SortableItem>{
    private int myInt;
    private String myString;

    public SortableItem(int myInt, String myString) {
        this.myInt = myInt;
        this.myString = myString;
    }

    @Override
    public String toString() {
        return "UnsortableItem{" +
                "myInt=" + myInt +
                ", myString='" + myString + '\'' +
                '}';
    }

    @Override
    public int compareTo(SortableItem o) {
        return myString.compareTo(o.myString);
    }
}
