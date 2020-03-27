package com.tudor;

import java.util.HashSet;
import java.util.Set;

public class NumberOfPairs {

    public static void main(String[] args) {

        int[] a = {1,2,6,2,6};
        long k = 65;

        int numPairs = NumberOfPairs(a, k);
        System.out.println("Number of pairs = " + numPairs);
    }

    // Custom class simply created so that it can be placed in a Set in order to
    // give the number of distinct solutions
    static class SolutionPair {
        int a;
        int b;

        SolutionPair(int x, int y) {
            // Always assign a to the smaller int - helps for the equals comparison
            // as we are told that (1,3) is the same as (3,1)
            if ( x < y ) {
                a = x;
                b = y;
            }
            else {
                a = y;
                b = x;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SolutionPair that = (SolutionPair) o;

            if (a != that.a) return false;
            return b == that.b;

        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            return result;
        }
    }

    private static int NumberOfPairs(int[] a, long k) {
        Set<SolutionPair> set = new HashSet<>();
        for(int i=0; i < a.length; i++) {
            int num1 = a[i];
            for(int j = i+1; j < a.length; j++) {
                int num2 = a[j];
                if ( (num1 + num2) == k ) {
                    set.add(new SolutionPair(num1, num2));
                }
            }
        }

        return set.size();
    }
}
