package com.top30javainterviewquestions.ishappynumber;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static boolean isHappy(int num) {
        Set<Integer> previousResults = new HashSet<>();

        int sumOfSquares = calcSumOfSquares(num);
        while(sumOfSquares != 1) {
            if ( previousResults.contains(sumOfSquares) )
                return false;

            previousResults.add(sumOfSquares);
            sumOfSquares = calcSumOfSquares(sumOfSquares);
        }

        return true;
    }

    private static int calcSumOfSquares(int num) {
        String sNum = String.valueOf(num);
        char[] digits = sNum.toCharArray();
        int rv = 0;
        for(char digit : digits) {
            int iDigit = Integer.valueOf( Character.toString(digit) );
            rv = rv + (int)Math.pow(iDigit, 2);
        }

        return rv;
    }

    public static void main(String[] args) {
        int[] examples = {23, 4};

        for(int example : examples)
            System.out.println(example + " = " + isHappy(example));
    }
}
