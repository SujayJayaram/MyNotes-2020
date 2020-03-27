package com.suj.puzzlers;

/**
 * Created by sujayjayaram on 13/02/2016.
 */
public class SwapTwoNumbersWithoutAThirdVariable {

    public static  void main(String... args) {

        int i1 = 999;
        int i2 = 1080;

        i1 = i1 ^ i2;
        System.out.println("i1 = " + i1 + " and i2 = " + i2);

        i2 = i1 ^ i2;
        System.out.println("i1 = " + i1 + " and i2 = " + i2);

        i1 = i1 ^ i2;
        System.out.println("i1 = " + i1 + " and i2 = " + i2);

        // DONE!

        int x = 10;
        int y = 20;
        System.out.println("Before swap:");
        System.out.println("x value: "+x);
        System.out.println("y value: "+y);
        x = x+y;
        y=x-y;
        x=x-y;
        System.out.println("After swap:");
        System.out.println("x value: "+x);
        System.out.println("y value: "+y);

        // DONE!
    }
}
