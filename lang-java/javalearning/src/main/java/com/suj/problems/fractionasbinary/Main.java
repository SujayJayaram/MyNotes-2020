package com.suj.problems.fractionasbinary;

/**
 * Created by sujayjayaram on 01/03/2016.
 */
public class Main {
    public static void main(String... args){
//        long l = 0.3245L;
//        System.out.println("Double = " + d);
//


        long binary = Double.doubleToLongBits(0.5);
        String strBinary = Long.toBinaryString(binary);
        System.out.println("Binary: " + strBinary);


    }
}
