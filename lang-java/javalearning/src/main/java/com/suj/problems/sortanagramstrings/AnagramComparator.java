package com.suj.problems.sortanagramstrings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by sujayjayaram on 28/02/2016.
 */
public class AnagramComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return getSortedString(o1).compareTo(getSortedString(o2));
    }

    private String getSortedString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
