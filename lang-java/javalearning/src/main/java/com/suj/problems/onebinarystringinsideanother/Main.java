package com.suj.problems.onebinarystringinsideanother;

/**
 * Created by sujayjayaram on 29/02/2016.
 */
public class Main {

    public static void main(String... args) {
        int n = 0b110010011101;
        int m = 0b1010;
        // Want 0b110010101001
        int startBit = 2;
        int endBit = 5;

        int allOnes = ~0;
        System.out.println("All Ones: " + Integer.toBinaryString(allOnes));

        int lhsMask = allOnes << (endBit+1);
        System.out.println("lhsMask: " + Integer.toBinaryString(lhsMask));

        int rhsMask = (1 << startBit) -1;
        System.out.println("rhsMask: " + Integer.toBinaryString(rhsMask));

        int fullMask = lhsMask | rhsMask;
        System.out.println("fullMask: " + Integer.toBinaryString(fullMask));

        int nMasked = n & fullMask;
        System.out.println("nMasked: " + Integer.toBinaryString(nMasked));

        int mShifted = m << startBit;
        System.out.println("mShifted: " + Integer.toBinaryString(mShifted));

        int result = nMasked | mShifted;
        System.out.println("result: " + Integer.toBinaryString(result));
    }
}
