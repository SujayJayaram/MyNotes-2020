package com.suj.util;

import java.lang.ref.WeakReference;
import java.util.stream.IntStream;

/**
 * Created by sujayjayaram on 03/02/2016.
 */
public class WeakRefDemo {

    public static void main(String[] args){
        Integer i = new Integer(5);

        WeakReference<Integer> iWeak = new WeakReference<Integer>(i);

        // VM Options: -Xms12M -Xmx12M
        i = null;

        while( (iWeak != null) && (iWeak.get() != null) )
            doLeakMemory();

        System.out.println("iWeak: " + iWeak);
        System.out.println("iWeak.get(): " + iWeak.get());
        System.out.println("Ends");

    }

    private static void doLeakMemory() {
        System.out.println("doLeakMemory()");
        IntStream.range(0, 10).forEach( i -> {
            new Integer(i);
            StringBuffer sb = new StringBuffer(20000);
            //System.out.println(i);
        });
        try {
            Thread.currentThread().sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
