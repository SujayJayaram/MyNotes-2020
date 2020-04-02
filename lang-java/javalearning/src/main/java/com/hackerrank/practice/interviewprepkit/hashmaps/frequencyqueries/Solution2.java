package com.hackerrank.practice.interviewprepkit.hashmaps.frequencyqueries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

// https://www.hackerrank.com/challenges/frequency-queries/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
// ***** Still too slow
@SuppressWarnings("Duplicates")
public class Solution2 {
    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> rv = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        Map<Integer, Long> mapOfCounts = new HashMap<>();

        queries.stream().forEach(
                query -> {
                    int opType = query.get(0);
                    int opVal = query.get(1);

                    switch(opType) {
                        case 1: {
                            data.add(opVal); // Add this value
                            long thisCount = mapOfCounts.getOrDefault(opVal, 0L) + 1;
                            mapOfCounts.put(opVal, thisCount);
                            break;
                        }

                        case 2: {
                            data.remove(Integer.valueOf(opVal)); // Cast to Integer so that there is no ambiguity about which remove() operation we are after.
                            long thisCount = mapOfCounts.getOrDefault(opVal, 0L);
                            if ( thisCount > 0 ) {
                                thisCount--;
                                if ( thisCount == 0 )
                                    mapOfCounts.remove(opVal);
                                else
                                    mapOfCounts.put(opVal, thisCount);
                            }
                            break;
                        }

                        case 3: {
                            if (mapOfCounts.containsValue(Long.valueOf(opVal)))
                                rv.add(1);
                            else
                                rv.add(0);

                            break;
                        }

                        default:
                            throw new RuntimeException("Should never get here");
                    }
                }
        );

        return rv;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

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

        //bufferedWriter.write(
        System.out.println(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        //bufferedWriter.close();
    }
}

