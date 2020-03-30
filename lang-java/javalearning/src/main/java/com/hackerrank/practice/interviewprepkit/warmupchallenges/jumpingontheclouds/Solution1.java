package com.hackerrank.practice.interviewprepkit.warmupchallenges.jumpingontheclouds;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup&h_r=next-challenge&h_v=zen

public class Solution1 {
    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        return jumpingOnClouds(0, c, 0);
    }

    static int jumpingOnClouds(int currPos, int[] c, int stepCount) {

        if ( (currPos > (c.length-1)) || (c[currPos] == 1) )
            return Integer.MAX_VALUE; // Overrun or bad cloud jump

        if ( currPos == (c.length-1) )
            return stepCount; // Got to last cloud

        // Recurse
        stepCount++;
        int branch1Steps = jumpingOnClouds(currPos+1, c, stepCount);
        int branch2Steps = jumpingOnClouds(currPos+2, c, stepCount);

        return (branch1Steps < branch2Steps)? branch1Steps : branch2Steps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();
        System.out.println("Result = " + result);

        scanner.close();
    }
}
