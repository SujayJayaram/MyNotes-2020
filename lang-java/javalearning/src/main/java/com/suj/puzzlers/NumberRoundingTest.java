package com.suj.puzzlers;

/**
 * Created by sujayjayaram on 10/02/2016.
 */
public class NumberRoundingTest {

    public static void main(String[] args){

        int i = 5;
        System.out.println(i/2); // Prints 2 as we are dividing an int (which is then rounded down)

        System.out.println(i/2.0); // This prints 2.5 as we force the calc to be based on double or float
    }
}
