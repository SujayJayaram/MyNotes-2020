package com.hackerrank.practice.interviewprepkit.hashmaps.frequencyqueries;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/frequency-queries/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
// ***** This now works - the Map.containsValue(0 method of previous attempts was taking too long ( O[n] )

@SuppressWarnings("Duplicates")
public class Solution4 {
    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> rv = new ArrayList<>();
        Map<Integer, Long> mapOfCounts = new HashMap<>();
        Map<Long, List<Integer>> mapOfFrequencies = new HashMap<>();

        for(List<Integer> query : queries) {
            int opType = query.get(0);
            int opVal = query.get(1);

            switch(opType) {
                case 1: {
                    long oldCount = mapOfCounts.getOrDefault(opVal, 0L);
                    long newCount = oldCount + 1;
                    mapOfCounts.put(opVal, newCount);
                    updateFrequencyMap(mapOfFrequencies, opVal, oldCount, newCount);
                    break;
                }

                case 2: {
                    long oldCount = mapOfCounts.getOrDefault(opVal, 0L);
                    if ( oldCount > 0 ) {
                        long newCount = oldCount - 1;
                        updateFrequencyMap(mapOfFrequencies, opVal, oldCount, newCount);
                        if ( newCount == 0 )
                            mapOfCounts.remove(opVal);
                        else
                            mapOfCounts.put(opVal, newCount);
                    }
                    break;
                }

                case 3: {
                    if (mapOfFrequencies.containsKey(Long.valueOf(opVal)) )
                        rv.add(1);
                    else
                        rv.add(0);

                    break;
                }

                default:
                    throw new RuntimeException("Should never get here");
            }
        }

        return rv;
    }

    private static void updateFrequencyMap(Map<Long, List<Integer>> mapOfFrequencies, int opVal, long oldCount, long newCount) {
        if ( oldCount != 0 ) {
            List<Integer> list = mapOfFrequencies.get(oldCount);
            if ( list != null ) {
                list.remove(Integer.valueOf(opVal));
                if ( list.size() == 0 )
                    mapOfFrequencies.remove(oldCount);
            }
        }

        if ( newCount != 0 ) {
            List<Integer> list = mapOfFrequencies.get(newCount);
            if ( list != null )
                list.add(opVal);
            else {
                list = new ArrayList<>();
                list.add(opVal);
                mapOfFrequencies.put(newCount, list);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        // Read fist param - how may subsequent lines follow.
        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                // Each line
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
        //System.out.println(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
