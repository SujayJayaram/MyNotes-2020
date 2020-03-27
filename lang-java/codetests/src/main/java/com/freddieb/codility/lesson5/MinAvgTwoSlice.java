package com.freddieb.codility.lesson5;

/**
 * Created by sujayjayaram on 26/12/2016.
 */
public class MinAvgTwoSlice {
    public static double solution(int[] a) {
        double minAve = Integer.MAX_VALUE;
        int[] sums = new int[a.length];
        for(int i = 0; i < a.length; i++) {
            if ( i == 0 )
                sums[0] = a[0];
            else
                sums[i] = sums[i-1] + a[i];

            for(int j = 2; j <= i; j++) {
                if ( j == 4 )
                    break; // see weblink for this lesson to see the theory behind this

                double thisAve = (double)(sums[i] - sums[i-j])/j;
                minAve = (minAve < thisAve)? minAve : thisAve;
            }
        }

        return minAve; // Return the value not the index as in the original problem
    }



//    public int solutionZZZ(int[] A) {
//        // Return value is an index, not the average itself
//        int minP = 0;
//        float minAvg = Integer.MAX_VALUE;
//        for (int P = 0; P < A.length; P++) {
//            int p1Val = A[P];
//            int slice = 1;
//            for (int Q = P + 1; Q < A.length; Q++) {
//                slice++;
//                if (slice > 3) {
//                    break;
//                }
//                int p2Val = A[Q];
//                p1Val += p2Val;
//                float tmpAvg = (float) p1Val / slice;
//                if (tmpAvg < minAvg) {
//                    minAvg = tmpAvg;
//                    minP = P;
//                }
//            }
//        }
//        return minP;
//    }
}
