package com.hackerrank.practice.interviewprepkit.warmupchallenges.countingvalleys;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/counting-valleys/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup

public class Solution1 {


    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {

        int numValleys = 0;
        int level = 0;
        boolean goingDown = false;

        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            switch(ch) {
                case 'U':
                    level++;
                    goingDown = false;
                    break;
                case 'D':
                    level--;
                    goingDown = true;
                    break;
                default:
                    throw new RuntimeException("Should never get here!");
            }

            if ( !goingDown && (level == 0) )
                numValleys++;
        }

        return numValleys;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        System.out.println("Result = " + result);

        scanner.close();
    }
}
