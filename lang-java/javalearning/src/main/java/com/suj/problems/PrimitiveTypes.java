package com.suj.problems;

/**
 * Created by sujayjayaram on 21/02/2016.
 */
public class PrimitiveTypes {

    public static void main(String... args){
        // Default for number literals is int or double
        int i = 10;

        long j = 20L;
        long k = 20l; // upper or lower case L allowed

        double d1 = 12.0;

        float f1 = 20.0f;
        float f2 = 20.0F; // upper or lower case F allowed

        int i2 = 0x7FFFFFFF; // Hex (all but the last (negative) bit set

        System.out.println("i2 = " + i2);
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);

        int i3 = 0x80000000; // Hex just the negative bit set

        System.out.println("i3 = " + i3);
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);

        int i4 = 07;
        System.out.println("i4 = " + Integer.toBinaryString(i4) );
    }
}
