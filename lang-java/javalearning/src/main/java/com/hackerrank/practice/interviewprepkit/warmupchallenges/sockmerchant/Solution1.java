package com.hackerrank.practice.interviewprepkit.warmupchallenges.sockmerchant;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution1 {


    /*
    Sample Input
        9
        10 20 20 10 10 30 50 10 20

        Sample output = 3;
     */


    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int val = ar[i];
            map.putIfAbsent(val, 0);

            int valCount = map.get(val) + 1;
            map.put(val, valCount);
        }

        int rv = 0;

        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Integer> mapElement = iter.next();

            // mapElement.getKey()
            // mapElement.getValue()

            int pairs = mapElement.getValue()/2;
            rv += pairs;
        }

        return rv;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

        System.out.println("Result = " + result);
        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}
