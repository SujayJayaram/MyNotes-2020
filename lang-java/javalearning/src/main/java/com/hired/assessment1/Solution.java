package com.hired.assessment1;

@SuppressWarnings("Duplicates")
class Solution {
    public static long solution(long[] prices) {
        if ( prices.length == 0 )
            return 0;


        long minVal = prices[0];
        long maxVal = prices[0];
        long bestProfit = Long.MIN_VALUE; // -99999999

        for(int i = 0; i < prices.length; i++) {
            long val = prices[i];
            maxVal = (val > maxVal)? val : maxVal;

            if ( val < minVal ) {
                maxVal = Long.MIN_VALUE; // invalidate it
                minVal = val;
            }

            if ( Long.MIN_VALUE != maxVal ) {
                long profit = maxVal - minVal;
                bestProfit = (profit > bestProfit) ? profit : bestProfit;
            }
        }

        return bestProfit;
    }

    public static void main(String[] args) {
        long[] prices = {13,10,8,4,10};

        long solution = solution(prices);

        System.out.println(solution);
    }
}
