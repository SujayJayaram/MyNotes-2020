package com.geekforgeeks.knapsack;

// https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
public class Solution1 {

    // Returns the maximum value that can be put in a knapsack of capacity W
    static int maxKnapSackValue(int maxWeight, int weightsAray[], int valuesArray[], int currVal) {

        System.out.println("maxKnapSackValue() called for maxWeight=" + maxWeight + " and currVal=" + currVal);

        if ( maxWeight < 0 ) {
            System.out.println("maxKnapSackValue() returns 0");
            return 0; // terminal
        }

        if ( maxWeight == 0 ) {
            System.out.println("maxKnapSackValue() returns " + currVal);
            return currVal; // terminal
        }

        int maxVal = 0;
        for(int i = 0; i < weightsAray.length; i++) {
            int recurseVal = maxKnapSackValue( (maxWeight-weightsAray[i]), weightsAray, valuesArray,  valuesArray[i]);
            if ( recurseVal > maxVal )
                maxVal = recurseVal;
        }

        System.out.println("maxKnapSackValue() returns " + (maxVal + currVal) );
        return maxVal + currVal;
    }

    public static void main(String[] args) {
        int[] weightsAray = {10, 20, 30};
        int[] valuesArray = {60, 100, 120};
        int maxWeight = 50;
        int maxValue = maxKnapSackValue(maxWeight, weightsAray, valuesArray, 0);
        System.out.println("Max Value = " + maxValue);
    }
}
