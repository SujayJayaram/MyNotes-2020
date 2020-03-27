package com.suj.spring.annotations1;

/**
 * Created by sujayjayaram on 21/02/2016.
 */
public class RedColor implements MyColor{

    @Override
    public void printColor() {
        System.out.println("It is red in color...");
    }
}
