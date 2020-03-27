package com.suj.problems.sumofcubes;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sujayjayaram on 02/12/2016.
 * Cracking The Code Interview p68.
 * Find all positive integer combinations (up to 1000) of:
 * a^3 + b^3 = c^3 + d^3
 */
public class SumOfCubes2 {

    public static void main(String[] args) {

        Set<Result2> set = new HashSet<>();
        for (int i = 0; i <= 1000; i++) {
            for(int j = 0; j <= 1000; j++) {
                double sumOfCubesLHS = Math.pow(i, 3) + Math.pow(j, 3);

                for(int k = 0; k <= 1000; k++) {
                    for(int l = 0; l <= 1000; l++) {
                        double sumOfCubesRHS = Math.pow(k, 3) + Math.pow(l, 3);

                        if ( sumOfCubesLHS == sumOfCubesRHS ) {
                            Result2 r = new Result2(i, j, k, l);
                            if ( r.isValid() )
                                set.add(r);
                        }
                    }
                }
                //System.out.println(".");
            }
            System.out.println(".");
        }

        for(Result2 r : set)
            System.out.println(r);
    }
}
