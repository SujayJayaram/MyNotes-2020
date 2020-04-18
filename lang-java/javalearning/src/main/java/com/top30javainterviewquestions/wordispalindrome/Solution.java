package com.top30javainterviewquestions.wordispalindrome;

public class Solution {

    public static boolean isPalindrome(String word) {
        int wordLength = word.length();
        int midIdx = wordLength/2;

        if ( (wordLength % 2) == 1 ) {
            // Odd length logic, start from the mid point and radiate out
            for(int i = 0; i < midIdx; i++) {
                int lhsIdx = (midIdx-1) -i;
                int rhsIdx = (midIdx+1) +i;
                if ( word.charAt(lhsIdx) != word.charAt(rhsIdx) ) {
                    return false;
                }
            }
        }
        else {
            // Even length
            for(int i = 0; i < midIdx; i++) {
                int lhsIdx = i;
                int rhsIdx = (wordLength-1)-i;
                if ( word.charAt(lhsIdx) != word.charAt(rhsIdx) ) {
                    return false;
                }
            }
        }

        return true;
    }

}
