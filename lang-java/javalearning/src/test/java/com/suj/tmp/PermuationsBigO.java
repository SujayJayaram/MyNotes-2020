package com.suj.tmp;

/**
 * Created by sujayjayaram on 23/11/2016.
 */
public class PermuationsBigO {


    public static void main(String[] args) {
        permutations("", "Sujay wins");
    }

    private static void permutations(String prefix, String remainder) {
        if (remainder.length() == 0)
            System.out.println(prefix);
        else
        {
            for (int i = 0; i < remainder.length(); i++) {
                String newRemainder = remainder.substring(0, i) + remainder.substring(i+1);
                permutations(prefix + remainder.charAt(i), newRemainder);
            }
        }
    }

}
