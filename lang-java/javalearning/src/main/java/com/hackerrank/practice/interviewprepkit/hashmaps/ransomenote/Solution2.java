package com.hackerrank.practice.interviewprepkit.hashmaps.ransomenote;

import java.util.*;


// https://www.hackerrank.com/challenges/ctci-ransom-note/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
@SuppressWarnings("Duplicates")
public class Solution2 {

    // **** Took too long
    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {

        Map<String, Integer> wordCountMap = new HashMap<>();
        for(String s : note) {
            wordCountMap.putIfAbsent(s, 0);
            int wordCount = wordCountMap.get(s) + 1;
            wordCountMap.put(s, wordCount);
        }

        for(String s : magazine) {
            Integer i = wordCountMap.get(s);
            if ( i != null ) {
                int tmpCount = i - 1;
                if ( tmpCount == 0 ) {
                    wordCountMap.remove(s);
                    if ( wordCountMap.size() == 0 ) {
                        System.out.println("Yes");
                        return;
                    }
                }
                else
                    wordCountMap.put(s, tmpCount);
            }
        }

        System.out.println("No");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}

