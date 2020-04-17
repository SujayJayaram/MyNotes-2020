package com.hired.assessment1;

class Solution2 {
    public static long solution(long[] prices) {
        if ( prices.length == 0 )
            return 0;

        long min = prices[0];
        long bestProfit = 0;
        for(int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            long max = maxVal(i, prices);
            long profit = max - min;
            bestProfit = (profit > bestProfit)? profit : bestProfit;
        }

        return bestProfit;
    }

    // Maximum value in this array
    private static long maxVal(int idx, long[] prices) {
        long maxVal = prices[idx];
        for(int i = idx; i < prices.length; i++) {
            maxVal = (prices[i] > maxVal)? prices[i] : maxVal;
        }

        return maxVal;
    }
}
