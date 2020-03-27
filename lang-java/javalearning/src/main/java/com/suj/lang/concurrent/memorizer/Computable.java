package com.suj.lang.concurrent.memorizer;

/**
 * Created by sujayjayaram on 22/01/2016.
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
