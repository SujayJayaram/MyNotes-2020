package com.hackerrank.practice.interviewprepkit.warmupchallenges.repeatedstring;

import java.io.IOException;
import java.util.Scanner;

// https://www.hackerrank.com/challenges/repeated-string/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
public class Solution1 {

    // ***** TAKES TOO LONG
    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {

        long count = 0;
        int base = s.length();
        for(long i = 0; i < n; i++) {
            int idx = (int)i % base;
            if ( s.charAt(idx) == 'a' )
                count++;
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();
        System.out.println("Result = " + result);

        scanner.close();
    }
}
