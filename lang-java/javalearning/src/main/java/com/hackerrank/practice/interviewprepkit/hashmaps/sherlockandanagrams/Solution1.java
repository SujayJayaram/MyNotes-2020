package com.hackerrank.practice.interviewprepkit.hashmaps.sherlockandanagrams;

import java.util.*;

// https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem
public class Solution1 {

    static int sherlockAndAnagrams(String s) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        // Keep track of how many anagrams we've seen
        int totalCount = 0;

        // Generate all substrings (N^2)
        for(int i = 0 ; i < s.length(); i++)
        {
            for(int j=i+1 ; j <= s.length(); j++)
            {
                String currentSubString = s.substring(i,j);

                // Sort all strings E.g. ab & ba both == ab now (they are anagrams)
                char[] chars = currentSubString.toCharArray();
                Arrays.sort(chars);
                currentSubString = String.valueOf(chars);

                int value = map.getOrDefault(currentSubString, 0);
                totalCount += value;
                map.put(currentSubString, ++value);
            }
        }
        return totalCount;
    }
    public static void main(String[] args) {
        String s = "abcab";

        int result = sherlockAndAnagrams(s);
        System.out.println("Result = " + result);
    }
}
