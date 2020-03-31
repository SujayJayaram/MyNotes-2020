package com.hackerrank.practice.interviewprepkit.arrays.newyearchaos;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
public class Solution1 {

    // Complete the minimumBribes function below.
    // ***** This takes to long
    // See https://www.youtube.com/watch?v=k9RQh21KrH8 for ARRAY INVERSIONS
    static void minimumBribes(int[] q) {

        int numBribes = 0;
        for(int i = 0; i < (q.length-1); i++) {

            // Logic for the too chaotic scenario
            {
                int currPos = i + 1; // i is zero based - currPos is based from 1 just like the elements inside
                int origPos = q[i];

                if ((origPos - currPos) > 2) {
                    System.out.println("Too chaotic");
                    return;
                }
            }

            for (int j = i + 1; j < q.length; j++) {
                if ( q[i] > q[j] )
                    numBribes++;
            }
        }

        System.out.println(numBribes);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
