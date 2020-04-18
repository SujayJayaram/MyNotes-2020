package com.top30javainterviewquestions.isprimenumber;

public class Solution {

    public static boolean isPrime(int num) {
        if ( num <=1 )
            return false;

        if ( num == 2 )
            return true;

        int mid = num/2 + 1;
        for(int i = mid; i > 1; i--) {
            if ( (num % i) == 0 )
                return false;
        }

        return true;
    }


    public static void main(String[] args) {
        for(int i = 1; i < 255; i++) {
            if ( isPrime(i) )
                System.out.println(i);
        }
    }

}
