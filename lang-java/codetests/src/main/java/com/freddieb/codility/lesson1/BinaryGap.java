package com.freddieb.codility.lesson1;

/**
 * Created by sujayjayaram on 24/12/2016.
 * See https://codility.com/programmers/lessons/1-iterations/binary_gap/
 */
public class BinaryGap {
    public static int solution1(int n) {

        if ( n <= 0 )
            return 0;

        // If the number is greater than zero then there will be a '1' in the binary representation somewhere
        String s = Integer.toBinaryString(n);

        // Remove all trailing zeros as this gap must be surrounded by '1's
        // We remove the last '1' as well.
        s = s.substring(0, s.lastIndexOf('1'));

        int longestGap = 0;
        int currentGap = 0;
        for(int i = 0; i < s.length(); i++) {

            // Advance past '1's
            while ( (i < s.length()) && (s.charAt(i) != '0') )
                i++;

            while ( (i < s.length()) && (s.charAt(i) == '0') ) {
                i++;
                currentGap++;
            }

            longestGap = (longestGap > currentGap)? longestGap : currentGap;
        }

        return longestGap;
    }

    public static int solution2(int n) {
        if ( n <= 0 )
            return 0;

        // If there are trailing zeros, take them off (leaving a '1' as the right most value)
        while (n%2 == 0)
            n /= 2;

        int longestGap = 0;
        int currentGap = 0;
        while ( n > 0 ) {
            if ( n%2 == 1) {
                longestGap = (longestGap > currentGap)? longestGap : currentGap;
                currentGap = 0;
            }
            else
                currentGap++;

            n >>>= 1;
        }

        return (longestGap > currentGap)? longestGap : currentGap;
    }
}
