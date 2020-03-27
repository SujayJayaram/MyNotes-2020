package com.suj.problems.firstcharappearingonlyonce;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sujayjayaram on 02/12/2016.
 */
public class FirstCharAppearingOnlyOnce {

    public static void main(String[] args){
        String str = "lliyfyreuif";
        Map<String, Integer> counts = new HashMap<>();
        for(char ch : str.toCharArray()) {
            Integer count = counts.get(String.valueOf(ch));
            if (count == null) {
                count = new Integer(0);
            }

            counts.put(String.valueOf(ch), count.intValue() + 1);
        }

        for(char ch : str.toCharArray()) {
            Integer count = counts.get(String.valueOf(ch));
            if ( count.intValue() == 1) {
                System.out.println("Found " + ch);
                break;
            }
        }
    }
}
