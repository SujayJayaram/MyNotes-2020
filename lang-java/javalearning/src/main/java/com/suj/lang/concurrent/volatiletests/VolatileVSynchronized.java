package com.suj.lang.concurrent.volatiletests;

/**
 * Created by sujayjayaram on 12/01/2016.
 * I used this class as a test for the IntelliJ extract interface functionality
 */
public interface VolatileVSynchronized {
    int getI1();

    // Always get the latest version of JUST i2 as its declared volatile
    int getI2();

    // Synchronized call means that all locallu cached thread variables are
    // refreshed from main memory.
    int getI3();
}
