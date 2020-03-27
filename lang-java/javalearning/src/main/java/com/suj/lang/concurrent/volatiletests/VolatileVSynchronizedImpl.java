package com.suj.lang.concurrent.volatiletests;

/**
 * Created by sujayjayaram on 12/01/2016.
 */
public class VolatileVSynchronizedImpl implements VolatileVSynchronized {

    private int i1;
    private volatile int i2;
    private int i3;

    public int getI1(){
        return i1;
    }


    // Always get the latest version of JUST i2 as its declared volatile
    public int getI2(){
        return i2;
    }

    // Synchronized call means that all locally cached thread variables are
    // refreshed from main memory, even if we had referred to i1 and i2 here.
    public synchronized int getI3(){
        return i3;
    }
}
