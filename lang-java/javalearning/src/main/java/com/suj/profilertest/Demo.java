package com.suj.profilertest;

import java.util.stream.IntStream;

/**
 * Created by sujayjayaram on 03/02/2016.
 */
public class Demo {

    public static void main(String[] args){

        try {
            for(int i = 0; i < 20; i++) {
                System.out.println("Sleeping for 10 secs...");
                Thread.currentThread().sleep(10 * 1000);

                doLargeLoop();

                System.out.println("Sleeping for 10 secs...");
                Thread.currentThread().sleep(10 * 1000);

                doLargeLoop();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void doLargeLoop() {
        IntStream.range(0, Integer.MAX_VALUE).forEach(i -> System.out.println("Creating " + i));
    }
}
