package com.hackerrank.practice.interviewprepkit.hashmaps.counttriplets;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/count-triplets-1/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps

// TOO SLOW
@SuppressWarnings("Duplicates")
public class Solution1 {
    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {

        // Create a mapping from the value of an array element to it's position in that array.
        // Assume the array may hold repeated values
        Map<Long, List<Integer> > mapNumberToIdxList = new HashMap<>();
        for(int i = 0; i < arr.size(); i++) {
            List<Integer> list = mapNumberToIdxList.getOrDefault(arr.get(i), new ArrayList<>());
            list.add(i);
            mapNumberToIdxList.put(arr.get(i), list);
        }

        // Loop through the array using a brute force approach.
        long count = 0;
        for(int i = 0;  i < arr.size(); i++) {
            long item2Val = arr.get(i)*r;
            List<Integer> item2PosList = mapNumberToIdxList.get(item2Val);
            if (item2PosList != null ) {
                for( int idx2 : item2PosList) {
                    if ( idx2 > i ) { // i.e. it's a progression
                        long item3Val = item2Val*r;
                        List<Integer> item3PosList = mapNumberToIdxList.get(item3Val);
                        if (item3PosList != null ) {
                            for( int idx3 : item3PosList) {
                                if ( idx3 > idx2 ) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
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

