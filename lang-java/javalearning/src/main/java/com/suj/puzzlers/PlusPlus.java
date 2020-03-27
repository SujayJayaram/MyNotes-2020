package com.suj.puzzlers;

/**
 * Created by sujayjayaram on 31/01/2016.
 */
public class PlusPlus {

    public static void main (String[] args){
        int i = 1;
        System.out.println("i = " + i); // i = 1

        int a = i++;
        System.out.println("i = " + i); // i = 2
        System.out.println("a = " + a); // a = 1

        int b = ++i;
        System.out.println("i = " + i); // i = 3
        System.out.println("b = " + b); // b = 3
    }
}