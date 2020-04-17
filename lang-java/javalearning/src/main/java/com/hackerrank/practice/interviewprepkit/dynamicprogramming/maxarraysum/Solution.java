package com.hackerrank.practice.interviewprepkit.dynamicprogramming.maxarraysum;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/max-array-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
public class Solution {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {

        if ( arr.length == 0 )
            return 0;

        int max = Integer.MIN_VALUE;
        Map<Params, List< List<Integer> >> cache = new HashMap<>();

        // Get all the possible combinations together.
        // Each element in this list is a single combination
        List< List<Integer> > combinations = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            // Use each item in arr as the start point
            List< List<Integer> > list = getCombinations(cache, i, arr);
            if ( list != null )
                combinations.addAll(list);
        }

        for(List<Integer> combination : combinations) {
            if (combination.size() > 1) {
                int sum = 0;
                for (Integer val : combination)
                    sum += val;

                if (sum > max)
                    max = sum;
            }
        }

        return max;
    }

    static class Params {
        int idx;
        List<Integer> list;

        public Params(int idx, int[] arr) {
            this.idx = idx;
            this.list = new ArrayList<>();
            for(int i : arr)
                list.add(i);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Params param = (Params) o;
            return idx == param.idx &&
                    Objects.equals(list, param.list);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idx, list);
        }
    }

    private static List< List<Integer> > getCombinations(Map<Params, List< List<Integer> >> cache, int idx, int[] arr) {
        // Terminal conditions
        if ( idx >= arr.length )
            return null;

        Params p = new Params(idx, arr);
        List<List<Integer>> cachedResult = cache.get(p);
        if ( cachedResult != null )
            return cachedResult;

        // Return this
        List< List<Integer> > rv = new ArrayList<>();

        // Single item list with the current element is always returned.
        List<Integer> singleItem = new ArrayList<>();
        singleItem.add(arr[idx]);
        rv.add(singleItem);

        // Start from +2 (non-adj)
        for(int i = idx+2; i < arr.length; i++) {
            List< List<Integer> > recursiveCombinations = getCombinations(cache, i, arr);
            if ( recursiveCombinations != null ) {
                for(List<Integer> recursiveCombination : recursiveCombinations) {
                    List<Integer> tmp = new ArrayList<>(singleItem);
                    tmp.addAll(recursiveCombination);

                    // Remove duplicates
                    Set set = new LinkedHashSet(tmp);
                    rv.add(new ArrayList<>(set));
                }
            }
        }

        cache.put(p, rv);
        return rv;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}