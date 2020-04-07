package com.hackerrank.practice.interviewprepkit.hashmaps.counttriplets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/count-triplets-1/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps

// TOO SLOW
@SuppressWarnings("Duplicates")
public class Solution2 {
    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> potential = new HashMap<>();
        Map<Long, Long> counter = new HashMap<>();
        long count = 0;
        for (int i = 0; i < arr.size(); i++) {
            long a = arr.get(i);
            long key = a / r;

            // If this is an exact multiple of the ratio....
            if (a % r == 0) {
                if (counter.containsKey(key)) {
                    count += counter.get(key);
                }

                // We have seen this value's geometric predecessor.
                if (potential.containsKey(key)) {
                    // How many times have we seen it?
                    long c = potential.get(key);

                    // Move this value over to the counter
                    counter.put(a, counter.getOrDefault(a, 0L) + c);
                }
            }

            // This ensures we track the number '1' which is not caught by the (a % r == 0) clause.
            // Every number can be the start of a triplet. Track how many times we see each number
            // up to now.
            potential.put(a, potential.getOrDefault(a, 0L) + 1);
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        System.out.println(ans);

        //bufferedWriter.write(String.valueOf(ans));
        //bufferedWriter.newLine();

        bufferedReader.close();
        //bufferedWriter.close();
    }
}

