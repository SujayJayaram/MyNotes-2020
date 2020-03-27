package com.suj.puzzlers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by sujayjayaram on 10/02/2016.
 */
public class SentenceWordReverser {

    public static void main(String[] args){

        // Reverse a word
        {
            String sentence = "The fox jumps over the lazy dog";
            String[] words = sentence.split(" ");
            List wordsList = Arrays.asList(words);
            Collections.reverse(wordsList);

            StringBuilder sb = new StringBuilder();
            wordsList.forEach(s -> sb.append(s + " "));

            sb.substring(0, sb.length() - 1); // Remove the trailing space

            System.out.println(sb);
        }

        // Detect if a word is a palindrome
        {
            boolean b = isPalindrom("foooof");
            System.out.println(b);

            b = isPalindrom("qwertyuytrewq");
            System.out.println(b);

            b = isPalindrom("z");
            System.out.println(b);
        }
    }

    private static boolean isPalindrom(String str) {
        if ( (str == null) || (str.length() == 0) )
            return false;

        for(int i = 0; i < ( (str.length()/2) + 1) ; i++){
            char head = str.charAt(i);
            char tail = str.charAt( (str.length()-1) -i);

            if ( head != tail )
                return false;
        }

        return true;
    }
}
