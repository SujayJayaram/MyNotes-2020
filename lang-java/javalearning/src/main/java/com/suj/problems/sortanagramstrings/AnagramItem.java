package com.suj.problems.sortanagramstrings;

import java.util.*;

/**
 * Created by sujayjayaram on 28/02/2016.
 */
public class AnagramItem implements Comparable<AnagramItem> {
    private final String text;
    private final String sortedText;

    public AnagramItem(String text) {
        this.text = text;

        List<Character> charSet = new ArrayList<>();
        for(int i = 0; i < text.length(); i++)
            charSet.add(text.charAt(i));

        Collections.sort(charSet);
        StringBuilder sb = new StringBuilder();
        charSet.forEach( i -> sb.append(i));

        sortedText = sb.toString();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        AnagramItem that = (AnagramItem) o;
//
//        return sortedText.equals(that.sortedText);
//    }
//
//    @Override
//    public int hashCode() {
//        return sortedText.hashCode();
//    }

    @Override
    public String toString() {
        return "AnagramItem{" +
                "text='" + text + '\'' +
                '}';
    }

    @Override
    public int compareTo(AnagramItem o) {
        return sortedText.compareTo(o.sortedText);
    }
}
