package com.suj.lang.collections;

/**
 * Created by sujayjayaram on 27/01/2016.
 * DOES NOT IMPLEMENT COMPARABLE
 */
public class UnsortableItem {
    private int myInt;
    private String myString;

    public UnsortableItem(int myInt, String myString) {
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
}
